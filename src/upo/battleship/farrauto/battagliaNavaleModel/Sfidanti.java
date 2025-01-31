package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public abstract class Sfidanti {
    protected ArrayList<Nave> naviSfidante;             //ArrayList che contiene tutte le navi
    protected ArrayList<Point> spariSfidante;           //ArrayList che contiene tutti gli spari

    public abstract void aggiungiNave(Nave n);

    public abstract void aggiornaLunghezza();

    public abstract void aggiungiSparo(Point sparo);

    public abstract int getLunghezza();

    public abstract void setSpariSfidante(ArrayList<Point> spariSfidante);

    public abstract void setNaviSfidante(ArrayList<Nave> naviSfidante);

    public abstract ArrayList<Point> getSpariSfidante();

    public abstract ArrayList<Nave> getNaviSfidante();
}
