package upo.battleship.farrauto.battagliaNavaleTest;

import upo.battleship.farrauto.battagliaNavaleModel.Coordinate;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Loris Farrauto
 * @author Stefano Alcamo
 */
@SuppressWarnings("unused, FieldCanBeLocal, SpellCheckingInspection")
class CoordinateTest {
    private final Coordinate coordinateClass = new Coordinate();

    @org.junit.jupiter.api.Test
    void verificaTutto() {
        assertTrue(this.coordinateClass.verificaTutto("1", "1"));
        assertFalse(this.coordinateClass.verificaTutto("1", "11"));
        assertFalse(this.coordinateClass.verificaTutto("0", "10"));
        assertFalse(this.coordinateClass.verificaTutto("1", "f"));
        assertFalse(this.coordinateClass.verificaTutto("1", ""));
    }

    @org.junit.jupiter.api.Test
    void verificaFieldVuoto() {
        assertTrue(coordinateClass.verificaFieldVuoto("1", "1"));
        assertFalse(coordinateClass.verificaFieldVuoto("", "1"));
        assertFalse(coordinateClass.verificaFieldVuoto("1", ""));
        assertFalse(coordinateClass.verificaFieldVuoto("", ""));
    }

    @org.junit.jupiter.api.Test
    void verificaFormatoField() {
        assertTrue(coordinateClass.verificaFormatoField("1","1"));
        assertFalse(coordinateClass.verificaFormatoField("f","1"));
        assertFalse(coordinateClass.verificaFormatoField("1","f"));
    }

    @org.junit.jupiter.api.Test
    void trasformazioneField() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;

        coordinateClass.trasformazioneField("1", "1");
        assertEquals(coordinateClass.getCoordinate(), puntoTest);
    }

    @org.junit.jupiter.api.Test
    void verificaGrandezzaNumero() {
        Point puntoTest = new Point();
        puntoTest.x = 1;
        puntoTest.y = 1;
        coordinateClass.setCoordinate(puntoTest);

        assertTrue(coordinateClass.verificaGrandezzaNumero(puntoTest));

        puntoTest.x = 0;
        puntoTest.y = 1;
        coordinateClass.setCoordinate(puntoTest);
        assertFalse(coordinateClass.verificaGrandezzaNumero(puntoTest));

        puntoTest.x = 1;
        puntoTest.y = 0;
        coordinateClass.setCoordinate(puntoTest);
        assertFalse(coordinateClass.verificaGrandezzaNumero(puntoTest));


        puntoTest.x = 11;
        puntoTest.y = 1;
        coordinateClass.setCoordinate(puntoTest);
        assertFalse(coordinateClass.verificaGrandezzaNumero(puntoTest));

        puntoTest.x = 1;
        puntoTest.y = 11;
        coordinateClass.setCoordinate(puntoTest);
        assertFalse(coordinateClass.verificaGrandezzaNumero(puntoTest));
    }

    @org.junit.jupiter.api.Test
    void verificaContatore() {
        assertTrue(coordinateClass.verificaContatore(3));
        assertFalse(coordinateClass.verificaContatore(0));
        assertFalse(coordinateClass.verificaContatore(1));
        assertFalse(coordinateClass.verificaContatore(2));
    }

    @org.junit.jupiter.api.Test
    public void setCoordinate() {
        Point testPoint = new Point();
        testPoint.x = 1;
        testPoint.y = 1;
        coordinateClass.setCoordinate(testPoint);
        assertEquals(coordinateClass.getCoordinate(), testPoint);
    }

    @org.junit.jupiter.api.Test
    public void getCoordinate() {
        Point testPoint = new Point();
        testPoint.x = 1;
        testPoint.y = 1;
        coordinateClass.setCoordinate(testPoint);
        Point coo = coordinateClass.getCoordinate();
        assertEquals(testPoint, coo);
    }
}