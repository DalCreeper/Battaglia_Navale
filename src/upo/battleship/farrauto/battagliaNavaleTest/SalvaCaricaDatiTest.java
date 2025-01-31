package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.Nave;
import upo.battleship.farrauto.battagliaNavaleModel.SalvaCaricaDati;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class SalvaCaricaDatiTest {
    private final SalvaCaricaDati SalvaCaricaDatiClass = new SalvaCaricaDati();

    @org.junit.jupiter.api.Test
    public void setNaviGiocatore() {
        ArrayList<Nave> naviGiocatoreTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviGiocatoreTest.add(n);
        }
        SalvaCaricaDatiClass.setNaviGiocatore(naviGiocatoreTest);
        assertEquals(SalvaCaricaDatiClass.getNaviGiocatore(), naviGiocatoreTest);
    }

    @org.junit.jupiter.api.Test
    public void getNaviGiocaotreTest() {
        ArrayList<Nave> naviGiocaotreTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviGiocaotreTest.add(n);
        }
        SalvaCaricaDatiClass.setNaviGiocatore(naviGiocaotreTest);
        assertEquals(SalvaCaricaDatiClass.getNaviGiocatore(), naviGiocaotreTest);
    }

    @org.junit.jupiter.api.Test
    public void setNaviCPU() {
        ArrayList<Nave> naviCPUTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviCPUTest.add(n);
        }
        SalvaCaricaDatiClass.setNaviCpu(naviCPUTest);
        assertEquals(SalvaCaricaDatiClass.getNaviCpu(), naviCPUTest);
    }

    @org.junit.jupiter.api.Test
    public void getNaviCPUTest() {
        ArrayList<Nave> naviCPUTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            Nave n = new Nave(puntoTest, 0, 0, 0);
            naviCPUTest.add(n);
        }
        SalvaCaricaDatiClass.setNaviCpu(naviCPUTest);
        assertEquals(SalvaCaricaDatiClass.getNaviCpu(), naviCPUTest);
    }

    @org.junit.jupiter.api.Test
    public void setSpariGiocatoreTest() {
        ArrayList<Point> spariGiocatoreTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariGiocatoreTest.add(puntoTest);
        }
        SalvaCaricaDatiClass.setSpariGiocatore(spariGiocatoreTest);
        assertEquals(SalvaCaricaDatiClass.getSpariGiocatore(), spariGiocatoreTest);
    }

    @org.junit.jupiter.api.Test
    public void getSpariGiocatoreTest() {
        ArrayList<Point> spariGiocatoreTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariGiocatoreTest.add(puntoTest);
        }
        SalvaCaricaDatiClass.setSpariGiocatore(spariGiocatoreTest);
        assertEquals(SalvaCaricaDatiClass.getSpariGiocatore(), spariGiocatoreTest);
    }

    @org.junit.jupiter.api.Test
    public void setSpariCPUTest() {
        ArrayList<Point> spariCPUTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariCPUTest.add(puntoTest);
        }
        SalvaCaricaDatiClass.setSpariCpu(spariCPUTest);
        assertEquals(SalvaCaricaDatiClass.getSpariCpu(), spariCPUTest);
    }

    @org.junit.jupiter.api.Test
    public void getSpariCPUTest() {
        ArrayList<Point> spariCPUTest = new ArrayList<>(10);
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        for(int i = 0; i < 7; i++) {
            spariCPUTest.add(puntoTest);
        }
        SalvaCaricaDatiClass.setSpariCpu(spariCPUTest);
        assertEquals(SalvaCaricaDatiClass.getSpariCpu(), spariCPUTest);
    }

    @org.junit.jupiter.api.Test
    public void setInfoTest() {
        String infoTest;
        infoTest = "ciao";

        SalvaCaricaDatiClass.setInfo(infoTest);
        assertEquals(infoTest, SalvaCaricaDatiClass.getInfo());
    }

    @org.junit.jupiter.api.Test
    public void getInfo() {
        String infoTest;
        infoTest = "ciao";

        SalvaCaricaDatiClass.setInfo(infoTest);
        assertEquals(infoTest, SalvaCaricaDatiClass.getInfo());
    }

    @org.junit.jupiter.api.Test
    public void setSpazioNaviTest() {
        Boolean[][] spazioNaviTest = new Boolean[11][11];
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                spazioNaviTest[i][j] = false;
            }
        }

        SalvaCaricaDatiClass.setSpazioNavi(spazioNaviTest);
        assertEquals(SalvaCaricaDatiClass.getSpazioNavi(), spazioNaviTest);
    }

    @org.junit.jupiter.api.Test
    public void getSpazioNaviTest() {
        Boolean[][] spazioNaviTest = new Boolean[11][11];
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                spazioNaviTest[i][j] = false;
            }
        }

        SalvaCaricaDatiClass.setSpazioNavi(spazioNaviTest);
        assertEquals(SalvaCaricaDatiClass.getSpazioNavi(), spazioNaviTest);
    }

    @org.junit.jupiter.api.Test
    public void setSpazioNaviCpuTest() {
        Boolean[][] spazioNaviCpuTest = new Boolean[11][11];
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                spazioNaviCpuTest[i][j] = false;
            }
        }

        SalvaCaricaDatiClass.setSpazioNaviCpu(spazioNaviCpuTest);
        assertEquals(SalvaCaricaDatiClass.getSpazioNaviCpu(), spazioNaviCpuTest);
    }

    @org.junit.jupiter.api.Test
    public void getSpazioNaviCpuTest() {
        Boolean[][] spazioNaviCpuTest = new Boolean[11][11];
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                spazioNaviCpuTest[i][j] = false;
            }
        }

        SalvaCaricaDatiClass.setSpazioNaviCpu(spazioNaviCpuTest);
        assertEquals(SalvaCaricaDatiClass.getSpazioNaviCpu(), spazioNaviCpuTest);
    }
}