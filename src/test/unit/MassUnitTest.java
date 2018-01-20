package test.unit;

import main.model.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassUnitTest {

    @Test
    void getValueIn() throws UnitConversionException {
        assertEquals(MassUnit.Kilogram.getValueIn(MassUnit.Gram), 1000);
        assertEquals(MassUnit.Decagram.getValueIn(MassUnit.Gram), 10);
        assertEquals(MassUnit.Decagram.getValueIn(MassUnit.Kilogram), 0.01);
        assertEquals(MassUnit.Kilogram.getValueIn(MassUnit.Decagram), 100);

        assertNotEquals(MassUnit.Kilogram.getValueIn(MassUnit.Gram), 10);
        assertNotEquals(MassUnit.Gram.getValueIn(MassUnit.Decagram), 0.01);
        assertNotEquals(MassUnit.Decagram.getValueIn(MassUnit.Kilogram), 100);
        assertNotEquals(MassUnit.Gram.getValueIn(MassUnit.Kilogram), 0.1);
    }

    @Test
    void getValueInThrowsUnitConversionException() {
        assertThrows(UnitConversionException.class, () -> {
            MassUnit.Kilogram.getValueIn(OtherUnit.Dozen);
        });
        assertThrows(UnitConversionException.class, () -> {
            MassUnit.Gram.getValueIn(VolumeUnit.TeaSpoon);
        });
        assertThrows(UnitConversionException.class, () -> {
            MassUnit.Decagram.getValueIn(OtherUnit.Unit);
        });
    }


    @Test
    void isValueOf() {
        assertTrue(MassUnit.isValueOf("Kilogram"));
        assertTrue(MassUnit.isValueOf("Decagram"));
        assertTrue(MassUnit.isValueOf("Gram"));

        assertFalse(MassUnit.isValueOf("TeaSpoon"));
        assertFalse(MassUnit.isValueOf("Pudło"));
        assertFalse(MassUnit.isValueOf("Kontener"));
        assertFalse(MassUnit.isValueOf("ziemniak"));
        assertFalse(MassUnit.isValueOf("karat"));
        assertFalse(MassUnit.isValueOf("złoto"));
    }

}
