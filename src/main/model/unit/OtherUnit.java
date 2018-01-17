package main.model.unit;

import java.io.Serializable;

public enum OtherUnit implements ICalculable, Serializable {
    Unit(1), Dozen(12);

    private double fractionOfUnit;

    OtherUnit(double fractionOfUnit) {
        this.fractionOfUnit = fractionOfUnit;
    }

    public String toString() {
        return this.name();
    }

    public static boolean isValueOf(String str) {
        for (OtherUnit unit : OtherUnit.values()) {
            if (str.equals(unit.toString()))
                return true;
        }
        return false;
    }

    @Override
    public double getValueIn(ICalculable unit) throws UnitConversionException {
        if (unit.getClass().equals(VolumeUnit.class))
            throw new UnitConversionException("Conversion from Simple to Volume unit is not supported");
        if (unit.getClass().equals(MassUnit.class))
            throw new UnitConversionException("Conversion from Simple to Mass unit is not supported");
        return this.fractionOfUnit / unit.getFractionOfBasicUnit();
    }

    @Override
    public double getFractionOfBasicUnit() {
        return fractionOfUnit;
    }

}
