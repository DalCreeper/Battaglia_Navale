package upo.battleship.farrauto.battagliaNavaleController;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.SalvaCaricaDati;
import upo.battleship.farrauto.battagliaNavaleView.BattagliaNavaleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class MenuOptions {
    private BattagliaNavaleView view;                                   //
    private BattagliaNavaleModel model;                                 //
    private BattagliaNavaleController controller;                       //
    private JFrame dialog;                                              //mini frame per visualizzare i messaggi
    private ActionListener gestoreNuovaPartita;                         //gestore del JMenuItem itemNuovaPartita
    private ActionListener gestoreCaricaPartita;                        //gestore del JMenuItem itemCaricaPartita
    private ActionListener gestoreSalvaPartita;                         //gestore del JMenuItem itemSalvaPartita
    private ActionListener gestoreEsci;                                 //gestore del JMenuItem itemEsci
    private ActionListener gestoreIstruzioni;                           //gestore del JMenuItem itemIstruzioni
    private ActionListener gestoreCredits;                              //gestore del JMenuItem itemCredits

    /**
     * Il cotruttore del menuOptions attribuisce ai pulsanti dei
     * Jmenu nella view i corrispettivi action listener e avvia
     * il metodo Gestori()
     *
     * @param view viene passata la view
     * @param model viene passato il model
     * @param controller viene passato il controller
     * @see BattagliaNavaleView view
     * @see BattagliaNavaleModel model
     * @see BattagliaNavaleController controller
     */
    public MenuOptions(BattagliaNavaleView view, BattagliaNavaleModel model, BattagliaNavaleController controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;

        //faccio partire il metodo Gestori che contiene i Listener dei pulsanti del JMenuBar
        Gestori();

        //assegno il gestore di evento al JMenuItem itemNuovaPartita
        view.getItemNuovaPartita().addActionListener(gestoreNuovaPartita);

        //assegno il gestore di evento al JMenuItem itemCaricaPartita
        view.getItemCaricaPartita().addActionListener(gestoreCaricaPartita);

        //assegno il gestore di evento al JMenuItem itemSalvaPartita
        view.getItemSalvaPartita().addActionListener(gestoreSalvaPartita);

        //assegno il gestore di evento al JMenuItem itemEsci
        view.getItemEsci().addActionListener(gestoreEsci);

        //assegno il gestore di evento al JMenuItem itemIstruzioni
        view.getItemIstruzioni().addActionListener(gestoreIstruzioni);

        //assegno il gestore di evento al JMenuItem itemCredits
        view.getItemCredits().addActionListener(gestoreCredits);
    }

    /**
     * Il metodo gestisce gli action performed dei gestori al quale
     * sono stati assegnati gli action listener; vengono gestiti i
     * pulsanti del nuovaPartita, caricaPartita, salvaPartita,
     * esci, istruzioni e credits
     */
    public void Gestori() {
        //creo il gestore di evento per creare una nuova partita
        gestoreNuovaPartita = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.reset();
                controller.reset();
            }
        };

        //creo il gestore di evento per caricare una partita salvata in precedenza
        gestoreCaricaPartita = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerGioco timerGioco = controller.getTimerGioco();
                SalvaCaricaDati salvaCaricaDati = controller.getSalvaCaricaDati();
                if(controller.getId() > 6) {
                    timerGioco.stop();
                }
                dialog = new JFrame();
                int scelta = JOptionPane.showConfirmDialog(dialog,"Vuoi caricare una partita?", "Carica partita", JOptionPane.YES_NO_OPTION);
                switch (scelta) {
                    case JOptionPane.YES_OPTION:
                        carica();
                        if(controller.getId() > 6) {
                            if (controller.getPartita() == 0) {
                                timerGioco = controller.getTimerGioco();
                                timerGioco.start();
                            }
                        }
                        break;
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        if(controller.getId() > 6) {
                            if (controller.getPartita() == 0) {
                                timerGioco.start();
                            }
                        }
                        break;
                }
            }
        };

        //creo il gestore di evento per salvare la partita in corso
        gestoreSalvaPartita = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerGioco timerGioco = controller.getTimerGioco();
                SalvaCaricaDati salvaCaricaDati = controller.getSalvaCaricaDati();
                if(controller.getId() > 6) {
                    timerGioco.stop();
                }
                dialog = new JFrame();
                int scelta = JOptionPane.showConfirmDialog(dialog,"Vuoi salvare la partita corrente?", "Salva partita", JOptionPane.YES_NO_OPTION);
                switch (scelta) {
                    case JOptionPane.YES_OPTION:
                        String nickname = JOptionPane.showInputDialog(null, "Inserisci il tuo nickname.");
                        salva(nickname, salvaCaricaDati);
                        if(controller.getId() > 6) {
                            if (controller.getPartita() == 0) {
                                timerGioco.start();
                            }
                        }
                        break;
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        if(controller.getId() > 6) {
                            if (controller.getPartita() == 0) {
                                timerGioco.start();
                            }
                        }
                        break;
                }
            }
        };

        //creo il gestore di evento per uscire dal gioco da menù invece che dalla barra del titolo
        gestoreEsci = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerGioco timerGioco = controller.getTimerGioco();
                if(controller.getId() > 6) {
                    timerGioco.stop();
                }
                dialog = new JFrame();
                int scelta = JOptionPane.showConfirmDialog(dialog,"Sei sicuro di voler uscire?", "Esci", JOptionPane.YES_NO_OPTION);
                switch (scelta) {
                    case JOptionPane.YES_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        if(controller.getId() > 6) {
                            if (controller.getPartita() == 0) {
                                timerGioco.start();
                            }
                        }
                        break;
                }
            }
        };

        //creo il gestore di evento per visualizzare un JOptionPane con le istruzioni del gioco
        gestoreIstruzioni = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerGioco timerGioco = controller.getTimerGioco();
                if(controller.getId() > 6) {
                    timerGioco.stop();
                }
                dialog = new JFrame();
                JOptionPane.showMessageDialog(dialog, "Per giocare a battaglia navale occorrono quattro tabelle\n(due per giocatore), in questo caso solo 2 perchè il gioco\nè contro una CPU, tutte di uguali dimensioni (per esempio\n10×10 come in questo gioco o un'altra dimensione concordata\ndai giocatori). I quadretti della tabella sono identificate da\ncoppie di coordinate, corrispondenti a riga e colonna; tradizionalmente\nsi usano lettere per le colonne e numeri per le righe (perciò le celle\nsarebbero \"A-1\", \"B-6\", e così via, ma in questo caso\ncoppie di numeri \"1-1\", \"3-6\" ecc.). All'inizio, i\ngiocatori devono \"posizionare le proprie navi\" segnandole su\nuna delle loro due griglie (che terranno nascoste all'avversario\nper tutta la durata del gioco), ma in questo caso non c'è\nbisogno di nascondere nulla perchè si gioca contro la CPU,\nquindi per iniziare basterà inserire le coordinate della prua\ndella prima nave che verrà inserita nelle celle a\ndestra o in basso a seconda dell'orientamento inserito.\n\nUna \"nave\" occupa un certo numero di quadretti adiacenti in linea\nretta (orizzontale o verticale) sulla tabella. Due navi non\npossono toccarsi. I giocatori generalmente si accordano preliminarmente\nsu quante navi disporre e di quali dimensioni, in questo caso\nè impostato di DEFAULT il numero di navi da inserire (7) e\nle tipologie si possono scegliere nel menù tendina. La CPU sceglierà\nle stesse tipologie di navi da inserire che ha deciso il giocatore.\nVi sono diverse tipologie di navi, un sottomarino è di solito\nuna nave di dimensione 3, ma qui sarà di dimensione 1, un\ncacciatorpediniere è di dimensione 2, un incrociatore è di dimensione\n3 e le navi di lunghezza superiore sono corazzate (dimensione 4) e\nportaerei (dimensione 5), ma in questo gioco le corazzate non sono\npresenti e le portaerei sono di dimensione 4.\n\nUna volta posizionate le navi, il gioco procede a turni. Il giocatore\ndi turno \"spara un colpo\" dichiarando un quadretto (per esempio,\n\"2-5\"). L'avversario controlla sulla propria griglia se quella cella\nè occupata da una sua nave. In caso affermativo risponde \"colpito!\",\ne marca quel quadretto sulla propria tabella, che in questo gioco\novviamente non è visibile essendo della CPU; in caso negativo risponde\n\"acqua\" o \"mancato\". Sulla seconda tabella in dotazione i giocatori prendono\nnota dei colpi che hanno sparato e del loro esito (questo viene gestito in\nmodo automatico dal gioco, sono visibili solo le 2 tabelle del giocatore).\nQuando un colpo centra l'ultimo quadretto di una nave non ancora affondata, il\ngiocatore che subisce il colpo dovrà dichiarare \"colpito e affondato!\" (anche\nquesto è gestito in automatico dalla console), e la nave si considera persa.\nVince il giocatore che per primo affonda tutte le navi dell'avversario, in\nquesto gioco però c'è una condizione di vittoria in più per la CPU, ovvero, se\nil giocatore ci mette più di 1 min a compiere il suo turno, la CPU vince.", "Istruzioni", JOptionPane.INFORMATION_MESSAGE);
                if(controller.getId() > 6) {
                    if (controller.getPartita() == 0) {
                        timerGioco.start();
                    }
                }
            }
        };

        //creo il gestore di evento per visualizzare un JOptionPane con i crediti allo sviluppatore dell'applicazione
        gestoreCredits = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerGioco timerGioco = controller.getTimerGioco();
                if(controller.getId() > 6) {
                    timerGioco.stop();
                }
                dialog = new JFrame();
                JOptionPane.showMessageDialog(dialog, "Questo gioco è stato svilluppato interamente da Loris Farrauto e Stefano\nAlcamo come progetto universitario, durante i mesi di quarantena della\ntragica pandemia del 2020. Con molto coraggio gli sviluppatori sono rimasti\nchiusi in casa, facendosi crescere barba e capelli e lavorando senza sosta!!\n*applausi*", "Credits", JOptionPane.INFORMATION_MESSAGE);
                if(controller.getId() > 6) {
                    if (controller.getPartita() == 0) {
                        timerGioco.start();
                    }
                }
            }
        };
    }

    /**
     * Il metodo gestisce il salvataggio di tutti i dati
     * sensibili al funzionamento del gioco in un file
     * Partita.sav
     *
     * @param nickname viene passato dal JControlPanel nel
     *                 suo actionPerformed
     * @param salvaCaricaDati viene passato il class nel quale
     *                        sono salvati tutti i dati sensibili
     *                        di una singola partita
     */
    public void salva(String nickname, SalvaCaricaDati salvaCaricaDati) {
        FileOutputStream salva;
        ObjectOutputStream salvaOutput;
        salvaCaricaDati.setSpariGiocatore(model.getGiocatore().getSpariSfidante());
        salvaCaricaDati.setSpariCpu(model.getCpu().getSpariSfidante());
        salvaCaricaDati.setInfo(model.getInfo());
        salvaCaricaDati.setSpazioNavi(model.getSpazioNavi());
        salvaCaricaDati.setSpazioNaviCpu(model.getSpazioNaviCpu());
        try {
            salva = new FileOutputStream("Partita.sav");
            salvaOutput = new ObjectOutputStream(salva);
            salvaOutput.writeObject(nickname);
            salvaOutput.writeObject(salvaCaricaDati);
            salvaOutput.close();
            salva.close();
        } catch(Exception e) {
            dialog = new JFrame();
            JOptionPane.showMessageDialog(dialog, "Partita non salvata correttamente!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Il metodo gestisce il caricamento dei dati sensibili
     * di una singola partita, estrapolando i 2 oggetti dal
     * file Partita.sav
     */
    public void carica() {
        FileInputStream carica;
        ObjectInputStream caricaInput;
        try {
            carica = new FileInputStream("Partita.sav");
            caricaInput = new ObjectInputStream(carica);
            String nicknameSalvato = (String) caricaInput.readObject();
            SalvaCaricaDati salvaCaricaDatiDaFile = (SalvaCaricaDati) caricaInput.readObject();
            caricaInput.close();
            carica.close();

            //resetto i campi della view
            view.reset();

            view.setLblNickname(nicknameSalvato);
            controller.setSalvaCaricaDati(salvaCaricaDatiDaFile);
            view.bloccoPosizionamento();
            view.sbloccoSparo();
            controller.caricaPartita();

            //setto l'id del controller a 7 (quindi il controller così sa che il giocatore ha piazzato le sue navi)
            controller.setId();

            //setto la condizione di vittoria della partita a 0
            controller.setPartita(0);

            //setto il primo sparo della cpu false, se non ha ancora sparato e true, se invece ha già sparato almeno 1 colpo
            controller.setGeneraPrimoSparoCpu(salvaCaricaDatiDaFile.getSpariCpu().size() != 0);
        } catch(Exception e) {
            dialog = new JFrame();
            JOptionPane.showMessageDialog(dialog, "Il caricamento della partita non è andato a buon fine!\nControllare che sia presente il file di salvataggio:\nPartita.sav", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
