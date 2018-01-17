package main.model.unit;

import java.io.Serializable;

public enum VolumeUnit implements ICalculable, Serializable {
    Liter(1), Cup(0.25),
    TableSpoon(0.015), TeaSpoon(0.005);

    private double fractionOfLiter;

    VolumeUnit(double fractionOfLiter) {
        this.fractionOfLiter = fractionOfLiter;
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
        if (unit.getClass().equals(MassUnit.class))
            throw new UnitConversionException("Conversion from Volume to Mass unit is not supported");
        if (unit.getClass().equals(OtherUnit.class))
            throw new UnitConversionException("Conversion from Mass to Other unit is not supported");
        return this.fractionOfLiter / unit.getFractionOfBasicUnit();
    }

    @Override
    public double getFractionOfBasicUnit() {
        return fractionOfLiter;
    }
}
