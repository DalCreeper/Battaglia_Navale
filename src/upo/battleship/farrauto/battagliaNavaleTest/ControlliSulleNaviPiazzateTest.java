package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.ControlliSulleNaviPiazzate;
import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class ControlliSulleNaviPiazzateTest {
    @org.junit.jupiter.api.Test
    void controlloNaviVicineTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        ControlliSulleNaviPiazzate controlli= new ControlliSulleNaviPiazzate();

        Boolean[][] spazioNaviTest = new Boolean[11][11];

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                spazioNaviTest[i][j] = false;
            }
        }

        //croce
        spazioNaviTest[5][5] = true;
        spazioNaviTest[5][4] = true;
        spazioNaviTest[5][6] = true;
        spazioNaviTest[6][5] = true;
        spazioNaviTest[4][5] = true;

        //x
        spazioNaviTest[4][4] = true;
        spazioNaviTest[6][4] = true;
        spazioNaviTest[6][6] = true;
        spazioNaviTest[4][6] = true;

        model.setSpazioNavi(spazioNaviTest);

        //controllo sommergibile
        Point puntoTest1 = new Point();
        puntoTest1.x = 5;
        puntoTest1.y = 5;

        assertTrue(controlli.controlloNaviVicine(puntoTest1, true, 0, 0, model, 11));

        //controllo torpediniere
        //controllo orizzontale
        Point puntoTest2 = new Point();
        puntoTest2.x = 5;
        puntoTest2.y = 5;

        assertTrue(controlli.controlloNaviVicine(puntoTest2, true, 0, 1, model, 11));

        //controllo verticale
        assertTrue(controlli.controlloNaviVicine(puntoTest2, true, 1, 1, model, 11));

        //controllo incrociatore
        //controllo orizzontale
        Point puntoTest3 = new Point();
        puntoTest3.x = 5;
        puntoTest3.y = 5;

        assertTrue(controlli.controlloNaviVicine(puntoTest3, true, 0, 2, model, 11));

        //controllo verticale
        assertTrue(controlli.controlloNaviVicine(puntoTest3, true, 1, 2, model, 11));

        //controllo portaerei
        //controllo orizzontale
        Point puntoTest4 = new Point();
        puntoTest4.x = 5;
        puntoTest4.y = 5;

        assertTrue(controlli.controlloNaviVicine(puntoTest4, true, 0, 3, model, 11));

        //controllo verticale
        assertTrue(controlli.controlloNaviVicine(puntoTest4, true, 1, 3, model, 11));
    }

    @org.junit.jupiter.api.Test
    void controlloNaviPosizionateTest() {
        BattagliaNavaleModel model = new BattagliaNavaleModel();
        ControlliSulleNaviPiazzate controlli= new ControlliSulleNaviPiazzate();

        Boolean[][] spazioNaviTest = new Boolean[11][11];
        ArrayList<Nave> naviSfidante = new ArrayList<>(10);

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                spazioNaviTest[i][j] = false;
            }
        }

        spazioNaviTest[1][1] = true;

        model.setSpazioNavi(spazioNaviTest);

        Point puntoTest1 = new Point();
        puntoTest1.x = 1;
        puntoTest1.y = 1;
        Nave nave1 = new Nave(puntoTest1, 0, 0, 1);
        model.getGiocatore().aggiungiNave(nave1);
        model.piazzaNaveNellaMappa(nave1,true, 11);
        model.getGiocatore().aggiungiNave(nave1);

        assertTrue(controlli.controlloNaviPosizionate(puntoTest1, true, model, 11));

        Point puntoTest2 = new Point();
        puntoTest2.x = 3;
        puntoTest2.y = 3;

        assertFalse(controlli.controlloNaviPosizionate(puntoTest2, true, model, 11));
    }
}