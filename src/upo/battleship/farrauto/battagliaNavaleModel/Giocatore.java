package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class Giocatore extends Sfidanti {
    private int lunghezza;

    /**
     * Il costruttore del giocatore costruisce le sue 2 liste,
     * la lista contenente le navi del giocatore e la lista
     * contenente gli spari del giocatore
     *
     * @see Sfidanti sfidanti
     */
    public Giocatore() {
        naviSfidante = new ArrayList<>(10);
        spariSfidante = new ArrayList<>();
    }

    /**
     * Il metodo aggiunge una nave alla lista delle navi
     * del giocatore
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
     * del giocatore
     *
     * @param sparo viene passato lo sparo appena effettuato
     * @see Sfidanti sfidanti
     */
    public void aggiungiSparo(Point sparo) {
        spariSfidante.add(sparo);
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