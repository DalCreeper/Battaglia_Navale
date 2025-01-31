package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class BattagliaNavaleModel {
    private Giocatore giocatore;            //collegamento all'estensione del model (giocatore)
    private CPU cpu;                        //collegamento all'estensione del model (cpu)
    private String info;                    //stringa di informazione delle azioni di gioco
    private Boolean[][] spazioNavi;         //griglia booleana per capire dove sono posizionate le navi e per posizionarle
    private Boolean[][] spazioSparo;        //griglia booleana per capire dove l'utente spara
    private Boolean[][] spazioNaviCpu;      //griglia booleana per capire dove sono posizionate le navi della CPU e per posizionarle
    private Boolean[][] spazioSparoCpu;     //griglia booleana per capire dove la CPU spara
    private Point[] cooNave;                //array di Point che memorizza tutte le coordinate di una nave appena inserita
    private int id;                         //id di una nave colpita o colpita e affondata
    private int idColpito;                  //id di una nave colpita ma non affondata

    /**
     * Il costruttore del modello costruisce gli oggetti giocatore e cpu
     */
    public BattagliaNavaleModel() {
        giocatore = new Giocatore();    //oggetto giocatore della classe Giocatore
        cpu = new CPU();                //oggetto cpu della classe CPU
        id = 0;                         //inizializzo la variabile id che indica una nave colpita
        idColpito = 0;                  //inizializzo la variabile idColpito che indica una nave colpita ma non affondata
    }

    /**
     * Il metodo inizializza le griglie booleane delle navi e degli
     * spari a false
     *
     * @param dim viene data la dimensione massima delle griglie
     */
    public void inizializzaSpazionaveSpaziosparo(int dim) {
        spazioNavi = new Boolean[dim][dim];
        spazioSparo = new Boolean[dim][dim];
        spazioNaviCpu = new Boolean[dim][dim];
        spazioSparoCpu = new Boolean[dim][dim];

        //inizializzo tutte le mappe a false
        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioNavi[i][j] = false;
                spazioSparo[i][j] = false;
                spazioNaviCpu[i][j] = false;
                spazioSparoCpu[i][j] = false;
            }
        }
    }

    /**
     * Il metodo inizializza le griglie booleane degli spari a false
     *
     * @param dim viene data la dimensione massima delle griglie
     */
    public void inizializzaSpaziosparo(int dim) {
        spazioSparo = new Boolean[dim][dim];
        spazioSparoCpu = new Boolean[dim][dim];

        //inizializzo tutte la mappe a false
        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparo[i][j] = false;
                spazioSparoCpu[i][j] = false;
            }
        }
    }

    /**
     * Il metodo controlla se ha vinto la cpu o il giocatore
     *
     * @return 0 ritorna praticamente una negazione, cioe' che non
     *           ha ancora vinto nessuno
     *         1 ritorna la vittoria del giocatore
     *         2 ritorna la vittoria della cpu
     */
    public int controlloPartita() {
        ArrayList<Nave> naviGiocatore;
        ArrayList<Nave> naviCpu;
        int colpito;
        int contGiocatore = 0;
        int contCpu = 0;
        naviGiocatore = giocatore.getNaviSfidante();
        naviCpu = cpu.getNaviSfidante();

        for(int z = 0; z < giocatore.getLunghezza(); z++) {
            colpito = naviGiocatore.get(z).getColpito();
            if(colpito == 0) {
                contGiocatore++;
            }
        }
        for(int z = 0; z < cpu.getLunghezza(); z++) {
            colpito = naviCpu.get(z).getColpito();
            if(colpito == 0) {
                contCpu++;
            }
        }
        if(contGiocatore == 7) {
            return 2;
        } else if(contCpu == 7) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Il metodo inserisce nella griglia booleana degli spari il valore
     * a true, che indica la zona della mappa dove e' stato effettuato
     * lo sparo
     *
     * @param coordinate vengono date le coordinate dove inserire lo sparo
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param dim viene data la dimensione massima delle griglie
     */
    public void inserisciSparo(Point coordinate, boolean tipo, int dim) {
        Boolean[][] spazioSparoGenerico;                     //creo una matrice di Boolean locale per gli spazisparo
        if(tipo) {
            spazioSparoGenerico = spazioSparo;
        } else {
            spazioSparoGenerico = spazioSparoCpu;
        }

        for(int i = 1; i < dim; i++) {
            for (int j = 1; j < dim; j++) {
                if ((i == coordinate.x) && (j == coordinate.y)) {
                    spazioSparoGenerico[i][j] = true;
                }
            }
        }
    }

    /**
     * Il metodo controlla se lo sparo che si vuole inserire
     * e' gia' presente nella griglia degli spari
     *
     * @param coordinate vengono date le coordinate dove inserire lo sparo
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param dim viene data la dimensione massima delle griglie
     * @return true ritorna se lo sparo e' presente
     *         false ritorna se lo sparo non e' presente
     */
    public boolean controllaSparoPresente(Point coordinate, boolean tipo, int dim) {
        Boolean[][] spazioSparoGenerico;                     //creo una matrice di Boolean locale per gli spazisparo
        if(tipo) {
            spazioSparoGenerico = spazioSparo;
        } else {
            spazioSparoGenerico = spazioSparoCpu;
        }
        for(int i = 1; i < dim; i++) {
            for (int j = 1; j < dim; j++) {
                if(spazioSparoGenerico[i][j]) {
                    if ((i == coordinate.x) && (j == coordinate.y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Il metodo piazza una nave nella griglia booleana delle navi e
     * carica in un array di Point tutte le coordinate della singola nave,
     * poi aggiorna nell'oggetto Nave l'array con tutte le sue coordinate
     *
     * @param n viene passata la nave appena creata
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param dim viene data la dimensione massima delle griglie
     */
    public void piazzaNaveNellaMappa(Nave n, boolean tipo, int dim) {
        int x = n.getCoordinatePosizionamento().x;      //variabile che estrapola la coordinata X della prua della nave
        int y = n.getCoordinatePosizionamento().y;      //variabile che estrapola la coordinata Y della prua della nave
        int orientamento = n.getDirezionePrua();        //variabile che estrapola l'orientamento della nave
        int lunghezzaNave = n.getTipoNave();            //variabile che estrapola la lunghezza della nave
        int cont = 0;                                   //contatore per controllare che non vengano incrementate le coordinate più della lunghezza della nave
        int z = 0;                                      //contatore dell'array di Point per caricare tutte le coordinate di 1 nave
        cooNave = new Point [lunghezzaNave];            //array di point che contiene tutte le coordinate di una singola nave

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                if((i == x) && (j == y)) {
                    if(cont < lunghezzaNave) {
                        if(tipo) {
                            spazioNavi[i][j] = true;
                        } else {
                            spazioNaviCpu[i][j] = true;
                        }
                        cooNave[z] = new Point(x, y);
                        if(orientamento == 0) {
                            y++;
                        }
                        if(orientamento == 1) {
                            x++;
                        }
                        cont++;
                        z++;
                    }
                }
            }
        }
        n.setCooNave(cooNave);
    }

    /**
     * Il metodo controlla se lo sparo appena effettuato ha colpito
     * una nave e distingue tutti i casi
     *
     * @param coordinate vengono passate le coordinate per effettuare
     *                   il controllo sullo sparo
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param dim viene data la dimensione massima delle griglie
     * @return 0 ritorna se il giocatore ha colpito e affondato una nave
     *         1 ritorna se il giocatore ha colpito ma <b>NON</b> affondato una nave
     *         2 ritorna se il giocatore ha mancato il bersaglio
     *         3 ritorna se la cpu ha colpito e affondato una nave
     *         4 ritorna se la cpu ha colpito ma <b>NON</b> affondato una nave
     *         5 ritorna se la cpu ha mancato il bersaglio
     */
    public int controlloSparo(Point coordinate, boolean tipo, int dim) {
        ArrayList<Nave> listaNavi;                  //dichiaro un ArrayList locale sul quale lavorare
        Boolean[][] spazioNaviGenerico;             //creo una matrice di Boolean locale per gli spazioNavi
        int lunghezzaNave;                          //variabile locale di tipo intero per inserire la lunghezza della nave
        int lunghezzaListaNavi;                     //variabile locale di tipo intero che indica la lunghezza degli ArrayList delle navi
        int colpito;                                //variabile locale di tipo intero per inserire il numero di colpi che mancano per affondare la nave
        Point[] cooControllo;                       //variabile locale di tipo arry di Point per inserire tutte le coordinate di una singola nave
        id = 0;                                     //id di una singola nave

        if(tipo) {
            listaNavi = cpu.getNaviSfidante();         //attribuisco la lista delle navi della cpu all'ArrayList locale
            spazioNaviGenerico = spazioNaviCpu;
            lunghezzaListaNavi = cpu.getLunghezza();
        } else {
            listaNavi = giocatore.getNaviSfidante();   //attribuisco la lista delle navi del giocatore all'ArrayList locale
            spazioNaviGenerico = spazioNavi;
            lunghezzaListaNavi = giocatore.getLunghezza();
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                if(spazioNaviGenerico[i][j]) {
                    if((i == coordinate.x) && (j == coordinate.y)) {
                        for(int z = 0; z < lunghezzaListaNavi; z++) {     //ciclo la lista con tutte le navi
                            cooControllo = listaNavi.get(z).getCooNave();       //prendo una nave in mezzo alla lista di tutte le navi
                            lunghezzaNave = listaNavi.get(z).getTipoNave();     //qui prendo i valori della lunghezza della nave appena presa

                            //qui controllo se effettivamente ho colpito una delle navi tra le 7 inserite e modifico lo stato di "colpito"
                            for(int k = 0; k < lunghezzaNave; k++) {
                                if ((cooControllo[k].x == coordinate.x) && (cooControllo[k].y == coordinate.y)) {
                                    colpito = listaNavi.get(z).getColpito();
                                    if(!tipo) {
                                        idColpito = listaNavi.get(z).getId();       //idColpito serve a memorizzare l'id di una nave che viene colpita dalla cpu (ma non ancora affondata)
                                    }
                                    if(colpito == 1) {
                                        listaNavi.get(z).setColpito(colpito - 1);
                                        id = listaNavi.get(z).getId();
                                        if(!tipo) {
                                            idColpito = 0;       //idColpito viene resettato a 0 se la nave viene colpita e affondata così nel suo cervello non la prenderà più in considerazione
                                        }
                                        if(tipo) {
                                            return 0;   //se il giocatore colpisce e affonda una nave
                                        } else {
                                            return 3;   //se la cpu colpisce e affonda una nave
                                        }
                                    } else {
                                        listaNavi.get(z).setColpito(colpito - 1);
                                        if(tipo) {
                                            return 1;   //se il giocatore colpisce una nave ma non l'affonda
                                        } else {
                                            return 4;   //se la cpu colpisce una nave ma non l'affonda
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(tipo) {
            return 2;   //se il giocatore manca il bersaglio (acqua)
        } else {
            return 5;   //se la cpu manca il bersaglio (acqua)
        }
    }

    /**
     * Il metodo aggiorna il display indicando chi ha effettuato
     * lo sparo e cosa e' stato colpito, inoltre ogni volta che viene
     * effettuato uno sparo, quest'ultimo viene inserito nel rispettivo
     * array contenente tutti gli spari effettuati
     *
     * @param coordinate vengono passate le coordinate per effettuare
     *                   il controllo sullo sparo
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param dim dim viene data la dimensione massima delle griglie
     * @return 0 ritorna se come condizione di dafault, per avere sempre
     *           un return disponibile
     *         1 ritorna se il colpo sparato colpisce e affonda una nave
     *         2 ritorna se il colpo sparato colpisce ma <b>NON</b> affonda una nave
     *         3 ritorna se il colpo sparato manca il bersaglio
     */
    public int spara(Point coordinate, boolean tipo, int dim) {
        int control;
        control = controlloSparo(coordinate, tipo, dim);
        if(control == 0) {
            info = "Hai sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + " Hai colpito e AFFONDATO il bersaglio!";
            giocatore.aggiungiSparo(coordinate);
            return 1;
        } else if(control == 1) {
            info = "Hai sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + " Hai colpito il bersaglio!";
            giocatore.aggiungiSparo(coordinate);
            return 2;
        } else if(control == 2) {
            info = "Hai sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + " Acqua!";
            giocatore.aggiungiSparo(coordinate);
            return 3;
        } else if(control == 3) {
            info = "La CPU ha sparato alle coordinate: " + "X = " + coordinate.x + " Y = " + coordinate.y + " Ha colpito e AFFONDATO il bersaglio!";
            cpu.aggiungiSparo(coordinate);
            return 1;
        } else if(control == 4) {
            info = "La CPU ha sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + " Ha colpito il bersaglio!";
            cpu.aggiungiSparo(coordinate);
            return 2;
        } else if(control == 5) {
            info = "La CPU ha sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + " Acqua!";
            cpu.aggiungiSparo(coordinate);
            return 3;
        } else {
            return 0;
        }
    }

    /**
     * Metodo getter della variabile idColpito
     *
     * @return idColpito
     */
    public int getIdColpito() {
        return idColpito;
    }

    /**
     * Metodo setter del valore idColpito
     * <p>
     * Questo metodo setter serve solo a livello di test,
     * non verra' mai chiamato nel programma
     * </p>
     *
     * @param idColpito viene passato il valore modificato
     */
    public void setIdColpito(int idColpito) {
        this.idColpito = idColpito;
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
     * Metodo getter dell'oggetto giocatore
     *
     * @return giocatore
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     * Metodo getter dell'oggetto cpu
     *
     * @return cpu
     */
    public CPU getCpu() {
        return cpu;
    }

    /**
     * Metodo getter della griglia spazioNavi
     *
     * @return spazionavi
     */
    public Boolean[][] getSpazioNavi() {
        return spazioNavi;
    }

    /**
     * Metodo setter della griglia spazioNavi
     * <p>
     * La griglia viene aggiornata ogni volta che viene creata
     * una nuova partita o ne viene caricata una
     * </p>
     *
     * @param spazioNavi viene passata la griglia modificata
     */
    public void setSpazioNavi(Boolean[][] spazioNavi) {
        this.spazioNavi = spazioNavi;
    }

    /**
     * Metodo getter della griglia spazioSparo
     * <p>
     * Questo metodo setter serve solo a livello di test,
     * non verra' mai chiamato nel programma
     * </p>
     *
     * @return spazioSparo
     */
    public Boolean[][] getSpazioSparo() {
        return spazioSparo;
    }

    /**
     * Metodo setter della griglia spazioSparo
     * <p>
     * La griglia viene aggiornata ogni volta che viene creata
     * una nuova partita
     * </p>
     *
     * @param spazioSparo viene passata la griglia modificata
     */
    public void setSpaziosparo(Boolean[][] spazioSparo) {
        this.spazioSparo = spazioSparo;
    }

    /**
     * Metodo getter della griglia spazioNaviCpu
     *
     * @return spazioNaviCpu
     */
    public Boolean[][] getSpazioNaviCpu() {
        return spazioNaviCpu;
    }

    /**
     * Metodo setter della griglia spazioNaviCpu
     * <p>
     * La griglia viene aggiornata ogni volta che viene creata
     * una nuova partita o ne viene caricata una
     * </p>
     *
     * @param spazioNaviCpu viene passata la griglia modificata
     */
    public void setSpazioNaviCpu(Boolean[][] spazioNaviCpu) {
        this.spazioNaviCpu = spazioNaviCpu;
    }

    /**
     * Metodo getter della griglia spazioSparoCpu
     *
     * @return spazioSparoCpu
     */
    public Boolean[][] getSpazioSparoCpu() {
        return spazioSparoCpu;
    }

    /**
     * Metodo setter della griglia spazioSparoCpu
     * <p>
     * La griglia viene aggiornata ogni volta che viene creata
     * una nuova partita
     * </p>
     *
     * @param spazioSparoCpu viene passata la griglia modificata
     */
    public void setSpazioSparoCpu(Boolean[][] spazioSparoCpu) {
        this.spazioSparoCpu = spazioSparoCpu;
    }

    /**
     * Metodo getter dell'array di Point cooNave
     *
     * @return cooNave
     */
    public Point[] getCooNave() {
        return cooNave;
    }

    /**
     * Il metodo aggiorna il display dicendo che lo sparo effettuato
     * e' gia' presente
     *
     * @param coordinate vengono passate le coordinate per essere
     *                   stampate a video
     */
    public void errorSparoPresente(Point coordinate) {
        info = "Ops.. Hai già sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + "!";
    }

    /**
     * Il metodo aggiorna il display dicendo la nave non puo' essere
     * posizionata, perche' troppo vicina ad un 'altra
     */
    public void errorNaveVicina() {
        info = "Ops.. Stai cercando di posizionare la nave troppo vicino ad un'altra!";
    }

    /**
     * Il metodo aggiorna il display dicendo che il giocatore ha vinto
     */
    public void vittoriaGiocatore() {
        info = "CONGRATULAZIONI!!! HAI VINTO!!! La CPU sta piangendo ora MUAHAHAH!!!";
    }

    /**
     * Il metodo aggiorna il display dicendo che la cpu ha vinto
     */
    public void vittoriaCpu() {
        info = "Hai perso miseramente contro la CPU.. Ci hai provato..";
    }

    /**
     * Il metodo aggiorna il display dicendo che la cpu ha vinto perche'
     * al giocatore e' scaduto il tempo per effettuare il turno
     */
    public void vittoriaCpuTempo() {
        info = "Hai perso contro la CPU.. Ci hai impiegato troppo tempo..";
    }

    /**
     * Il metodo aggiorna il display dicendo che la nave non puo' essere
     * posizionata perchè ve ne e' una in quelle coordinate
     *
     * @param coordinate vengono passate le coordinate per
     *                   essere stampate a video
     */
    public void errorNavePresente(Point coordinate) {
        info = "Ops.. Hai già posizionato una nave alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + "!";
    }

    /**
     * Il metodo aggiorna il display dicendo che la cpu sta elaborando
     */
    public void pensa() {
        info = "La CPU sta pensando dove sparare...";
    }

    /**
     * Il metodo aggiorna il display dicendo di riprovare, perche'
     * si e' verificato un errore
     */
    public void error() {
        info = "Ops.. Riprova!";
    }

    /**
     * Metodo getter della String info
     *
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Metodo setter della String info
     *
     * @param info viene passata la String modificata
     */
    public void setInfo(String info) {
        this.info = info;
    }
}
