package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class Nave implements Serializable {
    private int tipoNave;                           //indica la tipologia di nave
    private int direzionePrua;                      //indica l'orientamento della nave
    private int colpito;                            //numero di volte che la nave dev'essere colpita per essere affondata
    private int id;                                 //codice identificativo della nave
    private String info;                            //stringa di informazione delle azioni di gioco
    private Point coordinateNave;                   //variabile per copiare le coordinate della nave
    private Point coordinatePosizionamento;         //variabile che serve per posizionare le navi senza eccedere dalla mappa
    private Point[] cooNave;                        //variabile di tipo Point che contiene tutte le coordinate di una singola nave
    private String tipoNaveScritto;                 //indica esattamente che tipo di nave hai inserito

    private final int SOMMERGIBILE = 1;
    private final int TORPEDINIERE = 2;
    private final int INCROCIATORE = 3;
    private final int PORTAEREI = 4;

    /**
     * Il costruttore nave provvede alla costruzione
     * appropriata dell'oggetto nave, ovvero, ridefinendo
     * alcuni parametri ricevuti in ingresso e definendo
     * quante volte la nave deve essere colpita per far si'
     * che affondi
     *
     * @param coordinate vengono passate le coordinate di dove
     *                   si vuole piazzare la prua della nave
     * @param orientamento viene indicato l'orientamento della
     *                     nave
     * @param tipologia viene indicata la tipologia di nave
     *                  piazzata
     * @param id viene passato l'identificativo della nave
     */
    public Nave(Point coordinate, int orientamento, int tipologia, int id) {
        this.coordinateNave = new Point(coordinate.x, coordinate.y);
        this.id = id;
        switch (tipologia) {
            case 0:
                tipoNave = SOMMERGIBILE;
                tipoNaveScritto = "Sommergibile";
                break;
            case 1:
                tipoNave = TORPEDINIERE;
                tipoNaveScritto = "Torpediniere";
                break;
            case 2:
                tipoNave = INCROCIATORE;
                tipoNaveScritto = "Incrociatore";
                break;
            case 3:
                tipoNave = PORTAEREI;
                tipoNaveScritto = "Portaerei";
                break;
            default:
                break;
        }
        this.colpito = tipoNave;    //il numero di volte che la nave deve essere colpita per essere affondata è pari alla sua lunghezza
        switch (orientamento) {
            case 0:
                direzionePrua = 0;  //prua orientata orizzontalmente
                break;
            case 1:
                direzionePrua = 1;  //prua orientata verticalmente
                break;
            default:
                break;
        }
        controlloEccedenzaOrientamento(coordinateNave, direzionePrua, tipoNave);
    }

    /**
     * Il metodo controlla se con le coordinate inserite per
     * posizionare la nave, quest'ultima eccede dalla mappa
     * e in automatico ne ridefinisce le coordinate
     *
     * @param coordinate vengono passate le coordinate di dove
     *                   si vuole piazzare la prua della nave
     * @param orientamentoPrua viene indicato l'orientamento della
     *                         prua della nave
     * @param tipoNave viene indicata la tipologia precedentemente
     *                 ridefinita di nave piazzata
     */
    public void controlloEccedenzaOrientamento(Point coordinate, int orientamentoPrua, int tipoNave) {
        coordinatePosizionamento = new Point(coordinate.x, coordinate.y);

        if(orientamentoPrua == 0) {
            if(tipoNave == PORTAEREI && coordinate.y > 7) {
                coordinatePosizionamento.y = 7;
            }
            if(tipoNave == INCROCIATORE && coordinate.y > 8) {
                coordinatePosizionamento.y = 8;
            }
            if(tipoNave == TORPEDINIERE && coordinate.y > 9) {
                coordinatePosizionamento.y = 9;
            }
            if(tipoNave == SOMMERGIBILE) {
                coordinatePosizionamento.y = coordinate.y;
            }
        }

        if(orientamentoPrua == 1) {
            if(tipoNave == PORTAEREI && coordinate.x > 7) {
                coordinatePosizionamento.x = 7;
            }
            if(tipoNave == INCROCIATORE && coordinate.x > 8) {
                coordinatePosizionamento.x = 8;
            }
            if(tipoNave == TORPEDINIERE && coordinate.x > 9) {
                coordinatePosizionamento.x = 9;
            }
            if(tipoNave == SOMMERGIBILE) {
                coordinatePosizionamento.x = coordinate.x;
            }
        }
    }

    /**
     * Il metodo aggiorna il display dicendo che
     * e' stata posizionata una nave
     */
    public void navePosizionata() {
        info = "Hai posizionato un " + tipoNaveScritto + "!";
    }

    /**
     * Metodo getter dell'array di point cooNave
     *
     * @return cooNave
     */
    public Point[] getCooNave() {
        return cooNave;
    }

    /**
     * Metodo setter dell'array di Point cooNave
     *
     * @param cooNave viene passato l'array modificato
     */
    public void setCooNave(Point[] cooNave) {
        this.cooNave = cooNave;
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
     * Metodo getter del valore che indica la
     * tipologia di nave tipoNave
     *
     * @return tipoNave
     */
    public int getTipoNave() {
        return tipoNave;
    }

    /**
     * Metodo getter del valore che indica la
     * direzione della prua direzionePrua
     *
     * @return direzionePrua
     */
    public int getDirezionePrua() {
        return direzionePrua;
    }

    /**
     * Metodo getter del valore che indica il numero
     * di tentativi che mancano per affondare la nave
     *
     * @return colpito
     */
    public int getColpito() {
        return colpito;
    }

    /**
     * Metodo setter dell'array di Point cooNave
     *
     * @param colpito viene passato il valore modificato
     */
    public void setColpito(int colpito) {
        this.colpito = colpito;
    }

    /**
     * Metodo getter del valore che indica l'id
     * di una nave
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo getter del Point coordinatePosizionamento
     *
     * @return coordinatePosizionamento
     */
    public Point getCoordinatePosizionamento() {
        return coordinatePosizionamento;
    }
}
