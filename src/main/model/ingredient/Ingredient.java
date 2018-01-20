package main.model.ingredient;

import main.model.unit.ICalculable;
import main.model.unit.MassUnit;
import main.model.unit.OtherUnit;
import main.model.unit.VolumeUnit;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum Ingredient implements Serializable {
    // name( isMeat, units )
    Hazelnut(false, Arrays.asList(MassUnit.values())),
    Beef(true, Arrays.asList(MassUnit.values())),
    ChorizoSausage(true, Arrays.asList(MassUnit.values())),
    Rice(false, Arrays.asList(MassUnit.values())),
    Flour(false, Arrays.asList(MassUnit.values())),
    Salt(false, Arrays.asList(MassUnit.values())),
    Potatoes(false, Arrays.asList(MassUnit.values())),
    VegetableOil(false, Arrays.asList(VolumeUnit.values())),
    Butter(false, Arrays.asList(VolumeUnit.values())),
    Onion(false, Arrays.asList(OtherUnit.values())),
    Egg(false, Arrays.asList(OtherUnit.values()));

    private boolean meat;
    private List<Enum> properUnits;

    Ingredient(boolean meat, List<Enum> properArguments) {
        this.meat = meat;
        this.properUnits = properArguments;
    }

    public String toString() {
        return this.name();
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