package main.model.ingredient;

import main.model.unit.ICalculable;
import main.model.unit.MassUnit;
import main.model.unit.OtherUnit;
import main.model.unit.VolumeUnit;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum Ingredient implements Serializable {
    // name( price, isMeat, units )
    Onion(1.6, false, Arrays.asList(OtherUnit.values())),
    Egg(0.4, false, Arrays.asList(OtherUnit.values())),
    Garlic(0.9, false, Arrays.asList(OtherUnit.values())),
    Celery(0.7, false, Arrays.asList(OtherUnit.values())),

    VegetableOil(3.5, false, Arrays.asList(VolumeUnit.values())),
    Oil(3.1, false, Arrays.asList(VolumeUnit.values())),
    Butter(5, false, Arrays.asList(VolumeUnit.values())),
    Milk(2.1, false, Arrays.asList(VolumeUnit.values())),
    Water(0.1, false, Arrays.asList(VolumeUnit.values())),

    Hazelnut(30, false, Arrays.asList(MassUnit.values())),
    Beef(20, true, Arrays.asList(MassUnit.values())),
    ChorizoSausage(15, true, Arrays.asList(MassUnit.values())),
    Rice(2, false, Arrays.asList(MassUnit.values())),
    Flour(2.5, false, Arrays.asList(MassUnit.values())),
    Salt(1.2, false, Arrays.asList(MassUnit.values())),
    Potatoes(1.1, false, Arrays.asList(MassUnit.values()));

    private double price;
    private boolean meat;
    private List<Enum> properUnits;

    Ingredient(double price, boolean meat, List<Enum> properUnits) {
        this.price = price;
        this.meat = meat;
        this.properUnits = properUnits;
    }

    public String toString() {
        return this.name();
    }

    public double getPrice() {
        return price;
    }

    public boolean isMeat() {
        return meat;
    }

    public boolean isProperUnit(Object unit) {
        if (!(unit instanceof ICalculable))
            return false;
        for (Enum properUnit : properUnits) {
            if (unit.getClass().equals(properUnit.getClass()))
                return true;
        }
        return false;
    }
}