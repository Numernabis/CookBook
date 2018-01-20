package main.model.recipe;

import main.model.unit.ICalculable;
import main.model.ingredient.Ingredient;

public class IllegalUnitException extends Exception {
    public IllegalUnitException() {
        super("Given unit is not proper for this Ingredient");
    }

    public IllegalUnitException(ICalculable unit, Ingredient ingredient) {
        super("Given unit: " + unit.toString() +" is not proper for: " + ingredient.toString());
    }
}
