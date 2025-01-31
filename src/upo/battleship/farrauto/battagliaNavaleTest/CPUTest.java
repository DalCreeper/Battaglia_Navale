package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class CPUTest {
    @org.junit.jupiter.api.Test
    void estrapolaNaviTest() {
        ArrayList<Nave> naviSfidante = new ArrayList<>(10);

        BattagliaNavaleModel model = new BattagliaNavaleModel();
        model.inizializzaSpazionaveSpaziosparo(11);

        Point puntoTest1 = new Point();
        puntoTest1.x = 1;
        puntoTest1.y = 1;
        Nave nave1 = new Nave(puntoTest1, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave1);
        naviSfidante.add(nave1);

        Point puntoTest2 = new Point();
        puntoTest2.x = 1;
        puntoTest2.y = 3;
        Nave nave2 = new Nave(puntoTest2, 0, 0, 2);
        model.getGiocatore().aggiungiNave(nave2);
        naviSfidante.add(nave2);

        Point puntoTest3 = new Point();
        puntoTest3.x = 1;
        puntoTest3.y = 5;
        Nave nave3 = new Nave(puntoTest3, 0, 0, 3);
        model.getGiocatore().aggiungiNave(nave3);
        naviSfidante.add(nave3);

        Point puntoTest4 = new Point();
        puntoTest4.x = 1;
        puntoTest4.y = 7;
        Nave nave4 = new Nave(puntoTest4, 0, 0, 4);
        model.getGiocatore().aggiungiNave(nave4);
        naviSfidante.add(nave4);

        Point puntoTest5 = new Point();
        puntoTest5.x = 1;
        puntoTest5.y = 9;
        Nave nave5 = new Nave(puntoTest5, 0, 0, 5);
        model.getGiocatore().aggiungiNave(nave5);
        naviSfidante.add(nave5);

        Point puntoTest6 = new Point();
        puntoTest6.x = 3;
        puntoTest6.y = 1;
        Nave nave6 = new Nave(puntoTest6, 0, 0, 6);
        model.getGiocatore().aggiungiNave(nave6);
        naviSfidante.add(nave6);

        Point puntoTest7 = new Point();
        puntoTest7.x = 3;
        puntoTest7.y = 3;
        Nave nave7 = new Nave(puntoTest7, 0, 0, 7);
        model.getGiocatore().aggiungiNave(nave7);
        naviSfidante.add(nave7);

        int dim = 11;
        model.getCpu().estrapolaNavi(model, dim);
        assertEquals(model.getCpu().getNaviSfidante().size(), naviSfidante.size());
    }

    @org.junit.jupiter.api.Test
    void cervelloTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        Boolean[][] spazioSparoCpuTest = new Boolean[11][11];
        Boolean[][] spazioNaviTest = new Boolean[11][11];
        ArrayList<Nave> naviSfidante = new ArrayList<>(10);

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                spazioSparoCpuTest[i][j] = false;
            }
        }

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                spazioNaviTest[i][j] = false;
            }
        }

        spazioSparoCpuTest[1][1] = true;

        model.setSpazioNavi(spazioNaviTest);

        Point puntoTest1 = new Point();
        puntoTest1.x = 1;
        puntoTest1.y = 1;
        Nave nave1 = new Nave(puntoTest1, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave1);
        model.piazzaNaveNellaMappa(nave1,true, 11);
        naviSfidante.add(nave1);

        Point puntoTest2 = new Point();
        puntoTest2.x = 1;
        puntoTest2.y = 3;
        Nave nave2 = new Nave(puntoTest2, 0, 0, 2);
        model.getGiocatore().aggiungiNave(nave2);
        model.piazzaNaveNellaMappa(nave2,true, 11);
        naviSfidante.add(nave2);

        Point puntoTest3 = new Point();
        puntoTest3.x = 1;
        puntoTest3.y = 5;
        Nave nave3 = new Nave(puntoTest3, 0, 0, 3);
        model.getGiocatore().aggiungiNave(nave3);
        model.piazzaNaveNellaMappa(nave3,true, 11);
        naviSfidante.add(nave3);

        Point puntoTest4 = new Point();
        puntoTest4.x = 1;
        puntoTest4.y = 7;
        Nave nave4 = new Nave(puntoTest4, 0, 0, 4);
        model.getGiocatore().aggiungiNave(nave4);
        model.piazzaNaveNellaMappa(nave4,true, 11);
        naviSfidante.add(nave4);

        Point puntoTest5 = new Point();
        puntoTest5.x = 1;
        puntoTest5.y = 9;
        Nave nave5 = new Nave(puntoTest5, 0, 0, 5);
        model.getGiocatore().aggiungiNave(nave5);
        model.piazzaNaveNellaMappa(nave5,true, 11);
        naviSfidante.add(nave5);

        Point puntoTest6 = new Point();
        puntoTest6.x = 3;
        puntoTest6.y = 1;
        Nave nave6 = new Nave(puntoTest6, 0, 0, 6);
        model.getGiocatore().aggiungiNave(nave6);
        model.piazzaNaveNellaMappa(nave6,true, 11);
        naviSfidante.add(nave6);

        Point puntoTest7 = new Point();
        puntoTest7.x = 3;
        puntoTest7.y = 3;
        Nave nave7 = new Nave(puntoTest7, 0, 0, 7);
        model.getGiocatore().aggiungiNave(nave7);
        model.piazzaNaveNellaMappa(nave7,true, 11);
        naviSfidante.add(nave7);

        model.getCpu().setNaviSfidante(naviSfidante);
        model.getCpu().setNaviGiocatore(model.getGiocatore().getNaviSfidante());
        model.setSpazioSparoCpu(spazioSparoCpuTest);
        model.setIdColpito(1);

        assertTrue(model.getCpu().cervello(model, 11));

        spazioNaviTest[1][1] = false;

        assertFalse(model.getCpu().cervello(model, 11));
    }

    @org.junit.jupiter.api.Test
    void pensaTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        Boolean[][] spazioSparoCpuTest = new Boolean[11][11];

        Point puntoTest = new Point();
        puntoTest.x = 5;
        puntoTest.y = 5;
        Point puntoTestUp = new Point();
        puntoTestUp.x = 5;
        puntoTestUp.y = 6;
        Point puntoTestDown = new Point();
        puntoTestDown.x = 5;
        puntoTestDown.y = 4;
        Point puntoTestLeft = new Point();
        puntoTestLeft.x = 4;
        puntoTestLeft.y = 5;
        Point puntoTestRight = new Point();
        puntoTestRight.x = 6;
        puntoTestRight.y = 5;

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                spazioSparoCpuTest[i][j] = false;
            }
        }
        model.setSpazioSparoCpu(spazioSparoCpuTest);

        Point puntoCoordRet;
        puntoCoordRet = model.getCpu().pensa(puntoTest, 2, model, 11);

        if(puntoCoordRet == puntoTestUp) {
            assertEquals(puntoCoordRet, puntoTestUp);
        }
        if(puntoCoordRet == puntoTestDown) {
            assertEquals(puntoCoordRet, puntoTestDown);
        }
        if(puntoCoordRet == puntoTestLeft) {
            assertEquals(puntoCoordRet, puntoTestLeft);
        }
        if(puntoCoordRet == puntoTestRight) {
            assertEquals(puntoCoordRet, puntoTestRight);
        }

        puntoCoordRet = model.getCpu().pensa(puntoTest, 0, model, 11);

        if(puntoCoordRet == puntoTestUp) {
            assertEquals(puntoCoordRet, puntoTestUp);
        }
        if(puntoCoordRet == puntoTestDown) {
            assertEquals(puntoCoordRet, puntoTestDown);
        }

        puntoCoordRet = model.getCpu().pensa(puntoTest, 1, model, 11);

        if(puntoCoordRet == puntoTestLeft) {
            assertEquals(puntoCoordRet, puntoTestLeft);
        }
        if(puntoCoordRet == puntoTestRight) {
            assertEquals(puntoCoordRet, puntoTestRight);
        }
    }

    @org.junit.jupiter.api.Test
    void GeneraCoordinateTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        boolean xControl = false;
        boolean yControl = false;

        Point puntoTest;
        puntoTest = model.getCpu().generaCoordinate();

        if(puntoTest.x < 11 && puntoTest.x > 0) {
            xControl = true;
        }

        if(puntoTest.y < 11 && puntoTest.y > 0) {
            yControl = true;
        }

        assertTrue(xControl);
        assertTrue(yControl);
    }

    @org.junit.jupiter.api.Test
    public void aggiungiNaveTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave nave = new Nave(puntoTest, 0, 0, 0);
        model.getCpu().aggiungiNave(nave);
        int lunghezza = model.getCpu().getLunghezza();
        assertEquals(model.getCpu().getLunghezza(), lunghezza);
    }

    @org.junit.jupiter.api.Test
    public void aggiornaLunghezzaTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        Nave nave = new Nave(puntoTest, 0, 0, 0);
        model.getCpu().aggiungiNave(nave);
        model.getCpu().aggiornaLunghezza();
        int lunghezza = model.getCpu().getLunghezza();
        assertEquals(model.getCpu().getLunghezza(), lunghezza);
    }

    @org.junit.jupiter.api.Test
    public void aggiungiSparoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        model.getCpu().aggiungiSparo(puntoTest);
        int lunghezzaSpari = model.getCpu().getSpariSfidante().size();
        assertEquals(model.getCpu().getSpariSfidante().size(),lunghezzaSpari);
    }

    @org.junit.jupiter.api.Test
    void randomizeTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        int risultato = 0;
        boolean controllo = false;
        risultato = model.getCpu().randomize(10, 1);
        if(risultato >= 0 && risultato <= 11) {
            controllo = true;
        }
        assertTrue(controllo);
    }

    @org.junit.jupiter.api.Test
    void getCoordinateFinaliTest() {
        Point puntoTest = new Point();
        puntoTest.x = 0;
        puntoTest.y = 0;

        BattagliaNavaleModel model = new BattagliaNavaleModel();

        assertEquals(model.getCpu().getCoordinateFinali(), puntoTest);
    }

    @org.junit.jupiter.api.Test
    void getLunghezzaTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        int lunghezza = 0;

        assertEquals(model.getCpu().getLunghezza(), lunghezza);
    }

    @org.junit.jupiter.api.Test
    void getNaviSfidanteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        ArrayList<Nave> naviSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviSfidanteTest.add(n);
        }
        model.getCpu().setNaviSfidante(naviSfidanteTest);
        assertEquals(model.getCpu().getNaviSfidante(), naviSfidanteTest);
    }

    @org.junit.jupiter.api.Test
    void setNaviSfidanteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        ArrayList<Nave> naviSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviSfidanteTest.add(n);
        }
        model.getCpu().setNaviSfidante(naviSfidanteTest);
        assertEquals(model.getCpu().getNaviSfidante(), naviSfidanteTest);
    }


    @org.junit.jupiter.api.Test
    void setNaviGiocatoreTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        ArrayList<Nave> naviSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviSfidanteTest.add(n);
        }
        model.getCpu().setNaviGiocatore(naviSfidanteTest);
        assertEquals(model.getCpu().getNaviGiocatore(), naviSfidanteTest);
    }

    @org.junit.jupiter.api.Test
    public void setSpariSfidanteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        ArrayList<Point> spariSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariSfidanteTest.add(puntoTest);
        }
        model.getCpu().setSpariSfidante(spariSfidanteTest);
        assertEquals(model.getCpu().getSpariSfidante(), spariSfidanteTest);
    }

    @org.junit.jupiter.api.Test
    public void getSpariSfidanteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        ArrayList<Point> spariSfidanteTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariSfidanteTest.add(puntoTest);
        }
        model.getCpu().setSpariSfidante(spariSfidanteTest);
        assertEquals(model.getCpu().getSpariSfidante(), spariSfidanteTest);
    }
}