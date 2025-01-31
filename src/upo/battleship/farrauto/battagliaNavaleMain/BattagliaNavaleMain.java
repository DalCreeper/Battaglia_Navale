package upo.battleship.farrauto.battagliaNavaleMain;

import upo.battleship.farrauto.battagliaNavaleController.BattagliaNavaleController;
import upo.battleship.farrauto.battagliaNavaleController.MenuOptions;
import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleView.BattagliaNavaleView;

import javax.swing.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class BattagliaNavaleMain {

    /**
     * Il metodo Main e' il cuore del gioco, fa partire
     * tutto il modello MVC
     *
     * @param args e' un parametro preimpostato
     */
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { //invocazione metodi di SWING tramite thread, usando la SWING utility sopra
                BattagliaNavaleView view = new BattagliaNavaleView("Battaglia Navale");
                BattagliaNavaleModel model = new BattagliaNavaleModel();
                BattagliaNavaleController controller = new BattagliaNavaleController(view, model);
                MenuOptions menuOptions = new MenuOptions(view, model, controller);
            }
        });
    }
}
