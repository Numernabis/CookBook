package test.unit;

import main.model.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtherUnitTest {
    @Test
    void getValueIn() throws UnitConversionException {
        assertEquals(OtherUnit.Unit.getValueIn(OtherUnit.Unit), 1);
        assertEquals(OtherUnit.Dozen.getValueIn(OtherUnit.Unit), 12);

        assertNotEquals(OtherUnit.Dozen.getValueIn(OtherUnit.Unit), 15);
        assertNotEquals(OtherUnit.Unit.getValueIn(OtherUnit.Dozen), 0.08);
    }

    @Test
    void getValueInThrowsUnitConversionException() {
        assertThrows(UnitConversionException.class, () -> {
            OtherUnit.Unit.getValueIn(MassUnit.Gram);
        });
        assertThrows(UnitConversionException.class, () -> {
            OtherUnit.Dozen.getValueIn(VolumeUnit.TeaSpoon);
        });
        assertThrows(UnitConversionException.class, () -> {
            OtherUnit.Dozen.getValueIn(MassUnit.Decagram);
        });
    }


    @Test
    void isValueOf() {
        assertTrue(OtherUnit.isValueOf("Unit"));
        assertTrue(OtherUnit.isValueOf("Dozen"));

        assertFalse(OtherUnit.isValueOf("TableSpoon"));
        assertFalse(OtherUnit.isValueOf("Paczka"));
        assertFalse(OtherUnit.isValueOf("Paleta"));
    }

}
