package upo.battleship.farrauto.battagliaNavaleController;

import upo.battleship.farrauto.battagliaNavaleModel.BattagliaNavaleModel;
import upo.battleship.farrauto.battagliaNavaleModel.SalvaCaricaDati;
import upo.battleship.farrauto.battagliaNavaleView.BattagliaNavaleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
public class TimerGioco {
    private Timer timer;
    private long startTime;
    private long diffTime;
    private int decSecondi;
    private int secondi;
    private int minuti;

    /**
     * Il costruttore del timerGioco svolge tutte le
     * azioni relative al timer stesso
     *
     * @param tempo viene passato il tempo iniziale da cui partire
     *              a scalare il tempo
     * @param view viene passata la view
     * @param model viene passato il model
     * @param salvaCaricaDati viene passato il salvaCaricaDati
     * @see BattagliaNavaleView view
     * @see BattagliaNavaleModel model
     * @see SalvaCaricaDati salvaCaricaDati
     */
    public TimerGioco(long tempo, BattagliaNavaleView view, BattagliaNavaleModel model, SalvaCaricaDati salvaCaricaDati) {
        startTime = tempo;
        timer = new Timer (0, new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                diffTime = startTime--;
                if(diffTime == 0) {
                    timer.stop();
                    return;
                }
                decSecondi = (int) (diffTime / 1000 % 60);
                secondi = (int) (diffTime / 60000 % 60);
                minuti = (int) (diffTime / 3600000 % 60);
                String s = String.format ("%d:%02d:%02d", minuti, secondi, decSecondi);
                salvaCaricaDati.setStartTimeSave(startTime);
                view.setLblTime(s);
                if(startTime == 0) {    //se il tempo del turno arriva a 0, la cpu vince in automatico
                    view.bloccoSparo();
                    stop();
                    //aggiorno la JLabel di informazione
                    model.vittoriaCpuTempo();
                    view.setLblInfo(model.getInfo());
                }
            }
        });
    }

    /**
     * Il metodo resetta nella view la label del timer
     *
     * @param view viene passata la view
     * @see BattagliaNavaleView view
     */
    public void resetTimerView(BattagliaNavaleView view) {
        String s = "1:00:00";
        view.setLblTime(s);
    }

    /**
     * Il metodo resetta il timer, reimpostando i valori del timer
     * = al tempo passato come parametro
     *
     * @param tempo viene passato il tempo iniziale da cui partire
                    a scalare il tempo
     */
    public void resetTimer(long tempo) {
        startTime = tempo;
    }

    /**
     * Il metodo fa ripartire il timer
     */
    public void start() {
        timer.start();
    }

    /**
     * Il metodo ferma il timer
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Metodo getter della variabile diffTime
     *
     * @return diffTime
     */
    public long getDiffTime() {
        return diffTime;
    }

    /**
     * Metodo setter del long startTime
     *
     * @param startTime valore dal quale il timer deve
     *                  iniziare a decrementarsi
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}