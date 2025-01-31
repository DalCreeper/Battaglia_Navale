package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class NaveTest {
    @org.junit.jupiter.api.Test
    void controlloEccedenzaOrientamentoTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveSomm = new Nave(puntoTest, 0, 0, 1);

        naveSomm.controlloEccedenzaOrientamento(puntoTest, 0, 1);
        assertEquals(naveSomm.getCoordinatePosizionamento().x, 1);
        assertEquals(naveSomm.getCoordinatePosizionamento().y, 1);

        naveSomm.controlloEccedenzaOrientamento(puntoTest, 1, 1);
        assertEquals(naveSomm.getCoordinatePosizionamento().x, 1);
        assertEquals(naveSomm.getCoordinatePosizionamento().y, 1);

        Point puntoTest2 = new Point();
        puntoTest2.x = 10;
        puntoTest2.y = 10;
        Nave naveTorp = new Nave(puntoTest2, 0, 1, 1);

        naveTorp.controlloEccedenzaOrientamento(puntoTest2, 0, 2);
        assertEquals(naveTorp.getCoordinatePosizionamento().x, 10);
        assertEquals(naveTorp.getCoordinatePosizionamento().y, 9);

        naveTorp.controlloEccedenzaOrientamento(puntoTest2, 1, 2);
        assertEquals(naveTorp.getCoordinatePosizionamento().x, 9);
        assertEquals(naveTorp.getCoordinatePosizionamento().y, 10);

        Point puntoTest3 = new Point();
        puntoTest3.x = 9;
        puntoTest3.y = 9;
        Nave naveIncr = new Nave(puntoTest3, 0, 2, 1);

        naveIncr.controlloEccedenzaOrientamento(puntoTest3, 0, 3);
        assertEquals(naveIncr.getCoordinatePosizionamento().x, 9);
        assertEquals(naveIncr.getCoordinatePosizionamento().y, 8);

        naveIncr.controlloEccedenzaOrientamento(puntoTest3, 1, 3);
        assertEquals(naveIncr.getCoordinatePosizionamento().x, 8);
        assertEquals(naveIncr.getCoordinatePosizionamento().y, 9);

        Point puntoTest4 = new Point();
        puntoTest4.x = 8;
        puntoTest4.y = 8;
        Nave navePort = new Nave(puntoTest3, 0, 3, 1);

        navePort.controlloEccedenzaOrientamento(puntoTest4, 0, 4);
        assertEquals(navePort.getCoordinatePosizionamento().x, 8);
        assertEquals(navePort.getCoordinatePosizionamento().y, 7);

        navePort.controlloEccedenzaOrientamento(puntoTest4, 1, 4);
        assertEquals(navePort.getCoordinatePosizionamento().x, 7);
        assertEquals(navePort.getCoordinatePosizionamento().y, 8);
    }

    @org.junit.jupiter.api.Test
    void setCooNaveTest() {
        Point[] pointVett = new Point[1];
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        pointVett[0] = puntoTest;

        naveTest.setCooNave(pointVett);
        assertEquals(naveTest.getCooNave(), pointVett);
    }

    @org.junit.jupiter.api.Test
    void getCooNaveTest() {
        Point[] pointVett = new Point[1];
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        pointVett[0] = puntoTest;

        naveTest.setCooNave(pointVett);
        assertEquals(naveTest.getCooNave(), pointVett);
    }

    @org.junit.jupiter.api.Test
    void getCooNave() {
        Point[] pointVett = new Point[1];
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        pointVett[0] = puntoTest;

        naveTest.setCooNave(pointVett);
        assertEquals(naveTest.getCooNave(), pointVett);
    }

    @org.junit.jupiter.api.Test
    void navePosizionataTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        naveTest.navePosizionata();
        String infoTest = "Hai posizionato un Sommergibile!";
        assertEquals(naveTest.getInfo(), infoTest);
    }

    @org.junit.jupiter.api.Test
    void getInfoTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        naveTest.navePosizionata();
        String infoTest = "Hai posizionato un Sommergibile!";
        assertEquals(naveTest.getInfo(), infoTest);
    }

    @org.junit.jupiter.api.Test
    void getTipoNaveTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        assertEquals(naveTest.getTipoNave(),1);
    }

    @org.junit.jupiter.api.Test
    void getDirezionePruaTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        assertEquals(naveTest.getDirezionePrua(), 0);

        Point puntoTest2 = new Point();
        puntoTest2.x = 1;
        puntoTest2.y = 1;
        Nave naveTest2 = new Nave(puntoTest2, 1, 0, 1);
        assertEquals(naveTest2.getDirezionePrua(), 1);
    }

    @org.junit.jupiter.api.Test
    void getColpitoTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        naveTest.setColpito(0);
        assertEquals(naveTest.getColpito(), 0);
        naveTest.setColpito(1);
        assertEquals(naveTest.getColpito(), 1);
    }

    @org.junit.jupiter.api.Test
    void setColpitoTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        naveTest.setColpito(0);
        assertEquals(naveTest.getColpito(), 0);
        naveTest.setColpito(1);
        assertEquals(naveTest.getColpito(), 1);
    }

    @org.junit.jupiter.api.Test
    void getIdTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveTest = new Nave(puntoTest, 0, 0, 1);
        assertEquals(naveTest.getId(), 1);
    }

    @org.junit.jupiter.api.Test
    void getCoordinatePosizionametoTest() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave naveSomm = new Nave(puntoTest, 0, 0, 1);

        naveSomm.controlloEccedenzaOrientamento(puntoTest, 0, 0);
        assertEquals(naveSomm.getCoordinatePosizionamento().x, 1);
        assertEquals(naveSomm.getCoordinatePosizionamento().y, 1);

        naveSomm.controlloEccedenzaOrientamento(puntoTest, 1, 0);
        assertEquals(naveSomm.getCoordinatePosizionamento().x, 1);
        assertEquals(naveSomm.getCoordinatePosizionamento().y, 1);
    }
}