package upo.battleship.farrauto.battagliaNavaleModel;

import java.awt.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class ControlliSulleNaviPiazzate {

    /**
     * Il metodo controlla se le coordinate passategli
     * per posizionare una nave, rispettano il distanziamento
     * di 1 cella da un'altra eventuale nave
     * <p>
     * Il metodo basta che ritorni un valore maggiore di 0 per capire
     * se la nave che si era intenzionati a piazzare andrebbe ad
     * interferire con un'altra gia' piazzata
     * </p>
     *
     * @param coordinate vengono date le coordinate dove inserire la nave
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param orientamento viene indicato l'orientamento della nave
     * @param lunghezzaNave viene indicata la lunghezza della nave
     * @param model viene passato il model
     * @param dim viene data la dimensione massima delle griglie
     * @see BattagliaNavaleModel model
     * @return true ritorna se il contatore e' maggiore di 0 e quindi
     *              vuol dire che vi erano navi vicine
     *         false ritorna se il contatore rimane a 0 e quindi vuol
     *               dire che non sono presenti navi nelle vicinanze di
     *               quelle coordinate
     */
    public boolean controlloNaviVicine(Point coordinate, boolean tipo, int orientamento, int lunghezzaNave, BattagliaNavaleModel model, int dim) {       //la lunghezzaNave presa in ingresso non è ancora stata ridefinita, quindi avrò i valori dell'index della JComboBox
        int cont = 0;                                       //inizializzo a 0 il contatore di errori
        Boolean[][] spazioNavi;                             //inizializzo la griglia booleana di spazioNavi

        if(tipo) {
            spazioNavi = model.getSpazioNavi();             //collego lo spazionavi del model con un nuovo spazioNavi locale
        } else {
            spazioNavi = model.getSpazioNaviCpu();          //collego lo spazionaviCpu del model con un nuovo spazioNavi locale
            lunghezzaNave--;                                //diminuisco di 1 di la lunghezza della nave, così corrisponde all'index della JComboBox del tipoNave
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                if(spazioNavi[i][j]) {
                    if (lunghezzaNave == 0) {                                       //controllo sommergibile sia orizzontale che verticale in quanto consiste in una sola coppia di coordinate
                        if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                            cont++;
                        }
                        if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                            cont++;
                        }
                        if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                            cont++;
                        }
                        if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                            cont++;
                        }
                        if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                            cont++;
                        }
                        if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                            cont++;
                        }
                        if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                            cont++;
                        }
                        if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                            cont++;
                        }
                    }
                    if(orientamento == 0) {
                        if (lunghezzaNave == 1) {                                       //controllo torpediniere orizzontale
                            if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if (coordinate.y > 8) {
                                if ((coordinate.x == i) && (coordinate.y - 2 == j)) {
                                    cont++;
                                }
                                if ((coordinate.x == i) && (coordinate.y - 3 == j)) {
                                    cont++;
                                }
                            }
                        }
                        if (lunghezzaNave == 2) {                                       //controllo incrociatore orizzontale
                            if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 3 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 3 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 3 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if (coordinate.y > 8) {
                                if ((coordinate.x == i) && (coordinate.y - 2 == j)) {
                                    cont++;
                                }
                                if ((coordinate.x == i) && (coordinate.y - 3 == j)) {
                                    cont++;
                                }
                            }
                        }
                        if (lunghezzaNave == 3) {                                       //controllo portaerei orizzontale
                            if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 3 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 4 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 3 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 4 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 2 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 3 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 4 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if (coordinate.y > 8) {
                                if ((coordinate.x == i) && (coordinate.y - 2 == j)) {
                                    cont++;
                                }
                                if ((coordinate.x == i) && (coordinate.y - 3 == j)) {
                                    cont++;
                                }
                            }
                        }
                    }
                    if(orientamento == 1) {
                        if (lunghezzaNave == 1) {                                       //controllo torpediniere verticale
                            if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if (coordinate.x > 8) {
                                if ((coordinate.x - 2 == i) && (coordinate.y == j)) {
                                    cont++;
                                }
                                if ((coordinate.x - 3 == i) && (coordinate.y == j)) {
                                    cont++;
                                }
                            }
                        }
                        if (lunghezzaNave == 2) {                                       //controllo incrociatore verticale
                            if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 3 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 3 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 3 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if (coordinate.x > 8) {
                                if ((coordinate.x - 2 == i) && (coordinate.y == j)) {
                                    cont++;
                                }
                                if ((coordinate.x - 3 == i) && (coordinate.y == j)) {
                                    cont++;
                                }
                            }
                        }
                        if (lunghezzaNave == 3) {                                       //controllo portaerei verticale
                            if ((coordinate.x + 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 3 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 4 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 3 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 4 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 2 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 3 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x + 4 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y - 1 == j)) {
                                cont++;
                            }
                            if ((coordinate.x - 1 == i) && (coordinate.y + 1 == j)) {
                                cont++;
                            }
                            if (coordinate.x > 8) {
                                if ((coordinate.x - 2 == i) && (coordinate.y == j)) {
                                    cont++;
                                }
                                if ((coordinate.x - 3 == i) && (coordinate.y == j)) {
                                    cont++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return cont > 0;
    }

    /**
     * Il metodo controlla se le coordinate passategli corrispondono
     * ad una nave gia' piazzata
     *
     * @param coordinate vengono date le coordinate dove inserire la nave
     * @param tipo viene indicato di chi e' il turno (true = giocatore
     *             e false = cpu)
     * @param model viene passato il model
     * @param dim viene data la dimensione massima delle griglie
     * @see BattagliaNavaleModel model
     * @return true ritorna se la nave e' presente
     *         false ritorna se non e' presente alcuna nave
     */
    public boolean controlloNaviPosizionate(Point coordinate, boolean tipo, BattagliaNavaleModel model, int dim) {
        Boolean[][] spazioNavi;

        if(tipo) {
            spazioNavi = model.getSpazioNavi();             //collego lo spazionavi del model con un nuovo spazioNavi locale
        } else {
            spazioNavi = model.getSpazioNaviCpu();          //collego lo spazionaviCpu del model con un nuovo spazioNavi locale
        }

        for(int i = 1; i < dim; i++) {
            for(int j = 1; j < dim; j++) {
                if(spazioNavi[i][j]) {
                    if((coordinate.x == i) && (coordinate.y == j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
