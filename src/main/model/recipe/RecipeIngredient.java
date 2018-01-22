package main.model.recipe;

import main.model.ingredient.Ingredient;
import main.model.unit.*;

import java.io.Serializable;

public class RecipeIngredient implements Serializable {
    private Ingredient ingredient;
    private ICalculable unit;
    private double quantity;

    public RecipeIngredient(Ingredient ingredient, ICalculable unit, double quantity)
            throws IllegalQuantityException, IllegalUnitException {
        setQuantity(quantity);
        if (!ingredient.isProperUnit(unit))
            throw new IllegalUnitException(unit, ingredient);
        this.ingredient = ingredient;
        this.unit = unit;
    }

    public RecipeIngredient(Ingredient ingredient, ICalculable unit)
            throws IllegalQuantityException, IllegalUnitException {
        this(ingredient, unit, 1);
    }

    public void convertToUnit(ICalculable unit) throws UnitConversionException {
        double fraction = this.unit.getValueIn(unit);
        quantity *= fraction;
        this.unit = unit;
    }

    public void setQuantity(double quantity) throws IllegalQuantityException {
        if (quantity < 0)
            throw new IllegalQuantityException();
        this.quantity = quantity;
    }

    public double getQuantity() { return quantity; }

    public Ingredient getIngredient() {
        return ingredient;
    }

    @Override
    public String toString() {
        return Double.toString(quantity) + " " + unit.toString() + " " + ingredient.toString();
    }

    @Override
    public boolean equals(Object obj) {
        final double E = 0.0001;
        if (obj == null)
            return false;
        if (!(obj instanceof RecipeIngredient))
            return false;
        if (this.ingredient != ((RecipeIngredient) obj).ingredient)
            return false;
        double thisQuantity = this.unit.getFractionOfBasicUnit() * this.quantity;
        double objQuantity = (((RecipeIngredient) obj).unit).getFractionOfBasicUnit() * ((RecipeIngredient) obj).quantity;
        return (thisQuantity - objQuantity < E);
    }

}
