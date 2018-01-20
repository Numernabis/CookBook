package main.model.unit;

import java.io.Serializable;

public enum MassUnit implements ICalculable, Serializable {
    Kilogram(1), Decagram(0.01), Gram(0.001);

    private double fractionOfKilogram;

    MassUnit(double fractionOfKilogram) {
        this.fractionOfKilogram = fractionOfKilogram;
    }

    public String toString() {
        return this.name();
    }

    public static boolean isValueOf(String str) {
        for (MassUnit unit : MassUnit.values()) {
            if (str.equals(unit.toString()))
                return true;
        }
        return false;
    }

    @Override
    public double getValueIn(ICalculable unit) throws UnitConversionException {
        if (unit.getClass().equals(VolumeUnit.class))
            throw new UnitConversionException("Conversion from Mass to Volume unit is not supported.");
        if (unit.getClass().equals(OtherUnit.class))
            throw new UnitConversionException("Conversion from Mass to Other unit is not supported.");
        return this.fractionOfKilogram / unit.getFractionOfBasicUnit();
    }

    @Override
    public double getFractionOfBasicUnit() {
        return fractionOfKilogram;
    }
}