package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.CPU;
import upo.battleship.farrauto.battagliaNavaleModel.Giocatore;
import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class BattagliaNavaleModelTest {
    @org.junit.jupiter.api.Test
    void inizializzaSpazionaveSpaziosparoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        int dim = 11;
        Boolean[][] spazioNavi = new Boolean[dim][dim];
        Boolean[][] spazioSparo = new Boolean[dim][dim];
        Boolean[][] spazioNaviCpu = new Boolean[dim][dim];
        Boolean[][] spazioSparoCpu = new Boolean[dim][dim];

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioNavi[i][j] = false;
                spazioSparo[i][j] = false;
                spazioNaviCpu[i][j] = false;
                spazioSparoCpu[i][j] = false;
            }
        }

        model.inizializzaSpazionaveSpaziosparo(dim);

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioNavi()[i][j], spazioNavi[i][j]);
                assertEquals(model.getSpazioNaviCpu()[i][j], spazioNaviCpu[i][j]);
                assertEquals(model.getSpazioSparo()[i][j], spazioSparo[i][j]);
                assertEquals(model.getSpazioSparoCpu()[i][j], spazioSparoCpu[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void inizializzaSpaziosparoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        int dim = 11;
        Boolean[][] spazioSparo = new Boolean[dim][dim];
        Boolean[][] spazioSparoCpu = new Boolean[dim][dim];

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparo[i][j] = false;
                spazioSparoCpu[i][j] = false;
            }
        }

        model.inizializzaSpaziosparo(dim);

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioSparo()[i][j], spazioSparo[i][j]);
                assertEquals(model.getSpazioSparoCpu()[i][j], spazioSparoCpu[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void controlloPartitaTest() {
        int dim = 11;
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        model.inizializzaSpazionaveSpaziosparo(dim);
        ArrayList<Nave> naviSfidante = new ArrayList<>(10);

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

        assertEquals(model.controlloPartita(), 0 );
    }

    @org.junit.jupiter.api.Test
    void inserisciSparoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        int dim = 11;
        Boolean[][] spazioSparo = new Boolean[dim][dim];

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparo[i][j] = false;
            }
        }

        spazioSparo[1][1] = true;

        model.inizializzaSpazionaveSpaziosparo(dim);

        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        model.inserisciSparo(puntoTest, true, dim);

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioSparo()[i][j], spazioSparo[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void controllaSparoPresenteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        int dim = 11;

        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        Point puntoTest2 = new Point();
        puntoTest2.x = 2;
        puntoTest2.y = 2;

        model.inizializzaSpazionaveSpaziosparo(dim);
        model.inserisciSparo(puntoTest, true, dim);
        assertTrue(model.controllaSparoPresente(puntoTest, true, dim));
        assertFalse(model.controllaSparoPresente(puntoTest2, true, dim));
    }

    @org.junit.jupiter.api.Test
    void piazzaNaveNellaMappaTest() {
        int dim = 11;
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        model.inizializzaSpazionaveSpaziosparo(dim);
        Boolean[][] spazioNaviTest = new Boolean[11][11];

        model.inizializzaSpazionaveSpaziosparo(dim);

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                spazioNaviTest[i][j] = false;
            }
        }

        spazioNaviTest[1][1] = true;

        Point puntoTest1 = new Point();
        puntoTest1.x = 1;
        puntoTest1.y = 1;
        Nave nave1 = new Nave(puntoTest1, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave1);
        model.piazzaNaveNellaMappa(nave1,true, 11);

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioNavi()[i][j], spazioNaviTest[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void controlloSparoTest() {
        int dim = 11;
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        model.inizializzaSpazionaveSpaziosparo(dim);

        Point puntoTest1 = new Point();
        puntoTest1.x = 1;
        puntoTest1.y = 1;
        Nave nave1 = new Nave(puntoTest1, 0, 0, 1);
        Nave nave8 = new Nave(puntoTest1, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave1);
        model.getCpu().aggiungiNave(nave8);
        model.piazzaNaveNellaMappa(nave1,true, 11);
        model.piazzaNaveNellaMappa(nave8,false, 11);

        Point puntoTest2 = new Point();
        puntoTest2.x = 1;
        puntoTest2.y = 3;
        Nave nave2 = new Nave(puntoTest2, 0, 0, 2);
        Nave nave9 = new Nave(puntoTest2, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave2);
        model.getCpu().aggiungiNave(nave9);
        model.piazzaNaveNellaMappa(nave2,true, 11);
        model.piazzaNaveNellaMappa(nave9,false, 11);

        Point puntoTest3 = new Point();
        puntoTest3.x = 1;
        puntoTest3.y = 5;
        Nave nave3 = new Nave(puntoTest3, 0, 0, 3);
        Nave nave10 = new Nave(puntoTest3, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave3);
        model.getCpu().aggiungiNave(nave10);
        model.piazzaNaveNellaMappa(nave3,true, 11);
        model.piazzaNaveNellaMappa(nave10,false, 11);

        Point puntoTest4 = new Point();
        puntoTest4.x = 1;
        puntoTest4.y = 7;
        Nave nave4 = new Nave(puntoTest4, 0, 0, 4);
        Nave nave11 = new Nave(puntoTest4, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave4);
        model.getCpu().aggiungiNave(nave11);
        model.piazzaNaveNellaMappa(nave4,true, 11);
        model.piazzaNaveNellaMappa(nave11,false, 11);

        Point puntoTest5 = new Point();
        puntoTest5.x = 1;
        puntoTest5.y = 9;
        Nave nave5 = new Nave(puntoTest5, 0, 0, 5);
        Nave nave12 = new Nave(puntoTest5, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave5);
        model.getCpu().aggiungiNave(nave12);
        model.piazzaNaveNellaMappa(nave5,true, 11);
        model.piazzaNaveNellaMappa(nave12,false, 11);

        Point puntoTest6 = new Point();
        puntoTest6.x = 3;
        puntoTest6.y = 1;
        Nave nave6 = new Nave(puntoTest6, 0, 0, 6);
        Nave nave13 = new Nave(puntoTest6, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave6);
        model.getCpu().aggiungiNave(nave13);
        model.piazzaNaveNellaMappa(nave6,true, 11);
        model.piazzaNaveNellaMappa(nave13,false, 11);

        Point puntoTest7 = new Point();
        puntoTest7.x = 3;
        puntoTest7.y = 3;
        Nave nave7 = new Nave(puntoTest7, 0, 0, 7);
        Nave nave14 = new Nave(puntoTest7, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave7);
        model.getCpu().aggiungiNave(nave14);
        model.piazzaNaveNellaMappa(nave7,true, 11);
        model.piazzaNaveNellaMappa(nave14,false, 11);

        Point puntoTestSparoVuoto = new Point();
        puntoTest6.x = 2;
        puntoTest6.y = 2;

        assertEquals(model.controlloSparo(puntoTest1, true, dim),0);
        assertEquals(model.controlloSparo(puntoTestSparoVuoto, true, dim),2);
        assertEquals(model.controlloSparo(puntoTest1, false, dim),3);
        assertEquals(model.controlloSparo(puntoTestSparoVuoto, false, dim),5);
    }

    @org.junit.jupiter.api.Test
    void sparaTest() {
        int dim = 11;
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        model.inizializzaSpazionaveSpaziosparo(dim);

        Point puntoTest1 = new Point();
        puntoTest1.x = 1;
        puntoTest1.y = 1;
        Nave nave1 = new Nave(puntoTest1, 0, 0, 1);
        Nave nave8 = new Nave(puntoTest1, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave1);
        model.getCpu().aggiungiNave(nave8);
        model.piazzaNaveNellaMappa(nave1,true, 11);
        model.piazzaNaveNellaMappa(nave8,false, 11);

        Point puntoTest2 = new Point();
        puntoTest2.x = 1;
        puntoTest2.y = 3;
        Nave nave2 = new Nave(puntoTest2, 0, 0, 2);
        Nave nave9 = new Nave(puntoTest2, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave2);
        model.getCpu().aggiungiNave(nave9);
        model.piazzaNaveNellaMappa(nave2,true, 11);
        model.piazzaNaveNellaMappa(nave9,false, 11);

        Point puntoTest3 = new Point();
        puntoTest3.x = 1;
        puntoTest3.y = 5;
        Nave nave3 = new Nave(puntoTest3, 0, 0, 3);
        Nave nave10 = new Nave(puntoTest3, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave3);
        model.getCpu().aggiungiNave(nave10);
        model.piazzaNaveNellaMappa(nave3,true, 11);
        model.piazzaNaveNellaMappa(nave10,false, 11);

        Point puntoTest4 = new Point();
        puntoTest4.x = 1;
        puntoTest4.y = 7;
        Nave nave4 = new Nave(puntoTest4, 0, 0, 4);
        Nave nave11 = new Nave(puntoTest4, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave4);
        model.getCpu().aggiungiNave(nave11);
        model.piazzaNaveNellaMappa(nave4,true, 11);
        model.piazzaNaveNellaMappa(nave11,false, 11);

        Point puntoTest5 = new Point();
        puntoTest5.x = 1;
        puntoTest5.y = 9;
        Nave nave5 = new Nave(puntoTest5, 0, 0, 5);
        Nave nave12 = new Nave(puntoTest5, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave5);
        model.getCpu().aggiungiNave(nave12);
        model.piazzaNaveNellaMappa(nave5,true, 11);
        model.piazzaNaveNellaMappa(nave12,false, 11);

        Point puntoTest6 = new Point();
        puntoTest6.x = 3;
        puntoTest6.y = 1;
        Nave nave6 = new Nave(puntoTest6, 0, 0, 6);
        Nave nave13 = new Nave(puntoTest6, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave6);
        model.getCpu().aggiungiNave(nave13);
        model.piazzaNaveNellaMappa(nave6,true, 11);
        model.piazzaNaveNellaMappa(nave13,false, 11);

        Point puntoTest7 = new Point();
        puntoTest7.x = 3;
        puntoTest7.y = 3;
        Nave nave7 = new Nave(puntoTest7, 0, 0, 7);
        Nave nave14 = new Nave(puntoTest7, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave7);
        model.getCpu().aggiungiNave(nave14);
        model.piazzaNaveNellaMappa(nave7,true, 11);
        model.piazzaNaveNellaMappa(nave14,false, 11);

        Point puntoTestSparoVuoto = new Point();
        puntoTest6.x = 2;
        puntoTest6.y = 2;

        assertEquals(model.spara(puntoTest1,true, dim), 1);
        assertEquals(model.spara(puntoTestSparoVuoto, true, dim), 3);

        assertEquals(model.spara(puntoTest1,false, dim), 1);
        assertEquals(model.spara(puntoTestSparoVuoto, false, dim), 3);
    }

    @org.junit.jupiter.api.Test
    void getIdColpitoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        assertEquals(model.getIdColpito(), 0);
    }

    @org.junit.jupiter.api.Test
    void getIdTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        assertEquals(model.getId(), 0);
    }

    @org.junit.jupiter.api.Test
    void getGiocatore() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        Giocatore giocatore = new Giocatore();

        assertEquals(model.getGiocatore().getLunghezza(), giocatore.getLunghezza());
        assertEquals(model.getGiocatore().getNaviSfidante().size(), giocatore.getNaviSfidante().size());
        assertEquals(model.getGiocatore().getSpariSfidante().size(), giocatore.getSpariSfidante().size());
    }

    @org.junit.jupiter.api.Test
    void getCpuTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        CPU cpu = new CPU();

        assertEquals(model.getCpu().getLunghezza(), cpu.getLunghezza());
        assertEquals(model.getCpu().getNaviGiocatore(), cpu.getNaviGiocatore());
        assertEquals(model.getCpu().getCoordinateFinali(), cpu.getCoordinateFinali());
        assertEquals(model.getCpu().getNaviSfidante().size(), cpu.getNaviSfidante().size());
        assertEquals(model.getCpu().getSpariSfidante().size(), cpu.getSpariSfidante().size());
    }

    @org.junit.jupiter.api.Test
    void getSpazioNaviTest() {
        int dim = 11;
        Boolean[][] spazioNavi = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        model.inizializzaSpazionaveSpaziosparo(dim);
        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioNavi[i][j] = false;
            }
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioNavi()[i][j], spazioNavi[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void setSpazioNaviTest() {
        int dim = 11;
        Boolean[][] spazioNavi = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioNavi[i][j] = false;
            }
        }
        model.setSpazioNavi(spazioNavi);
        assertEquals(model.getSpazioNavi(),spazioNavi);
    }

    @org.junit.jupiter.api.Test
    void getSpazioSparoTest() {
        int dim = 11;
        Boolean[][] spazioSparo = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        model.inizializzaSpazionaveSpaziosparo(dim);
        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparo[i][j] = false;
            }
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioSparo()[i][j], spazioSparo[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void setSpazioSparoTest() {
        int dim = 11;
        Boolean[][] spazioSparo = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparo[i][j] = false;
            }
        }
        model.setSpaziosparo(spazioSparo);
        assertEquals(model.getSpazioSparo(),spazioSparo);
    }

    @org.junit.jupiter.api.Test
    void getSpazioNaviCpuTest() {
        int dim = 11;
        Boolean[][] spazioNaviCpu = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        model.inizializzaSpazionaveSpaziosparo(dim);
        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioNaviCpu[i][j] = false;
            }
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioNaviCpu()[i][j], spazioNaviCpu[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void setSpazioNaviCpuTest() {
        int dim = 11;
        Boolean[][] spazioNaviCpu = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioNaviCpu[i][j] = false;
            }
        }
        model.setSpazioNaviCpu(spazioNaviCpu);
        assertEquals(model.getSpazioNaviCpu(),spazioNaviCpu);
    }

    @org.junit.jupiter.api.Test
    void getSpazioSparoCpuTest() {
        int dim = 11;
        Boolean[][] spazioSparoCpu = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        model.inizializzaSpazionaveSpaziosparo(dim);
        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparoCpu[i][j] = false;
            }
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                assertEquals(model.getSpazioSparoCpu()[i][j], spazioSparoCpu[i][j]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void setSpazioSparoCpuTest() {
        int dim = 11;
        Boolean[][] spazioSparoCpu = new Boolean[dim][dim];
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                spazioSparoCpu[i][j] = false;
            }
        }
        model.setSpazioSparoCpu(spazioSparoCpu);
        assertEquals(model.getSpazioSparoCpu(),spazioSparoCpu);
    }

    @org.junit.jupiter.api.Test
    void getCooNaveTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        assertNull(model.getCooNave());
    }

    @org.junit.jupiter.api.Test
    void errorSparoPresenteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        Point coordinate = new Point();
        coordinate.x=1;
        coordinate.y=1;
        String info = "Ops.. Hai già sparato alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + "!";
        model.errorSparoPresente(coordinate);
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void errorNaveVicinaTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "Ops.. Stai cercando di posizionare la nave troppo vicino ad un'altra!";
        model.errorNaveVicina();
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void VittoriaGiocatoreTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "CONGRATULAZIONI!!! HAI VINTO!!! La CPU sta piangendo ora MUAHAHAH!!!";
        model.vittoriaGiocatore();
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void VittoriaCpuTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "Hai perso miseramente contro la CPU.. Ci hai provato..";
        model.vittoriaCpu();
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void VittoriaCpuTempoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "Hai perso contro la CPU.. Ci hai impiegato troppo tempo..";
        model.vittoriaCpuTempo();
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void errorNavePresenteTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        Point coordinate = new Point();
        coordinate.x=1;
        coordinate.y=1;
        String info = "Ops.. Hai già posizionato una nave alle coordinate: " + "X = " + coordinate.x + " Y = "+ coordinate.y + "!";
        model.errorNavePresente(coordinate);
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void pensaTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "La CPU sta pensando dove sparare...";
        model.pensa();
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void errorTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "Ops.. Riprova!";
        model.error();
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void setInfoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "Prova";
        model.setInfo(info);
        assertEquals(model.getInfo(), info);
    }

    @org.junit.jupiter.api.Test
    void getInfoTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();

        String info = "Prova";
        model.setInfo(info);
        assertEquals(model.getInfo(), info);
    }
}