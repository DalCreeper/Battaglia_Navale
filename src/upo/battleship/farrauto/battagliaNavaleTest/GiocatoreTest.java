package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.Giocatore;
import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class GiocatoreTest {
    private final Giocatore GiocatoreClass = new Giocatore();
    private int lunghezza = 0;
    private int lunghezzaSpari = 0;

    @org.junit.jupiter.api.Test
    public void aggiungiNaveTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        this.lunghezza = GiocatoreClass.getLunghezza();
        Nave nave = new Nave(puntoTest, 0, 0, 0);
        GiocatoreClass.aggiornaLunghezza();
        assertEquals(GiocatoreClass.getLunghezza(), lunghezza);
    }

    @org.junit.jupiter.api.Test
    public void aggiornaLunghezzaTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        lunghezza = GiocatoreClass.getLunghezza();
        Nave nave = new Nave(puntoTest, 0, 0, 0);
        GiocatoreClass.aggiornaLunghezza();
        assertEquals(GiocatoreClass.getLunghezza(), lunghezza);
    }

    @org.junit.jupiter.api.Test
    public void aggiungiSparoTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        this.lunghezzaSpari = GiocatoreClass.getSpariSfidante().size();
        GiocatoreClass.aggiungiSparo(puntoTest);
        assertEquals(GiocatoreClass.getSpariSfidante().size(),lunghezzaSpari + 1);
    }

    @org.junit.jupiter.api.Test
    public void getLunghezzaTest() {
        assertEquals(GiocatoreClass.getLunghezza(), lunghezza);
    }

    @org.junit.jupiter.api.Test
    public void setNaviSfidante() {
        ArrayList<Nave> naviSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviSfidanteTest.add(n);
        }
        GiocatoreClass.setNaviSfidante(naviSfidanteTest);
        assertEquals(GiocatoreClass.getNaviSfidante(), naviSfidanteTest);
    }

    @org.junit.jupiter.api.Test
    public void setSpariSfidanteTest() {
        ArrayList<Point> spariSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariSfidanteTest.add(puntoTest);
        }
        GiocatoreClass.setSpariSfidante(spariSfidanteTest);
        assertEquals(GiocatoreClass.getSpariSfidante(), spariSfidanteTest);
    }

    @org.junit.jupiter.api.Test
    public void getNaviSfidanteTest() {
        ArrayList<Nave> naviSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviSfidanteTest.add(n);
        }
        GiocatoreClass.setNaviSfidante(naviSfidanteTest);
        assertEquals(GiocatoreClass.getNaviSfidante(), naviSfidanteTest);
    }

    @org.junit.jupiter.api.Test
    public void getSpariSfidanteTest() {
        ArrayList<Point> spariSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariSfidanteTest.add(puntoTest);
        }
        GiocatoreClass.setSpariSfidante(spariSfidanteTest);
        assertEquals(GiocatoreClass.getSpariSfidante(), spariSfidanteTest);
    }
}