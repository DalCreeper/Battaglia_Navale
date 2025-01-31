package upo.battleship.farrauto.battagliaNavaleModel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class Coordinate {
    private JFrame dialog;                      //mini frame per visualizzare i messaggi d'errore
    private int cont;                           //contatore per capire se le coordinate inserite sono entrambe valide
    private Point coordinate;                   //variabile di tipo Point che contiene entrambe le coordinate

    /**
     * Il metodo verifica che tutte condizioni restituite
     * dagli altri metodi di controllo sulle coordinate
     * inserite dall'utente siano valide
     *
     * @param fieldX viene passato il contenuto del
     *               txtCoordinataX (posiziona o spara)
     * @param fieldY viene passato il contenuto del
     *               txtCoordinataY (posiziona o spara)
     * @return true ritorna se tutte le condizioni sono
     *              state verificate e hanno restituito true
     *         false ritorna se alemno 1 delle condizioni
     *               verificate non ha restituito true
     */
    public boolean verificaTutto(String fieldX, String fieldY) {
        cont = 0;
        if(this.verificaFieldVuoto(fieldX, fieldY)) {
            //verifica se il contenuto dei field è vuoto o meno, se non lo è incremento il contatore
            cont++;
        } else {
            //reinizializzo il contatore a 0 perchè il field era vuoto
            cont = 0;
            return false;
        }

        if(this.verificaFormatoField(fieldX, fieldY)) {
            //verifica se il formato del contenuto dei field sono effettivamente dei numeri
            cont++;
        } else {
            //reinizializzo il contatore a 0 perchè la verifica del formato non è andato a buon fine
            cont = 0;
            return false;
        }

        //trasformo le 2 coordinate in un'unica variabile di tipo Point
        this.trasformazioneField(fieldX, fieldY);

        if(this.verificaGrandezzaNumero(coordinate)) {
            //verifica che i numeri inseriti siano compresi tra 1 e 10, se avviene con successo incremento il contatore
            cont++;
        } else {
            //reinizializzo il contatore a 0 perchè la verifica della grandezza delle coordinate non è andata a buon fine
            cont = 0;
            return false;
        }

        if(this.verificaContatore(cont)) {
            //reizniializzo il contatore a 0
            cont = 0;
            return true;
        } else {
            //reinizializzo il contatore a 0
            cont = 0;
            return false;
        }
    }

    /**
     * Il metodo verifica se uno o entrambi i field
     * sono vuoti
     *
     * @param x viene passato il contenuto del field della x
     * @param y viene passato il contenuto del field della y
     * @return true ritorna se i field non sono vuoti
     *         false ritorna se uno o entrambi i field non
     *               sono vuoti
     */
    public boolean verificaFieldVuoto(String x, String y) {
        if((x.equals("") || y.equals("")) || (x.length() == 0 || y.length() == 0)) {
            //verifica se è stato inserito qualcosa nei JTextField, se non c'è nulla stampa l'apposito messaggio di errore
            dialog = new JFrame();
            JOptionPane.showMessageDialog(dialog, "Non hai inserito nessuna coordinata!", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Il metodo verifica tramite un regex se vengono
     * effettivamente inseriti numeri
     *
     * @param x viene passato il contenuto del field della x
     * @param y viene passato il contenuto del field della y
     * @return true ritorna se nei field ci sono numeri
     *         false ritorna se in uno o in entrambi i field
     *               non ci sono numeri
     */
    public boolean verificaFormatoField(String x, String y) {
        if((x.matches("[0-9]+")) && (y.matches("[0-9]+"))) {
            return true;
        } else {
            //la verifica del formato non è andata a buon fine, quindi stampo l'apposito messaggio di errore
            JOptionPane.showMessageDialog(dialog, "Il formato di una delle 2 coordinate non è corretto!\nSono consentiti solo numeri!", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Il metodo trasforma il contenuto dei field delle
     * coordinate in numeri interi
     *
     * @param x viene passato il contenuto del field della x
     * @param y viene passato il contenuto del field della y
     */
    public void trasformazioneField(String x, String y) {
        int X;
        int Y;
        //trasforma il contenuto dei field in interi
        X = Integer.parseInt(x);
        Y = Integer.parseInt(y);
        //inserisco le coordinate in un'unica variale di tipo Point per ridurre le operazioni da svolgere
        coordinate = new Point(X, Y);
    }

    /**
     * Il metodo verifica se i numeri inseriti nei field
     * rispettano la grandezza delle griglie, ovvero numeri
     * compresi tra 1 e 10 compresi
     *
     * @param coordinate vengono passate le coordinate per
     *                   effettuare i controlli
     * @return true ritorna se le coordinate rispettano
     *              la grandezza delle griglie
     *         false ritorna se le coordinate eccedono dalle
     *               grandezze delle griglie
     */
    public boolean verificaGrandezzaNumero(Point coordinate) {
        if((coordinate.x >= 1 && coordinate.x <= 10) && (coordinate.y >= 1 && coordinate.y <= 10)) {
            return true;
        } else {
            //la verifica della grandezza delle coordinate non è andata a buon fine, quindi stampo a video un MessageDialog che indica l'errore
            dialog = new JFrame();
            JOptionPane.showMessageDialog(dialog, "COORDINATE NON VALIDE!\nSi prega di rispettare la grandezza del campo da gioco!\n(numeri da 1 a 10)\nModificare il campo o i campi sbagliati!", "Errore" , JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Il metodo controlla se il contatore si e' incrementato
     * correttamente
     * <p>
     * Se e' = 3 allora tutti i metodi di controllo hanno
     * avuto esito positivo; in genere non tornerà mai false
     * se i metodi precedenti sono corretti
     * </p>
     *
     * @param contatore viene passato il valore dal metodo
     *                  principale
     * @return true ritorna se tutti i metodi di verifica
     *              hanno avuto esito positivo
     *         false ritorna se per qualche motivo non e'
     *               stato mostrato un messaggio di errore
     *               da parte dei metodi chiamati precedentemente
     */
    public boolean verificaContatore(int contatore) {
        if(contatore == 3) {
            return true;
        } else {
            //il contatore non si è incrementato correttamente o c'è stato qualche errore, quindi stampo a video un MessageDialog che indica l'errore
            dialog = new JFrame();
            JOptionPane.showMessageDialog(dialog, "OPS qualcosa è andato storto!\nProva a reinserire le coordinate!", "Errore" , JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Metodo getter del Point coordinate
     *
     * @return coordinate
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Metodo setter del Point coordinate
     *
     * @param coordinate viene passato il Point modificato
     */
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
