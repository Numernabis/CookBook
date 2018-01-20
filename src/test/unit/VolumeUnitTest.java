package test.unit;

import main.model.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeUnitTest {

    @Test
    void getValueIn() throws UnitConversionException {
        assertEquals(VolumeUnit.Cup.getValueIn(VolumeUnit.Liter), 0.25);
        assertEquals(VolumeUnit.TeaSpoon.getValueIn(VolumeUnit.Liter), 0.005);
        assertEquals(VolumeUnit.TableSpoon.getValueIn(VolumeUnit.Liter), 0.015);
        assertEquals(VolumeUnit.Liter.getValueIn(VolumeUnit.TeaSpoon), 200);

        assertNotEquals(VolumeUnit.TeaSpoon.getValueIn(VolumeUnit.TableSpoon), 2);
        assertNotEquals(VolumeUnit.Cup.getValueIn(VolumeUnit.Liter), 5);
        assertNotEquals(VolumeUnit.Liter.getValueIn(VolumeUnit.TeaSpoon), 300);
        assertNotEquals(VolumeUnit.TableSpoon.getValueIn(VolumeUnit.Cup), 0.5);
    }

    @Test
    void getValueInThrowsUnitConversionException() {
        assertThrows(UnitConversionException.class, () -> {
            VolumeUnit.Cup.getValueIn(OtherUnit.Dozen);
        });
        assertThrows(UnitConversionException.class, () -> {
            VolumeUnit.TableSpoon.getValueIn(MassUnit.Gram);
        });
        assertThrows(UnitConversionException.class, () -> {
            VolumeUnit.Liter.getValueIn(OtherUnit.Unit);
        });
    }

    @Test
    void isValueOf() {
        assertTrue(VolumeUnit.isValueOf("Liter"));
        assertTrue(VolumeUnit.isValueOf("TableSpoon"));
        assertTrue(VolumeUnit.isValueOf("Cup"));

        assertFalse(VolumeUnit.isValueOf("CoffeeSpoon"));
        assertFalse(VolumeUnit.isValueOf("Wiadro"));
        assertFalse(VolumeUnit.isValueOf("Kubek"));
        assertFalse(VolumeUnit.isValueOf("kielich"));
        assertFalse(VolumeUnit.isValueOf("karafka"));
    }

}

