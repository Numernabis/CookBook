package test.recipe;

import main.model.ingredient.Ingredient;
import main.model.recipe.*;
import main.model.unit.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeIngredientTest {
    @Test
    void equals() throws IllegalQuantityException, IllegalUnitException {
        RecipeIngredient ingredient1 = new RecipeIngredient(Ingredient.Flour, MassUnit.Gram, 1000);
        RecipeIngredient ingredient2 = new RecipeIngredient(Ingredient.Flour, MassUnit.Kilogram, 1);
        RecipeIngredient ingredient3 = new RecipeIngredient(Ingredient.Potatoes, MassUnit.Decagram, 30);
        RecipeIngredient ingredient4 = new RecipeIngredient(Ingredient.Flour, MassUnit.Decagram, 100);
        RecipeIngredient ingredient5 = new RecipeIngredient(Ingredient.Salt, MassUnit.Gram, 3);
        RecipeIngredient ingredient6 = new RecipeIngredient(Ingredient.VegetableOil, VolumeUnit.TableSpoon);
        RecipeIngredient ingredient7 = new RecipeIngredient(Ingredient.VegetableOil, VolumeUnit.TableSpoon, 2);

        assertFalse(ingredient1.equals(ingredient3));
        assertFalse(ingredient3.equals(ingredient5));
        assertTrue(ingredient1.equals(ingredient2));
        assertTrue(ingredient2.equals(ingredient4));
        assertTrue(ingredient6.equals(ingredient7));
    }

    @Test
    void convertToMeasureUnit() throws IllegalQuantityException, IllegalUnitException, UnitConversionException {
        RecipeIngredient ingredient1 = new RecipeIngredient(Ingredient.Flour, MassUnit.Kilogram, 1);
        RecipeIngredient ingredient2 = new RecipeIngredient(Ingredient.Potatoes, MassUnit.Decagram, 30);
        RecipeIngredient ingredient3 = new RecipeIngredient(Ingredient.Flour, MassUnit.Gram, 1000);
        RecipeIngredient ingredient4 = new RecipeIngredient(Ingredient.Potatoes, MassUnit.Gram, 300);
        ingredient3.convertToUnit(MassUnit.Kilogram);
        ingredient4.convertToUnit(MassUnit.Decagram);

        assertTrue(ingredient1.equals(ingredient3));
        assertTrue(ingredient2.equals(ingredient4));
        assertFalse(ingredient2.equals(ingredient3));
        assertFalse(ingredient1.equals(ingredient4));
    }
}