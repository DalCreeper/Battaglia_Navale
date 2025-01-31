package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class SalvaCaricaDati implements Serializable {
    private ArrayList<Nave> naviGiocatore;      //ArrayList che contiene tutte le navi del giocatore
    private ArrayList<Nave> naviCpu;            //ArrayList che contiene tutte le navi della CPU
    private ArrayList<Point> spariGiocatore;    //ArrayList che contiene tutti gli spari effettuati dal giocatore
    private ArrayList<Point> spariCpu;          //ArrayList che contiene tutti gli spari effettuati dalla CPU
    private String info;                        //stringa di informazione delle azioni di gioco
    private Boolean[][] spazioNavi;             //griglia booleana per capire dove sono posizionate le navi e per posizionarle
    private Boolean[][] spazioNaviCpu;          //griglia booleana per capire dove sono posizionate le navi della CPU e per posizionarle
    private long startTimeSave;                 //valore salvato dello stato del timer, indica il tempo rimanente del timer

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
     * @param naviGiocatore viene passato ll'ArrayList modificato
     */
    public void setNaviGiocatore(ArrayList<Nave> naviGiocatore) {
        this.naviGiocatore = naviGiocatore;
    }

    /**
     * Metodo getter dell'ArrayList naviCpu
     *
     * @return naviCpu
     */
    public ArrayList<Nave> getNaviCpu() {
        return naviCpu;
    }

    /**
     * Metodo setter dell'ArrayList naviCpu
     *
     * @param naviCpu viene passato ll'ArrayList modificato
     */
    public void setNaviCpu(ArrayList<Nave> naviCpu) {
        this.naviCpu = naviCpu;
    }

    /**
     * Metodo getter dell'ArrayList spariGiocatore
     *
     * @return spariGiocatore
     */
    public ArrayList<Point> getSpariGiocatore() {
        return spariGiocatore;
    }

    /**
     * Metodo setter dell'ArrayList spariGiocatore
     *
     * @param spariGiocatore viene passato ll'ArrayList modificato
     */
    public void setSpariGiocatore(ArrayList<Point> spariGiocatore) {
        this.spariGiocatore = spariGiocatore;
    }

    /**
     * Metodo getter dell'ArrayList spariCpu
     *
     * @return spariCpu
     */
    public ArrayList<Point> getSpariCpu() {
        return spariCpu;
    }

    /**
     * Metodo setter dell'ArrayList spariCpu
     *
     * @param spariCpu viene passato ll'ArrayList modificato
     */
    public void setSpariCpu(ArrayList<Point> spariCpu) {
        this.spariCpu = spariCpu;
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

    /**
     * Metodo getter della matrice di Boolean spazioNavi
     *
     * @return spazioNavi
     */
    public Boolean[][] getSpazioNavi() {
        return spazioNavi;
    }

    /**
     * Metodo setter della matrice di Boolean spazioNavi
     *
     * @param spazioNavi viene passata la matrice modificata
     */
    public void setSpazioNavi(Boolean[][] spazioNavi) {
        this.spazioNavi = spazioNavi;
    }

    /**
     * Metodo getter della matrice di Boolean spazioNaviCpu
     *
     * @return spazioNaviCpu
     */
    public Boolean[][] getSpazioNaviCpu() {
        return spazioNaviCpu;
    }

    /**
     * Metodo setter della matrice di Boolean spazioNaviCpu
     *
     * @param spazioNaviCpu viene passata la matrice modificata
     */
    public void setSpazioNaviCpu(Boolean[][] spazioNaviCpu) {
        this.spazioNaviCpu = spazioNaviCpu;
    }

    /**
     * Metodo getter del long startTimeSave
     *
     * @return startTimeSave
     */
    public long getStartTimeSave() {
        return startTimeSave;
    }

    /**
     * Metodo setter del long startTimeSave
     *
     * @param startTimeSave viene passato il long modificato
     */
    public void setStartTimeSave(long startTimeSave) {
        this.startTimeSave = startTimeSave;
    }
}
