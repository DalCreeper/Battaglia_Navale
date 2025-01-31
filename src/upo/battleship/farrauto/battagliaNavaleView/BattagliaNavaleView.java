package upo.battleship.farrauto.battagliaNavaleView;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.CPU;
import upo.battleship.farrauto.battagliaNavaleModel.Giocatore;
import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class BattagliaNavaleView {
    private JFrame frame;
    private JLabel lblInfo;
    private JLabel lblCoordinateSpara;
    private JLabel lblXSpara;
    private JLabel lblYSpara;
    private JLabel lblIndicazioniCoordinateSpara;
    private JLabel lblCoordinatePosiziona;
    private JLabel lblXPosiziona;
    private JLabel lblYPosiziona;
    private JLabel lblOrientamento;
    private JLabel lblTipoNave;
    private JLabel lblIndicazioniCoordinatePosiziona;
    private JLabel lblImg;
    private JLabel lblInfoImg;
    private JLabel lblSopraNumNaviDaPosizionare;
    private JLabel lblSottoNumNaviDaPosizionare;
    private JLabel lblNumNavi;
    private JLabel lblInfoTime;
    private JLabel lblTime;
    private JLabel lblNickname;
    private JButton btnSpara;
    private JButton btnPosiziona;
    private CampoDaGioco campo;
    private CampoDaGioco campo2;
    private CampoDaGioco separatore;
    private CampoDaGioco separatore2;
    private JTextField txtCoordinataXSparo;
    private JTextField txtCoordinataYSparo;
    private JTextField txtCoordinataXPosiziona;
    private JTextField txtCoordinataYPosiziona;
    private JPanel pnlInfo;
    private JPanel pnlBlocco;
    private JComboBox<String> cmbOrientamento;
    private JComboBox<String> cmbTipoNave;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuInfo;
    private JMenuItem itemNuovaPartita;
    private JMenuItem itemCaricaPartita;
    private JMenuItem itemSalvaPartita;
    private JMenuItem itemEsci;
    private JMenuItem itemIstruzioni;
    private JMenuItem itemCredits;
    private Font fontLblImg;
    private Font fontLblNumNavi;
    private Font fontLblTime;
    private Border border;
    private Giocatore giocatore;                        //collegamento all'estensione del model (giocatore)
    private CPU cpu;                                    //collegamento all'estensione del model (giocatore)

    private final int dim = 11;                         //costante di grandezza del campo da gioco
    private final int larSeparatore = 1;                //costante di larghezza del separatore (lungo quanto il campo da gioco)

    /**
     * Il costruttore della view costruisce tutti gli
     * oggetti grafici di SWING
     *
     * @param titolo contiene il nome del JFrame
     */
    public BattagliaNavaleView (String titolo) {
        //creazione e settaggio del JFrame
        frame = new JFrame(titolo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1072, 725);

        //comando per rendere visibile il JFrame
        frame.setVisible(true);

        //settaggio finestra allineata al centro dello schermo
        frame.setLocationRelativeTo(null);

        //settaggio finestra non ridimensionabile
        frame.setResizable(false);

        //settaggio layout ad absolute, quindi posso posizionare gli elementi grafici dove voglio
        frame.setLayout(null);

        //creazione e settaggio menù
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuInfo = new JMenu("   ?   ");
        menuBar.add(menuInfo);
        itemNuovaPartita = new JMenuItem("Nuova partita");
        menuFile.add(itemNuovaPartita);
        itemCaricaPartita = new JMenuItem("Carica partita");
        menuFile.add(itemCaricaPartita);
        itemSalvaPartita = new JMenuItem("Salva partita");
        itemSalvaPartita.setEnabled(false);
        menuFile.add(itemSalvaPartita);
        menuFile.addSeparator();
        itemEsci = new JMenuItem("Esci");
        menuFile.add(itemEsci);
        itemIstruzioni = new JMenuItem("Istruzioni");
        menuInfo.add(itemIstruzioni);
        itemCredits = new JMenuItem("Credits");
        menuInfo.add(itemCredits);
        frame.setJMenuBar(menuBar);

        //creazione e settaggio JLabel del tempo di gioco rimanente
        lblInfoTime = new JLabel("Tempo rimanente:");
        lblInfoTime.setBounds(10, 630, 150, 30);
        frame.getContentPane().add(lblInfoTime);
        lblInfoTime.setVisible(true);

        //creazione e settaggio JLabel del tempo di gioco rimanente
        lblTime = new JLabel("1:00:00");
        lblTime.setBounds(110, 623, 150, 40);
        fontLblTime = new Font("SansSerif", Font.BOLD, 30);
        lblTime.setFont(fontLblTime);
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblTime);
        lblTime.setVisible(true);

        //creazione e pre-set della Label Info dentro il suo pannello
        border = BorderFactory.createLineBorder(Color.BLACK, 1);
        pnlInfo = new JPanel(new GridLayout(1,1));
        pnlInfo.setBounds(268, 630, 536, 30);
        pnlInfo.setOpaque(true);
        pnlInfo.setBackground(Color.WHITE);
        pnlInfo.setBorder(border);
        frame.getContentPane().add(pnlInfo);
        pnlInfo.setVisible(true);
        lblInfo = new JLabel("INIZIA! Posiziona le navi!");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setVerticalAlignment(SwingConstants.CENTER);
        pnlInfo.add(lblInfo);
        lblInfo.setVisible(true);

        //creazione e pre-set della Label Nickname
        lblNickname = new JLabel("Giocatore_1");
        lblNickname.setBounds(814, 630, 150, 30);
        frame.getContentPane().add(lblNickname);
        lblNickname.setVisible(true);

        //creazione del pannello di blocco visualizzazione dell'interfaccia di posizionamento delle navi
        pnlBlocco = new JPanel();
        pnlBlocco.setBounds(10, 520, 760, 100);
        pnlBlocco.setOpaque(true);
        pnlBlocco.setBackground(Color.WHITE);
        pnlBlocco.setBorder(border);
        frame.getContentPane().add(pnlBlocco);
        lblImg = new JLabel();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("resource/images/Veliero.png");
        assert resource != null;
        ImageIcon icon = new ImageIcon(resource);
        lblImg.setIcon(icon);
        lblImg.setVerticalAlignment(SwingConstants.CENTER);
        lblImg.setHorizontalAlignment(SwingConstants.LEFT);
        lblInfoImg = new JLabel("Congratulazioni hai inserito tutte le navi! Buona fortuna marinaio!!");
        lblInfoImg.setVerticalAlignment(SwingConstants.CENTER);
        lblInfoImg.setHorizontalAlignment(SwingConstants.LEFT);
        fontLblImg = new Font("Helvetica", Font.BOLD, 20);
        lblInfoImg.setFont(fontLblImg);
        pnlBlocco.add(lblImg);
        pnlBlocco.add(lblInfoImg);
        pnlBlocco.setVisible(false);

        //creazione e settaggio label sopra alle coordinate per sparare
        lblCoordinateSpara = new JLabel("Inserisci qui le coordinate:");
        lblCoordinateSpara.setBounds(785, 525, 300, 30);
        frame.getContentPane().add(lblCoordinateSpara);
        lblCoordinateSpara.setVisible(true);

        //creazione e settaggio label per indicare il JTextField della coordinata X per sparare
        lblXSpara = new JLabel("X:");
        lblXSpara.setBounds(820, 564, 25, 30);
        frame.getContentPane().add(lblXSpara);
        lblXSpara.setVisible(true);

        //creazione e settaggio della casella di testo per la coordinata X per sparare
        txtCoordinataXSparo = new JTextField();
        txtCoordinataXSparo.setBounds(845,564,25,30);
        txtCoordinataXSparo.setEditable(false);
        frame.getContentPane().add(txtCoordinataXSparo);
        txtCoordinataXSparo.setVisible(true);

        //creazione e settaggio label per indicare il JTextField della coordinata Y per sparare
        lblYSpara = new JLabel("Y:");
        lblYSpara.setBounds(890, 564, 25, 30);
        frame.getContentPane().add(lblYSpara);
        lblYSpara.setVisible(true);

        //creazione e settaggio della casella di testo per la coordinata Y per sparare
        txtCoordinataYSparo = new JTextField();
        txtCoordinataYSparo.setBounds(915,564,25,30);
        txtCoordinataYSparo.setEditable(false);
        frame.getContentPane().add(txtCoordinataYSparo);
        txtCoordinataYSparo.setVisible(true);

        //creazione e settaggio label sotto alle coordinate per sparare
        lblIndicazioniCoordinateSpara = new JLabel("(Coordinate tra 1 e 10)");
        lblIndicazioniCoordinateSpara.setBounds(810, 590, 300, 30);
        frame.getContentPane().add(lblIndicazioniCoordinateSpara);
        lblIndicazioniCoordinateSpara.setVisible(true);

        //creazione del bottone per le azioni dell'utente
        btnSpara = new JButton("SPARA!");
        btnSpara.setBounds(950,550,100,50);
        btnSpara.setEnabled(false);
        frame.getContentPane().add(btnSpara);
        btnSpara.setVisible(true);

        //creazione e settaggio del campo da gioco
        campo = new CampoDaGioco(dim);
        campo.setBounds(10, 10, 500, 500);
        frame.getContentPane().add(campo);
        campo.setVisible(true);

        //creazione di un separatore per allineare tutto meglio
        separatore = new CampoDaGioco(larSeparatore);
        separatore.setBounds(530,10,1,500);
        frame.getContentPane().add(separatore);
        separatore.setVisible(true);

        //creazione di un separatore per allineare tutto meglio
        separatore2 = new CampoDaGioco(larSeparatore);
        separatore2.setBounds(768,530,1,85);
        frame.getContentPane().add(separatore2);
        separatore2.setVisible(true);

        //creazione e settaggio del campo da gioco avversario
        campo2 = new CampoDaGioco(dim);
        campo2.setBounds(550, 10, 500, 500);
        frame.getContentPane().add(campo2);
        campo2.setVisible(true);

        //inserimento coordinate all'interno della tabella
        campo.riferimentiGriglia();
        campo2.riferimentiGriglia();

        //creazione e settaggio del bottone per posizionare le navi
        btnPosiziona = new JButton("Posiziona");
        btnPosiziona.setBounds(10,550,100,50);
        frame.getContentPane().add(btnPosiziona);
        btnPosiziona.setVisible(true);

        //creazione e settaggio label sopra alle coordinate per posizionare le navi
        lblCoordinatePosiziona = new JLabel("Inserisci qui le coordinate, l'orientamento e la tipologia della nave:");
        lblCoordinatePosiziona.setBounds(120, 525, 400, 30);
        frame.getContentPane().add(lblCoordinatePosiziona);
        lblCoordinatePosiziona.setVisible(true);

        //creazione e settaggio label per indicare il JTextField della coordinata X per posizionare una nave
        lblXPosiziona = new JLabel("X:");
        lblXPosiziona.setBounds(120, 564, 25, 30);
        frame.getContentPane().add(lblXPosiziona);
        lblXPosiziona.setVisible(true);

        //creazione e settaggio della casella di testo per la coordinata X per posizionare una nave
        txtCoordinataXPosiziona = new JTextField();
        txtCoordinataXPosiziona.setBounds(145,564,25,30);
        frame.getContentPane().add(txtCoordinataXPosiziona);
        txtCoordinataXPosiziona.setVisible(true);

        //creazione e settaggio label per indicare il JTextField della coordinata Y per posizionare una nave
        lblYPosiziona = new JLabel("Y:");
        lblYPosiziona.setBounds(190, 564, 25, 30);
        frame.getContentPane().add(lblYPosiziona);
        lblYPosiziona.setVisible(true);

        //creazione e settaggio della casella di testo per la coordinata Y per posizionare una nave
        txtCoordinataYPosiziona = new JTextField();
        txtCoordinataYPosiziona.setBounds(215,564,25,30);
        frame.getContentPane().add(txtCoordinataYPosiziona);
        txtCoordinataYPosiziona.setVisible(true);

        //creazione e settaggio label per indicare la combobox per l'orientamento di una nave
        lblOrientamento = new JLabel("Orientamento:");
        lblOrientamento.setBounds(260, 564, 95, 30);
        frame.getContentPane().add(lblOrientamento);
        lblOrientamento.setVisible(true);

        //creazione e settaggio della combobox per scegliere l'orientamento di una nave
        cmbOrientamento = new JComboBox<>();
        cmbOrientamento.addItem("Orizzontale");
        cmbOrientamento.addItem("Verticale");
        cmbOrientamento.setBounds(355,564,100,30);
        frame.getContentPane().add(cmbOrientamento);
        cmbOrientamento.setVisible(true);

        //creazione e settaggio label per indicare la combobox per il tipo di nave
        lblTipoNave = new JLabel("Tipo nave:");
        lblTipoNave.setBounds(475, 564, 90, 30);
        frame.getContentPane().add(lblTipoNave);
        lblTipoNave.setVisible(true);

        //creazione e settaggio della combobox per scegliere il tipo di nave
        cmbTipoNave = new JComboBox<>();
        cmbTipoNave.addItem("Sommergibile");
        cmbTipoNave.addItem("Torpediniere");
        cmbTipoNave.addItem("Incrociatore");
        cmbTipoNave.addItem("Portaerei");
        cmbTipoNave.setBounds(550,564,110,30);
        frame.getContentPane().add(cmbTipoNave);
        cmbTipoNave.setVisible(true);

        //creazione e settaggio label sotto alle coordinate per posizionare le navi
        lblIndicazioniCoordinatePosiziona = new JLabel("(Coordinate tra 1 e 10)");
        lblIndicazioniCoordinatePosiziona.setBounds(120, 590, 300, 30);
        frame.getContentPane().add(lblIndicazioniCoordinatePosiziona);
        lblIndicazioniCoordinatePosiziona.setVisible(true);

        //creazione e settaggio label sopra per indicare la label dinamica col numero di navi ancora da posizionare
        lblSopraNumNaviDaPosizionare = new JLabel("Hai ancora:");
        lblSopraNumNaviDaPosizionare.setBounds(680, 525, 85, 30);
        frame.getContentPane().add(lblSopraNumNaviDaPosizionare);
        lblSopraNumNaviDaPosizionare.setVisible(true);

        //creazione e pre-set della Label che indica il numero di navi ancora da posizionare nella mappa
        lblNumNavi = new JLabel("7");
        lblNumNavi.setBounds(695, 552, 40, 40);
        lblNumNavi.setHorizontalAlignment(SwingConstants.CENTER);
        fontLblNumNavi = new Font("Helvetica", Font.BOLD, 30);
        lblNumNavi.setFont(fontLblNumNavi);
        frame.getContentPane().add(lblNumNavi);
        lblNumNavi.setVisible(true);

        //creazione e settaggio label sotto per indicare la label dinamica col numero di navi ancora da posizionare
        lblSottoNumNaviDaPosizionare = new JLabel("Navi da piazzare!");
        lblSottoNumNaviDaPosizionare.setBounds(667, 590, 110, 30);
        frame.getContentPane().add(lblSottoNumNaviDaPosizionare);
        lblSottoNumNaviDaPosizionare.setVisible(true);
    }

    /**
     * Il metodo aggiorna la mappa del giocatore inserendo
     * le navi, vengono colorate di grigio le celle dove e'
     * presente la nave
     *
     * @param model viene passato il model
     * @see BattagliaNavaleModel model
     */
    public void updateGrigliaNavi(BattagliaNavaleModel model) {
        Boolean[][] spazioNavi = model.getSpazioNavi();                 //collego lo spazionavi del model con un nuovo spazioNavi locale

        for(int i = 1; i < dim; i++) {
            for (int j = 1; j < dim; j++) {
                if(spazioNavi[i][j]) {
                    campo.aggiungiElemento(i, j);
                }
            }
        }
    }

    /**
     * Il metodo sblocca l'interfaccia dell'utende per
     * sparare
     */
    public void sbloccoSparo() {
        btnSpara.setEnabled(true);
        txtCoordinataXSparo.setEditable(true);
        txtCoordinataYSparo.setEditable(true);
        itemSalvaPartita.setEnabled(true);
    }

    /**
     * Il metodo blocca l'interfaccia dell'utente per
     * sparare
     */
    public void bloccoSparo() {
        btnSpara.setEnabled(false);
        itemSalvaPartita.setEnabled(false);
        txtCoordinataXSparo.setEditable(false);
        txtCoordinataYSparo.setEditable(false);
        itemSalvaPartita.setEnabled(false);
    }

    /**
     * Il metodo blocca l'interfaccia dell'utente per
     * posizionare le navi
     */
    public void bloccoPosizionamento() {
        btnPosiziona.setVisible(false);
        cmbOrientamento.setVisible(false);
        cmbTipoNave.setVisible(false);
        txtCoordinataXPosiziona.setVisible(false);
        txtCoordinataYPosiziona.setVisible(false);
        pnlBlocco.setVisible(true);
    }

    /**
     * Il metodo sblocca l'interfaccia dell'utente per
     * posizionare le navi
     */
    public void sbloccoPosizionamento() {
        btnPosiziona.setVisible(true);
        cmbOrientamento.setVisible(true);
        cmbTipoNave.setVisible(true);
        txtCoordinataXPosiziona.setVisible(true);
        txtCoordinataYPosiziona.setVisible(true);
        pnlBlocco.setVisible(false);
    }

    /**
     * IL metodo aggiorna le mappe inserendo a livello grafico
     * gli spari effettuati
     *
     * @param model viene passato il model
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @see BattagliaNavaleModel model
     */
    public void updateGrigliaSparoColpitoEAffondato(BattagliaNavaleModel model, boolean tipo) {
        giocatore = model.getGiocatore();                       //qui faccio il collegamento all'oggetto giocatore della classe Giocatore che è stato modificato nel model
        cpu = model.getCpu();                                   //qui faccio il collegamento all'oggetto cpu della classe CPU che è stato modificato nel model
        int id;
        id = model.getId();                                     //estrapolo l'id della nave colpita e affondata dal model
        ArrayList<Nave> listaNavi;                              //dichiaro un ArrayList locale sul quale lavorare
        int lunghezzaNave;                                      //variabile locale di tipo intero per inserire la lunghezza della nave
        int lunghezzaListaNavi;                                 //variabile locale di tipo intero che indica la lunghezza degli ArrayList delle navi
        Point[] coordinateNave;                                 //variabile locale di tipo arry di Point per inserire tutte le coordinate di una singola nave
        Point coordinatePuntoNave;                              //variabile locale di tipo Point per salvare ogni singola coppia di coordinate di una nave

        if(tipo) {
            listaNavi = cpu.getNaviSfidante();                  //attribuisco la lista delle navi della cpu all'ArrayList locale
            lunghezzaListaNavi = cpu.getLunghezza();
        } else {
            listaNavi = giocatore.getNaviSfidante();            //attribuisco la lista delle navi del giocatore all'ArrayList locale
            lunghezzaListaNavi = giocatore.getLunghezza();
        }

        for(int i = 0; i < lunghezzaListaNavi; i++) {
            if (id == listaNavi.get(i).getId()) {
                coordinateNave = listaNavi.get(i).getCooNave();         //prendo una nave in mezzo alla lista di tutte le navi
                lunghezzaNave = listaNavi.get(i).getTipoNave();         //qui prendo i valori della lunghezza della nave appena presa e quanti tentativi mancano per affondarla
                for (int j = 0; j < lunghezzaNave; j++) {
                    coordinatePuntoNave = new Point(coordinateNave[j].x, coordinateNave[j].y);
                    if(tipo) {
                        campo2.bersaglioColpitoEAffondato(coordinatePuntoNave);
                    } else {
                        campo.bersaglioColpitoEAffondato(coordinatePuntoNave);
                    }
                }
            }
        }
    }

    /**
     * Il metodo aggiorna a livello grafico uno sparo
     * colpito, ma non affondato
     *
     * @param coordinate vengono passate le coordinate per
     *                   effettuare il controllo sullo sparo
     * @param tipo viene indicato di chi e' il turno (true =
     *             giocatore e false = cpu)
     */
    public void updateGrigliaSparoColpito(Point coordinate, boolean tipo) {
        if(tipo) {
            campo2.bersaglioColpito(coordinate);
        } else {
            campo.bersaglioColpito(coordinate);
        }
    }

    /**
     * Il metodo aggiorna a livello grafico uno sparo mancato
     *
     * @param coordinate vengono passate le coordinate per
     *                   effettuare il controllo sullo sparo
     * @param tipo viene indicato di chi e' il turno (true =
     *             giocatore e false = cpu)
     */
    public void updateGrigliaSparoMancato(Point coordinate, boolean tipo) {
        if(tipo) {
            campo2.aggiungiSparo(coordinate);
        } else {
            campo.aggiungiSparo(coordinate);
        }
    }

    /**
     * Il metodo resetta a livello grafico tutta l'interfaccia
     * utente
     */
    public void reset() {
        txtCoordinataXPosiziona.setText("");
        txtCoordinataYPosiziona.setText("");
        txtCoordinataXSparo.setText("");
        txtCoordinataYSparo.setText("");
        cmbOrientamento.setSelectedIndex(0);
        cmbTipoNave.setSelectedIndex(0);
        lblNumNavi.setText("7");
        this.sbloccoPosizionamento();
        this.bloccoSparo();
        campo.resetGriglia(dim);
        campo.riferimentiGriglia();
        campo2.resetGriglia(dim);
        campo2.riferimentiGriglia();
        lblInfo.setText("INIZIA! Posiziona le navi!");
        lblNickname.setText("Giocatore_1");
    }

    /**
     * Metodo setter della Label lblTime
     *
     * @param tempo viene passata la String modificata
     */
    public void setLblTime(String tempo) {
        lblTime.setText(tempo);
    }

    /**
     * Metodo getter del JTextField txtCoordinataXPosiziona
     *
     * @return txtCoordinataXPosiziona
     */
    public JTextField getTxtCoordinataXPosiziona() {
        return txtCoordinataXPosiziona;
    }

    /**
     * Metodo getter del JTextField txtCoordinataYPosiziona
     *
     * @return txtCoordinataYPosiziona
     */
    public JTextField getTxtCoordinataYPosiziona() {
        return txtCoordinataYPosiziona;
    }

    /**
     * Metodo getter del JComboBox cmbOrientamento
     *
     * @return cmbOrientamento
     */
    public JComboBox<String> getCmbOrientamento() {
        return cmbOrientamento;
    }

    /**
     * Metodo getter del JComboBox cmbTipoNave
     *
     * @return cmbTipoNave
     */
    public JComboBox<String> getCmbTipoNave() {
        return cmbTipoNave;
    }

    /**
     * Metodo getter del JButton btnPosiziona
     *
     * @return btnPosiziona
     */
    public JButton getBtnPosiziona() {
        return btnPosiziona;
    }

    /**
     * Metodo getter del JButton btnSpara
     *
     * @return btnSpara
     */
    public JButton getBtnSpara() {
        return btnSpara;
    }

    /**
     * Metodo getter del JTextField txtCoordinataXSparo
     *
     * @return txtCoordinataXSparo
     */
    public JTextField getCoordinataXSparo() {
        return txtCoordinataXSparo;
    }

    /**
     * Metodo getter del JTextField txtCoordinataYSparo
     *
     * @return txtCoordinataYSparo
     */
    public JTextField getCoordinataYSparo() {
        return txtCoordinataYSparo;
    }

    /**
     * Metodo getter della dimensione massima della mappa
     *
     * @return dim
     */
    public int getDim() {
        return dim;
    }

    /**
     * Metodo getter del JMenuItem itemNuovaPartita
     *
     * @return itemNuovaPartita
     */
    public JMenuItem getItemNuovaPartita() {
        return itemNuovaPartita;
    }

    /**
     * Metodo getter del JMenuItem itemCaricaPartita
     *
     * @return itemCaricaPartita
     */
    public JMenuItem getItemCaricaPartita() {
        return itemCaricaPartita;
    }

    /**
     * Metodo getter del JMenuItem itemSalvaPartita
     *
     * @return itemSalvaPartita
     */
    public JMenuItem getItemSalvaPartita() {
        return itemSalvaPartita;
    }

    /**
     * Metodo getter del JMenuItem itemEsci
     *
     * @return itemEsci
     */
    public JMenuItem getItemEsci() {
        return itemEsci;
    }

    /**
     * Metodo getter del JMenuItem itemIstruzioni
     *
     * @return itemIstruzioni
     */
    public JMenuItem getItemIstruzioni() {
        return itemIstruzioni;
    }

    /**
     * Metodo getter del JMenuItem itemCredits
     *
     * @return itemCredits
     */
    public JMenuItem getItemCredits() {
        return itemCredits;
    }

    /**
     * Metodo setter della JLabel lblNumNavi
     *
     * @param num viene passato il valore modificato
     */
    public void setLblNumNavi(int num) {
        lblNumNavi.setText(String.valueOf(num));
    }

    /**
     * Metodo setter della JLabel lblNickname
     *
     * @param nick viene passata la String modificata
     */
    public void setLblNickname(String nick) {
        lblNickname.setText(nick);
    }

    /**
     * Metodo setter della JLabel lblInfo
     *
     * @param info viene passata la String modificata
     */
    public void setLblInfo(String info) {
        lblInfo.setText(info);
    }
}
