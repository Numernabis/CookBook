package test.ingredient;

import main.model.ingredient.Ingredient;
import main.model.unit.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    @Test
    void isProperUnit() {
        assertTrue(Ingredient.ChorizoSausage.isProperUnit(MassUnit.Gram));
        assertTrue(Ingredient.Egg.isProperUnit(OtherUnit.Unit));
        assertTrue(Ingredient.Butter.isProperUnit(VolumeUnit.Cup));
        assertTrue(Ingredient.Beef.isProperUnit(MassUnit.Decagram));

        assertFalse(Ingredient.VegetableOil.isProperUnit(MassUnit.Gram));
        assertFalse(Ingredient.Flour.isProperUnit(OtherUnit.Dozen));
        assertFalse(Ingredient.Hazelnut.isProperUnit(VolumeUnit.Cup));
        assertFalse(Ingredient.Onion.isProperUnit(MassUnit.Kilogram));
    }
}