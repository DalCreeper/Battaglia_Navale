package upo.battleship.farrauto.battagliaNavaleView;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.Nave;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class CampoDaGioco extends JPanel {
    private Nave nave;                      //collegamento all'estensione del model (nave)
    private BattagliaNavaleModel model;     //collegamento al model
    private JLabel[][] griglia;

    /**
     * Il costruttore del campo da gioco costruisce la matrice
     * di JLabel sette con sfondo bianco
     *
     * @param dim viene data la dimensione massima della matrice
     */
    public CampoDaGioco(int dim) {
        this.setLayout(new GridLayout(dim, dim));
        griglia = new JLabel[dim][dim];
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                for(int i = 0; i < dim; i++) {
                    for(int j = 0; j < dim; j++) {
                        griglia[i][j] = new JLabel("");
                        griglia[i][j].setPreferredSize(new Dimension(10, 10));

                        //setto l'orientamento orizzontale e verticale, in modo che la "X" risulti centrata
                        griglia[i][j].setHorizontalAlignment(JLabel.CENTER);
                        griglia[i][j].setVerticalAlignment(JLabel.CENTER);

                        //i JLabel sono trasparenti per vedere il colore di sfondo dei JLabel bisogna renderli opachi
                        griglia[i][j].setOpaque(true);

                        //setto il colore dei JLabel a bianco
                        griglia[i][j].setBackground(Color.WHITE);

                        //aggiungo un bordo (creato prima del ciclo)
                        griglia[i][j].setBorder(border);
                        this.add(griglia[i][j]);
            }
        }
        this.setVisible(true);
    }

    /**
     * Il metodo setta in un punto della matrice di JLabel,
     * lo sfondo grigio, che indica una nave piazzata in quel punto
     *
     * @param x viene data la coordinata x della matrice
     * @param y viene data la coordinata y della matrice
     */
    public void aggiungiElemento(int x, int y) {
        griglia[x][y].setBackground(Color.lightGray);
    }

    /**
     * Il metodo setta il testo di un punto della matrice di JLabel
     * con una X, che indica lo sparo avvenuto in quelle coordinate,
     * ma il bersaglio e' stato mancato
     *
     * @param coordinate vengono passate le coordinate per settare
     *                   il punto della matrice
     */
    public void aggiungiSparo(Point coordinate) {
        griglia[coordinate.x][coordinate.y].setText("X");
    }

    /**
     * Il metodo setta lo sfondo di un punto della matrice di JLabel
     * di colore griglio, che indica lo sparo avvenuto in quelle
     * coordinate e il bersaglio e' stato colpito
     *
     * @param coordinate vengono passate le coordinate per settare
     *                   il punto della matrice
     */
    public void bersaglioColpito(Point coordinate) {
        griglia[coordinate.x][coordinate.y].setBackground(Color.lightGray);
        griglia[coordinate.x][coordinate.y].setText("X");
    }

    /**
     * Il metodo setta lo sfondo di un punto della matrice di JLabel
     * di colore rosso, che indica lo sparo avvenuto in quelle
     * coordinate e il bersaglio e' stato colpito e affondato
     *
     * @param coordinate vengono passate le coordinate per settare
     *                   il punto della matrice
     */
    public void bersaglioColpitoEAffondato(Point coordinate) {
        griglia[coordinate.x][coordinate.y].setBackground(Color.RED);
        griglia[coordinate.x][coordinate.y].setText("X");

    }

    /**
     * Il metodo resetta il testo e lo sfondo bianco di tutta la
     * matrice di JLabel
     *
     * @param dim viene data la dimensione massima della matrice
     */
    public void resetGriglia(int dim) {
        for(int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                griglia[i][j].setBackground(Color.white);
                griglia[i][j].setText("");
            }
        }
    }

    /**
     * Il metodo inserisce nella matrice di JLabel l'intestazione
     * di riga e ti colonna che indicano le coordinate
     */
    public void riferimentiGriglia() {
        griglia[0][0].setText("X\\Y");
        griglia[0][0].setBackground(Color.CYAN);

        griglia[0][1].setText("1");
        griglia[0][2].setText("2");
        griglia[0][3].setText("3");
        griglia[0][4].setText("4");
        griglia[0][5].setText("5");
        griglia[0][6].setText("6");
        griglia[0][7].setText("7");
        griglia[0][8].setText("8");
        griglia[0][9].setText("9");
        griglia[0][10].setText("10");

        griglia[0][1].setBackground(Color.GREEN);
        griglia[0][2].setBackground(Color.GREEN);
        griglia[0][3].setBackground(Color.GREEN);
        griglia[0][4].setBackground(Color.GREEN);
        griglia[0][5].setBackground(Color.GREEN);
        griglia[0][6].setBackground(Color.GREEN);
        griglia[0][7].setBackground(Color.GREEN);
        griglia[0][8].setBackground(Color.GREEN);
        griglia[0][9].setBackground(Color.GREEN);
        griglia[0][10].setBackground(Color.GREEN);

        griglia[1][0].setText("1");
        griglia[2][0].setText("2");
        griglia[3][0].setText("3");
        griglia[4][0].setText("4");
        griglia[5][0].setText("5");
        griglia[6][0].setText("6");
        griglia[7][0].setText("7");
        griglia[8][0].setText("8");
        griglia[9][0].setText("9");
        griglia[10][0].setText("10");

        griglia[1][0].setBackground(Color.GREEN);
        griglia[2][0].setBackground(Color.GREEN);
        griglia[3][0].setBackground(Color.GREEN);
        griglia[4][0].setBackground(Color.GREEN);
        griglia[5][0].setBackground(Color.GREEN);
        griglia[6][0].setBackground(Color.GREEN);
        griglia[7][0].setBackground(Color.GREEN);
        griglia[8][0].setBackground(Color.GREEN);
        griglia[9][0].setBackground(Color.GREEN);
        griglia[10][0].setBackground(Color.GREEN);
    }
}
