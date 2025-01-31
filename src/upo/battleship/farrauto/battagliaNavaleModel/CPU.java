package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class CPU extends Sfidanti {
    private Giocatore giocatore;                                        //collegamento all'estensione del model (giocatore)
    private ControlliSulleNaviPiazzate controlliNavi;                   //collegamento all'estensione del model (controlliSulleNaviPiazzate)
    private Nave nave;                                                  //collegamento all'estensione del model (nave)
    private Random rand;                                                //variabile della classe Rand
    private int valore;                                                 //risultato della funzione di randomize
    private int lunghezzaNave;                                          //indica la lunghezza di una nave che è uguale al suo tipo
    private int id;                                                     //indica l'id di una nave della cpu
    private int orientamento;                                           //indica l'orientamento di una nave
    private int lunghezza;                                              //indica la lunghezza dell'ArrayList naviSfidante
    private ArrayList<Nave> naviGiocatore;                              //ArrayList che contiene tutte le navi del giocatore
    private Point coordinateNave;                                       //indica le coordinate dove la cpu vuole piazzare una delle sue navi
    private Point coordinateFinali;                                     //indica le coordinate dove verrà effettivamente piazzata una delle navi della cpu

    private final static int MAX = 10;                                  //indica il valore massimo che può essere generato dalla funzione di randomize per creare delle coordinate
    private final static int MIN = 1;                                   //indica il valore minimo che può essere generato dalla funzione di randomize per creare delle coordinate

    /**
     * Il costruttore della cpu costruisce le sue 2 liste,
     * la lista contenente le navi della cpu e la lista
     * contenente gli spari della cpu
     *
     * @see Sfidanti sfidanti
     */
    public CPU() {
        naviSfidante = new ArrayList<>(10);
        spariSfidante = new ArrayList<>();
        id = 0;                                                         //inizializzo la variabile id delle navi della cpu a 0
        coordinateFinali = new Point(0,0);                        //inizializzo le coordinate finali degli spari pensati della cpu a 0
    }

    /**
     * Il metodo estrapola dalla classe Giocatore la lista
     * contenente le navi del giocatore, crea e piazza nella
     * sua mappa tante navi quante ne ha piazzate il giocatore,
     * dello stesso tipo, ma con orientamento casuale e in punti
     * casuali
     *
     * @param model viene passato il model
     * @param dim dim viene data la dimensione massima delle griglie
     * @see BattagliaNavaleModel model
     * @see Sfidanti sfidanti
     */
    public void estrapolaNavi(BattagliaNavaleModel model, int dim) {
        giocatore = model.getGiocatore();                               //estrapolo l'oggetto giocatore dal model
        controlliNavi = new ControlliSulleNaviPiazzate();               //oggetto controlliNavi della classe ControlliSulleNaviPiazzate
        int maxOr = 1;                                                  //indica il valore massimo che può essere generato dalla funzione di randomize per creare l'orientamento
        int minOr = 0;                                                  //indica il valore minimo che può essere generato dalla funzione di randomize per creare l'orientamento
        int cont;                                                       //inizializzo il contatore degli errori
        naviGiocatore = giocatore.getNaviSfidante();                    //estrapolo le navi del giocatore dalla sua classe

        for(int z = 0; z < giocatore.getLunghezza(); z++) {     //ciclo la lista con tutte le navi (10 totali) del giocatore
            lunghezzaNave = naviGiocatore.get(z).getTipoNave();     //qui prendo i valori della lunghezza della nave appena presa
            orientamento = this.randomize(maxOr, minOr);
            do {
                cont = 0;
                coordinateNave = this.generaCoordinate();
                if (controlliNavi.controlloNaviPosizionate(coordinateNave, false, model, dim)) {
                    cont++;
                }
                if (controlliNavi.controlloNaviVicine(coordinateNave, false, orientamento, lunghezzaNave, model, dim)) {
                    cont++;
                }
            } while (cont > 0);
            id++;
            nave = new Nave(coordinateNave, orientamento, lunghezzaNave - 1, id);
            this.aggiungiNave(nave);
            model.piazzaNaveNellaMappa(nave,false, dim);
        }
    }

    /**
     * Il metodo controlla se la cpu col suo sparo, ha colpito una
     * nave del giocatore e se cosi' e' stato, genera delle
     * coordinate apposite richiamando il metodo pensa()
     *
     * @param model viene passato il model
     * @param dim dim viene data la dimensione massima delle griglie
     * @see BattagliaNavaleModel model
     * @see Sfidanti sfidanti
     * @return true ritorna se ha generato delle coordinate pensate
     *         false ritorna se decide di sparare un colpo casuale
     */
    public boolean cervello(BattagliaNavaleModel model, int dim) {
        Boolean[][] spazioSparo = model.getSpazioSparoCpu();
        Boolean[][] spazioNavi = model.getSpazioNavi();
        Point[] cooControllo;
        int colpito;
        int orienta = 2;
        int lunghezza;
        int idColpito = model.getIdColpito();
        Point coordinate;

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                if(spazioSparo[i][j]) {
                    if(spazioNavi[i][j]) {
                        for(int z = 0; z < naviSfidante.size(); z++) {     //ciclo la lista con tutte le navi (7 totali) del giocatore
                            colpito = naviGiocatore.get(z).getColpito();            //estrapolo il numero di tentativi che ci vogliono ancora per affondare una nave del giocatore
                            lunghezza = naviGiocatore.get(z).getTipoNave();         //estrapolo la lunghezza di una nave del giocatore
                            cooControllo = naviGiocatore.get(z).getCooNave();       //prendo una nave in mezzo alla lista di tutte le navi
                            if(colpito != 0) {
                                if(colpito != lunghezza) {
                                    if(colpito < lunghezza - 1) {
                                        orienta = naviGiocatore.get(z).getDirezionePrua();      //estrapolo il valore che indica l'orientamento di una nave del giocatore
                                    }
                                }
                                if(idColpito == naviGiocatore.get(z).getId()) {
                                    for(int k = 0; k < lunghezza; k++) {
                                        coordinate = new Point(cooControllo[k].x, cooControllo[k].y);
                                        if(coordinate.x == i && coordinate.y == j) {
                                            coordinateFinali = new Point(pensa(coordinate, orienta, model, dim));
                                            if(!(model.controllaSparoPresente(coordinateFinali, false, dim))) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Il metodo genera delle coordinate intorno al punto della nave
     * colpito, in base anche all'orientamento se ha colpito almeno
     * 2 punti della stessa nave
     *
     * @param coordinateControllo vengono passate le coordinate del
     *                            punto dove e' stata colpita la nave
     * @param orienta viene passato l'orientamento della nave
     * @param model viene passato il model
     * @param dim dim viene data la dimensione massima delle griglie
     * @see BattagliaNavaleModel model
     * @see Sfidanti sfidanti
     * @return coordinatePensate ritona il punto pensato dove sparare
     */
    public Point pensa(Point coordinateControllo, int orienta, BattagliaNavaleModel model, int dim) {
        int random;
        int cont;
        int x;
        int y;
        Point coordinatePensate;
        boolean destra = false;
        boolean sinistra = false;

        do {
            coordinatePensate = new Point(coordinateControllo.x, coordinateControllo.y);
            x = (int) coordinatePensate.getX();
            y = (int) coordinatePensate.getY();
            cont = 0;
            if(orienta == 2) {
                random = this.randomize(4, 1);
                if(random == 1) {
                    x++;
                } else if(random == 2) {
                    y++;
                } else if(random == 3) {
                    x--;
                } else if(random == 4) {
                    y--;
                }
                //System.out.println("x = " + x + " y = " + y);
            } else if(orienta == 0) {
                random = this.randomize(2, 1);
                if(random == 1) {
                    y++;
                    destra = true;
                } else if(random == 2) {
                    y--;
                    sinistra = true;
                }
                //System.out.println("x = " + x + " y = " + y);
                if(destra && sinistra) {
                    if((x < 1) || (y < 1) || (x > 10) || (y > 10)) {
                        cont++;
                    }
                    coordinatePensate = new Point(x, y);
                    if(model.controllaSparoPresente(coordinatePensate,false, dim)) {
                        cont++;
                    }
                    if(cont > 0) {
                        coordinatePensate = new Point(coordinateControllo.x, coordinateControllo.y);
                        break;
                    } else {
                        break;
                    }
                }
            } else if(orienta == 1) {
                random = this.randomize(2, 1);
                if(random == 1) {
                    x++;
                    destra = true;
                } else if(random == 2) {
                    x--;
                    sinistra = true;
                }
                System.out.println("x = " + x + " y = " + y);
                if(destra && sinistra) {
                    if((x < 1) || (y < 1) || (x > 10) || (y > 10)) {
                        cont++;
                    }
                    coordinatePensate = new Point(x, y);
                    if(model.controllaSparoPresente(coordinatePensate,false, dim)) {
                        cont++;
                    }
                    if(cont > 0) {
                        coordinatePensate = new Point(coordinateControllo.x, coordinateControllo.y);
                        break;
                    } else {
                        break;
                    }
                }
            }
            if((x < 1) || (y < 1) || (x > 10) || (y > 10)) {
                cont++;
            }
            coordinatePensate = new Point(x, y);
            if(model.controllaSparoPresente(coordinatePensate,false, dim)) {
                cont++;
            }
        } while(cont > 0);
        return coordinatePensate;
    }

    /**
     * Il metodo genera delle coordinate randomiche
     *
     * @return coordinate ritorna un punto randomico
     */
    public Point generaCoordinate() {
        Point coordinate;
        coordinate = new Point((this.randomize(MAX, MIN)), (this.randomize(MAX, MIN)));
        return coordinate;
    }

    /**
     * Il metodo aggiunge una nave alla lista delle navi
     * della cpu
     *
     * @param n viene passata la nave appena creata
     * @see Sfidanti sfidanti
     */
    public void aggiungiNave(Nave n) {
        naviSfidante.add(n);
        this.aggiornaLunghezza();
    }

    /**
     * Il metodo setta la la variabile lunghezza
     *
     * @see Sfidanti sfidanti
     */
    public void aggiornaLunghezza() {
        lunghezza = naviSfidante.size();
    }

    /**
     * Il metodo aggiunge uno sparo alla lista degli spari
     * della cpu
     *
     * @param sparo viene passato lo sparo appena effettuato
     * @see Sfidanti sfidanti
     */
    public void aggiungiSparo(Point sparo) {
        spariSfidante.add(sparo);
    }

    /**
     * Il metodo genera un valore int casuale compreso
     * tra il valore min e max indicato
     *
     * @param max viene indicato il numero massimo che puo'
     *            assumere il valore
     * @param min viene indicato il numero minimo che puo'
     *            assumere il valore
     * @return valore ritorna un numero randomico
     */
    public int randomize(int max, int min) {
        rand = new Random();
        valore = rand.nextInt((max - min) + 1) + min;
        return valore;
    }

    /**
     * Metodo getter del Point coordinateFinali
     *
     * @return coordinateFinali
     */
    public Point getCoordinateFinali() {
        return coordinateFinali;
    }

    /**
     * Metodo getter del valore della lunghezza
     *
     * @return lunghezza
     */
    public int getLunghezza() {
        return lunghezza;
    }

    /**
     * Metodo getter dell'ArrayList naviSfidante
     *
     * @see Sfidanti sfidanti
     * @return naviSfidante
     */
    public ArrayList<Nave> getNaviSfidante() {
        return naviSfidante;
    }

    /**
     * Metodo setter dell'ArrayList naviSfidante
     *
     * @see Sfidanti sfidanti
     * @param naviSfidante viene passato l'ArrayList modificato
     */
    public void setNaviSfidante(ArrayList<Nave> naviSfidante) {
        this.naviSfidante = naviSfidante;
    }

    /**
     * Metodo getter dell'ArrayList naviGiocatore
     *
     * @return naviGiocatore
     */
    public ArrayList<Nave> getNaviGiocatore() {
        return naviGiocatore;
    }

    /**
     * Metodo setter dell'ArrayList naviGiocatore
     *
     * @param naviGiocatore viene passato l'ArrayList modificato
     */
    public void setNaviGiocatore(ArrayList<Nave> naviGiocatore) {
        this.naviGiocatore = naviGiocatore;
    }

    /**
     * Metodo getter dell'ArrayList spariSfidante
     *
     * @see Sfidanti sfidanti
     * @return spariSfidante
     */
    public ArrayList<Point> getSpariSfidante() {
        return spariSfidante;
    }

    /**
     * Metodo setter dell'ArrayList spariSfidante
     *
     * @see Sfidanti sfidanti
     * @param spariSfidante viene passato l'ArrayList modificato
     */
    public void setSpariSfidante(ArrayList<Point> spariSfidante) {
        this.spariSfidante = spariSfidante;
    }
}