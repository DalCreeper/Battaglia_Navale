package upo.battleship.farrauto.battagliaNavaleController;

import upo.battleship.farrauto.battagliaNavaleModel.*;
import upo.battleship.farrauto.battagliaNavaleView.BattagliaNavaleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class BattagliaNavaleController {
    private BattagliaNavaleView view;                                   //collegamento alla view
    private BattagliaNavaleModel model;                                 //collegamento al model
    private Nave nave;                                                  //collegamento all'estensione del model (nave)
    private Giocatore giocatore;                                        //collegamento all'estensione del model (giocatore)
    private Coordinate coordinate;                                      //collegamento all'estensione del model (coordinate)
    private TimerGioco timerGioco;                                      //collegamento all'estensione del model (TimerGioco)
    private CPU cpu;                                                    //collegamento all'estensione del model (cpu)
    private ControlliSulleNaviPiazzate controlliNavi;                   //collegamento all'estensione del model (controlliSulleNaviPiazzate)
    private SalvaCaricaDati salvaCaricaDati;                            //collegamento all'estensione del model (SalvaCaricaDati)
    private String fieldX;                                              //contenuto del JTextField della coordinata X
    private String fieldY;                                              //contenuto del JTextField della coordinata Y
    private int cmbTipoNave;                                            //contenuto della JComboBox del tipo di nave
    private int cmbOrientamento;                                        //contenuto della JComboBox dell'orientamento della nave
    private int x;                                                      //coordinata X
    private int y;                                                      //coordinata Y
    private int dim;                                                    //dimensione della mappa
    private int id;                                                     //identificantivo di ogni singola nave
    private int numNavi;                                                //numero di navi ancora da piazzare dal giocatore
    private int partita;                                                //variabile che indica lo stato della partita
    private int cont;                                                   //contatore di errori della cpu
    private boolean generaPrimoSparoCpu;                                //indica se il primo sparo è stato effettuato o meno
    private ActionListener gestoreSpara;                                //gestore del pulsante btnSpara
    private ActionListener gestorePosiziona;                            //gestore del pulsante btnPosiziona
    private Point coordinateSparo;                                      //coordinate dello sparo del giocatore
    private Point coordinateSparoCpu;                                   //coordinate dello sparo della CPU
    private Point coordinateNave;                                       //coordinate della posizione della nave

    private final static int MAX = 10;                                  //costante che indica il massimo numero che può generare la cpu
    private final static int MIN = 1;                                   //costante che indica il numero minimo che può generare la cpu
    private final static long tempo = 3600000;                          //3600000 = 1min in millisecondi, indica il tempo di un singolo turno

    /**
     * Il costruttore del controller costruisce gli oggetti coordinate,
     * controlliNave, salvaCaricaDati e timerGioco; inoltre inizializza
     * le variabili necessarie al funzionamento del controller; infine
     * attribuisce ai pulsanti della view i corrispettivi action listener
     * e avvia il metodo Gestori()
     *
     * @param view viene passata in ingresso la view del gioco
     * @param model viene passata in ingresso il model del gioco
     * @see BattagliaNavaleView view
     * @see BattagliaNavaleModel model
     */
    public BattagliaNavaleController(BattagliaNavaleView view, BattagliaNavaleModel model) {
        this.model = model;                 //collegamento al model
        this.view = view;                   //collegamento alla view

        //inizializzazione delle variabili e degli oggetti
        x = 0;                                                              //variabile x delle coordinate inserite dall'utente
        y = 0;                                                              //variabile y delle coordinate inserite dall'utente
        id = 0;                                                             //variabile id che si riferisce all'id di ogni singola nave
        numNavi = 7;                                                        //variabile numNavi che si riferisce al numero di navi ancora da piazzare del giocatore
        coordinate = new Coordinate();                                      //oggetto coordinate della classe Coordinate
        giocatore = model.getGiocatore();                                   //qui faccio il collegamento all'oggetto giocatore della classe Giocatore che è stato modificato nel model
        dim = view.getDim();                                                //prendo la dimensione della mappa dalla costante definita nella view
        controlliNavi = new ControlliSulleNaviPiazzate();                   //oggetto controlliNavi della classe ControlliSulleNaviPiazzate
        cpu = model.getCpu();                                               //oggetto cpu della classe CPU
        generaPrimoSparoCpu = false;                                        //variabile generaPrimoSparoCpu che indica che il primo sparo della cpu non è ancora stato effettuato
        partita = 0;                                                        //variabile partita che indica che la partita non è ancora stata vinta da nessuno
        salvaCaricaDati = new SalvaCaricaDati();                            //oggetto salvaCaricaDati della classe SalvaCaricaDati
        timerGioco = new TimerGioco(tempo, view, model, salvaCaricaDati);   //oggetto timerGioco della classe TimerGioco

        //inizializzo i tutte le griglie a false
        model.inizializzaSpazionaveSpaziosparo(dim);

        //faccio partire il metodo Gestori che contiene i Listener dei 2 pulsanti
        Gestori();

        //assegno il gestore di evento al bottone btnSpara
        view.getBtnSpara().addActionListener(gestoreSpara);

        //assegno il gestore di evento al bottone btnPosiziona
        view.getBtnPosiziona().addActionListener(gestorePosiziona);
    }

    /**
     * Il metodo gestisce gli action performed dei gestori al quale
     * sono stati assegnati gli action listener; il gestoreSpara
     * gestisce gli spari del giocatore e della cpu
     */
    public void Gestori() {
        //creo il gestore di evento per sparare alle coordinate indicate
        gestoreSpara = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //prendo il contenuto dei JTextField dello sparo e lo inserisco in variabili locali di tipo String
                fieldX = view.getCoordinataXSparo().getText();
                fieldY = view.getCoordinataYSparo().getText();

                if(coordinate.verificaTutto(fieldX, fieldY)) {
                    //entrambe le coordinate sono valide, quindi procedo a sparare
                    coordinateSparo = coordinate.getCoordinate();
                    if(model.controllaSparoPresente(coordinateSparo,true, dim)) {
                        //aggiorno la JLabel di informazione
                        model.errorSparoPresente(coordinateSparo);
                        view.setLblInfo(model.getInfo());
                        return;
                    }
                    timerGioco.stop();
                    inserimentoSparo(coordinateSparo, true);
                } else {
                    //aggiorno la JLabel di informazione
                    model.error();
                    view.setLblInfo(model.getInfo());
                    return;
                }
                SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {  //il metodo viene eseguito in un thread a parte in background rispetto al thread principale
                        view.bloccoSparo();
                        if(VinciPartita()) {     //qui viene controllato se il giocatore o la cpu ha vinto la partita
                            timerGioco.stop();
                            return null;
                        }
                        Thread.sleep(2000);
                        model.pensa();
                        view.setLblInfo(model.getInfo());
                        Thread.sleep(2000);
                        return null;
                    }

                    @Override
                    public void done() {    //questo metodo viene eseguito subito dopo il return del metodo precedente
                        if(partita == 0) {
                            turnoCpu();
                            view.sbloccoSparo();
                            if(VinciPartita()) {     //qui viene controllato se il giocatore o la cpu ha vinto la partita
                                timerGioco.stop();
                            }
                        }
                    }
                };
                swingWorker.execute();  //qui viene eseguito effettivamente lo swingWorker, che prima è stato solo creato, ma mai eseguito
            }
        };

        //creo il gestore di evento per sparare alle coordinate indicate
        gestorePosiziona = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //prendo il contenuto dei JTextField dello sparo e lo inserisco in variabili locali di tipo String
                fieldX = view.getTxtCoordinataXPosiziona().getText();
                fieldY = view.getTxtCoordinataYPosiziona().getText();
                cmbOrientamento = view.getCmbOrientamento().getSelectedIndex();
                cmbTipoNave = view.getCmbTipoNave().getSelectedIndex();

                if (coordinate.verificaTutto(fieldX, fieldY)) {
                    //entrambe le coordinate sono valide, quindi procedo a posizionare la nave
                    coordinateNave = coordinate.getCoordinate();
                    if(controlliNavi.controlloNaviPosizionate(coordinateNave,true, model, dim)) {
                        //aggiorno la JLabel di informazione
                        model.errorNavePresente(coordinateNave);
                        view.setLblInfo(model.getInfo());
                        return;
                    }
                    if(controlliNavi.controlloNaviVicine(coordinateNave,true, cmbOrientamento, cmbTipoNave, model, dim)) {
                        //aggiorno la JLabel di informazione
                        model.errorNaveVicina();
                        view.setLblInfo(model.getInfo());
                        return;
                    }
                    id++;
                    nave = new Nave(coordinateNave, cmbOrientamento, cmbTipoNave, id);
                    giocatore.aggiungiNave(nave);
                    salvaCaricaDati.setNaviGiocatore(giocatore.getNaviSfidante());
                    model.piazzaNaveNellaMappa(nave,true, dim);
                    view.updateGrigliaNavi(model);
                    //aggiorno la JLabel del numero di navi rimanenti
                    numNavi--;
                    view.setLblNumNavi(numNavi);
                    //aggiorno la JLabel di informazione
                    nave.navePosizionata();
                    view.setLblInfo(nave.getInfo());
                } else {
                    //aggiorno la JLabel di informazione
                    model.error();
                    view.setLblInfo(model.getInfo());
                    return;
                }
                if(id > 6) {
                    cpu.estrapolaNavi(model, dim);  //la cpu controlla la tipologia di navi isnerite dal giocatore e poi piazza le sue, dello stesso tipo casualmente
                    salvaCaricaDati.setNaviCpu(cpu.getNaviSfidante());
                    view.bloccoPosizionamento();
                    view.sbloccoSparo();
                    timerGioco.start();
                }
            }
        };
    }

    /**
     * Il metodo gestisce il turno della cpu
     */
    public void turnoCpu() {
        if(!generaPrimoSparoCpu) {  //genera il primo sparo casualmente
            generaPrimoSparoCpu = true;
            do {
                cont = 0;
                coordinateSparoCpu = cpu.generaCoordinate();
                if (model.controllaSparoPresente(coordinateSparoCpu, false, dim)) {
                    cont++;
                }
            } while (cont > 0);
        } else {    //altrimenti inizia a pensare dove sparare, quindi se fare un colpo casuale per beccare una nave, o se ne ha colpita una, provare a sparare vicino
            if (cpu.cervello(model, dim)) {
                coordinateSparoCpu = cpu.getCoordinateFinali();
            } else {
                do {
                    cont = 0;
                    coordinateSparoCpu = cpu.generaCoordinate();
                    if (model.controllaSparoPresente(coordinateSparoCpu, false, dim)) {
                        cont++;
                    }
                } while (cont > 0);
            }
        }
        inserimentoSparo(coordinateSparoCpu,false);
        timerGioco.resetTimer(tempo);   //resetto il timer a 1min ogni volta che finisce la rotazione dei turni (prima giocatore e poi cpu)
        timerGioco.start();
    }

    /**
     * Il metodo richiama i metodi nel model per controllare se lo
     * sparo effettuato ha colpito il bersaglio e poi aggiorna la view
     *
     * @param coordinate vengono date le coordinate dove effettuare lo sparo
     * @param turno viene passato in ingresso il valore booleano che indica
     *              il turno (true = giocatore e false = cpu)
     */
    public void inserimentoSparo(Point coordinate, boolean turno) {
        model.inserisciSparo(coordinate, turno, dim);
        int sparo = 0;
        sparo = model.spara(coordinate, turno, dim);

        if(sparo == 1) {                                                //nave colpita e affondata
            view.updateGrigliaSparoColpitoEAffondato(model, turno);
        } else if(sparo == 2) {                                         //nave colpita
            view.updateGrigliaSparoColpito(coordinate, turno);
        } else if(sparo == 3) {                                         //nave mancata
            view.updateGrigliaSparoMancato(coordinate, turno);
        } else {
            //aggiorno la JLabel di informazione
            model.error();
            view.setLblInfo(model.getInfo());
        }
        //aggiorno la JLabel di informazione
        view.setLblInfo(model.getInfo());
    }

    /**
     * Il metodo controlla se il giocatore o la cpu vincono la partita
     *
     * @return true se la partita e' stata vinta
     *         false se la partita non e' ancora finita
     */
    public boolean VinciPartita() {
        partita = model.controlloPartita();
        if(partita == 1) {          //partita vinta dal giocatore
            view.bloccoSparo();
            timerGioco.stop();
            //aggiorno la JLabel di informazione
            model.vittoriaGiocatore();
            view.setLblInfo(model.getInfo());
            return true;
        } else if(partita == 2) {   //partita vinta dalla cpu
            view.bloccoSparo();
            timerGioco.stop();
            //aggiorno la JLabel di informazione
            model.vittoriaCpu();
            view.setLblInfo(model.getInfo());
            return true;
        }
        return false;
    }

    /**
     * Il metodo resetta il timer del turno del giocatore
     */
    public void resetTimer() {
        timerGioco.stop();
        timerGioco.resetTimerView(view);
        timerGioco.resetTimer(tempo);
    }

    /**
     * Il metodo reinizializza tutte le variabili per creare
     * una nuova partita
     */
    public void reset() {
        ArrayList<Point> spariGiocatore;
        ArrayList<Point> spariCpu;
        ArrayList<Nave> naviGiocatore;
        ArrayList<Nave> naviCpu;
        spariGiocatore = new ArrayList<>();
        spariCpu = new ArrayList<>();
        naviGiocatore = new ArrayList<>();
        naviCpu = new ArrayList<>();

        //reinizializzo le liste delle navi e degli spari
        giocatore.setNaviSfidante(naviGiocatore);
        cpu.setNaviSfidante(naviCpu);
        giocatore.setSpariSfidante(spariGiocatore);
        cpu.setSpariSfidante(spariCpu);

        //resetto la variabile colpito di ogni nave delle due liste uguale al tipoNave
        this.resetColpitoNavi(true);
        this.resetColpitoNavi(false);

        resetTimer();
        partita = 0;
        id = 0;
        numNavi = 7;
        generaPrimoSparoCpu = false;
        model.inizializzaSpazionaveSpaziosparo(dim);
    }

    /**
     * Il metodo assegna a tutte le variabili sensibili del gioco, i dati
     * presi dal file di salvataggio, così da caricare una partita
     * precedentemente salvata
     */
    public void caricaPartita() {
        ArrayList<Point> spariGiocatore;
        ArrayList<Point> spariCpu;
        ArrayList<Nave> naviGiocatore;
        ArrayList<Nave> naviCpu;
        spariGiocatore = new ArrayList<>();
        spariCpu = new ArrayList<>();
        naviGiocatore = new ArrayList<>();
        naviCpu = new ArrayList<>();

        //reinizializzo tutte le griglie
        model.inizializzaSpaziosparo(dim);

        //reinizializzo le liste delle navi e degli spari
        giocatore.setNaviSfidante(naviGiocatore);
        cpu.setNaviSfidante(naviCpu);
        giocatore.setSpariSfidante(spariGiocatore);
        cpu.setSpariSfidante(spariCpu);

        //risetto le liste delle navi e degli spari del giocatore e della cpu con quelle salvate nel Partita.sav
        giocatore.setNaviSfidante(salvaCaricaDati.getNaviGiocatore());
        giocatore.aggiornaLunghezza();
        cpu.setNaviSfidante(salvaCaricaDati.getNaviCpu());
        cpu.setNaviGiocatore(salvaCaricaDati.getNaviGiocatore());   //qui viene settato l'ArrayList locale di CPU che contiene le navi del giocatore, non cambia nulla nella Classe Giocatore
        cpu.aggiornaLunghezza();

        //setto le griglie reinizializzate con le griglie salvate nel Partita.sav
        model.setSpazioNavi(salvaCaricaDati.getSpazioNavi());
        view.updateGrigliaNavi(model);
        model.setSpazioNaviCpu(salvaCaricaDati.getSpazioNaviCpu());

        //resetto la variabile colpito di ogni nave delle due liste uguale al tipoNave
        this.resetColpitoNavi(true);
        this.resetColpitoNavi(false);

        //risparo a tutte le coordinate salvate nell'Array degli spari del giocatore e della cpu
        this.caricaSpari(true);
        this.caricaSpari(false);

        //risetto l'ultima Info nel display
        model.setInfo(salvaCaricaDati.getInfo());
        view.setLblInfo(salvaCaricaDati.getInfo());

        //risetto i millisecondi del timer con il valore salvato nel file Partita.sav
        timerGioco.setStartTime(salvaCaricaDati.getStartTimeSave());
        timerGioco.start();
    }

    /**
     * Il metodo reimposta la variabile colpito della classe Nave
     * uguale al tipo della nave stessa, così da reimpostare il numero
     * di tentativi per poter affondare una nave
     *
     * @param turno viene passato in ingresso il valore booleano che indica
     *              il turno (true = giocatore e false = cpu)
     */
    public void resetColpitoNavi(boolean turno) {
        if(turno) {
            for (Nave i: giocatore.getNaviSfidante()) {
                i.setColpito(i.getTipoNave());
            }
        } else {
            for (Nave j : cpu.getNaviSfidante()) {
                j.setColpito(j.getTipoNave());
            }
        }
    }

    /**
     * Il metodo rispara tutti i colpi salvati nei rispettivi ArrayList,
     * che sono stati estrapolati dal file di salvataggio di una partita
     * precedentemente salvata
     *
     * @param turno viene passato in ingresso il valore booleano che indica
     *              il turno (true = giocatore e false = cpu)
     */
    public void caricaSpari(boolean turno) {
        if(turno) {
            for (Point i: salvaCaricaDati.getSpariGiocatore()) {
                this.inserimentoSparo(i,true);
            }
        } else {
            for (Point j : salvaCaricaDati.getSpariCpu()) {
                this.inserimentoSparo(j,false);
            }
        }
    }

    /**
     * Metodo getter della variabile timerGioco
     *
     * @return timerGioco
     */
    public TimerGioco getTimerGioco() {
        return timerGioco;
    }

    /**
     * Metodo getter della variabile id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo setter della variabile id
     * <p>
     * Viene modificata la variabile id per indicare che sono
     * state piazzate tutte le navi
     * </p>
     */
    public void setId() {
        this.id = numNavi;
    }

    /**
     * Metodo setter della variabile generaPrimoSparoCpu
     * <p>
     * Il generaPrimoSparoCpu viene settato solo quando viene caricata
     * una partita o generata una nuova
     * </p>
     *
     * @param generaPrimoSparoCpu viene passato il valore booleano
     *                            modificato, cosi' viene indicato
     */
    public void setGeneraPrimoSparoCpu(boolean generaPrimoSparoCpu) {
        this.generaPrimoSparoCpu = generaPrimoSparoCpu;
    }

    /**
     * Metodo getter della variabile partita
     *
     * @return partita
     */
    public int getPartita() {
        return partita;
    }

    /**
     * Metodo setter della variabile partita
     * <p>
     * La variabile partita viene settata solo quando viene generata
     * una nuova partita
     * </p>
     *
     * @param partita viene passato il valore modificato
     */
    public void setPartita(int partita) {
        this.partita = partita;
    }

    /**
     * Metodo getter della variabile salvaCaricaDati
     *
     * @return salvaCaricaDati
     */
    public SalvaCaricaDati getSalvaCaricaDati() {
        return salvaCaricaDati;
    }

    /**
     * Metodo setter della variabile salvaCaricaDati
     * <p>
     * La variabile salvaCaricaDati viene settata solo quando viene
     * caricata una partita dal file di salvataggio generato da una
     * partita precedentemente salvata
     * </p>
     *
     * @param salvaCaricaDati viene passato il valore modificato
     */
    public void setSalvaCaricaDati(SalvaCaricaDati salvaCaricaDati) {
        this.salvaCaricaDati = salvaCaricaDati;
    }
}
