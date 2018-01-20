package test.recipe;

import main.model.ingredient.Ingredient;
import main.model.recipe.*;
import main.model.unit.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    @Test
    void addIngredient() throws IllegalQuantityException, IllegalUnitException {
        Recipe recipe = new Recipe("scrambled eggs");
        RecipeIngredient ingredient1 = new RecipeIngredient(Ingredient.Onion, OtherUnit.Unit, 2);
        recipe.addIngredient(ingredient1);
        RecipeIngredient ingredient2 = new RecipeIngredient(Ingredient.Egg, OtherUnit.Unit, 5);
        recipe.addIngredient(ingredient2);
        assertEquals(ingredient1, recipe.getRecipeIngredientList().get(0));
        assertEquals(ingredient2, recipe.getRecipeIngredientList().get(1));
        assertNotEquals(ingredient1, recipe.getRecipeIngredientList().get(1));
    }

    @Test
    void removeIngredient() throws IllegalQuantityException, IllegalUnitException {
        Recipe recipe = new Recipe("hazelnut muffin");
        RecipeIngredient ingredient1 = new RecipeIngredient(Ingredient.Hazelnut, MassUnit.Decagram, 7);
        recipe.addIngredient(ingredient1);
        assertEquals(ingredient1, recipe.getRecipeIngredientList().get(0));
        recipe.removeIngredient(0);
        assertTrue(recipe.getRecipeIngredientList().isEmpty());
    }

    @Test
    void addDirection() {
        Recipe recipe = new Recipe("spanish omelette");
        recipe.addDirection("Beat eggs with salt");
        assertFalse(recipe.getDirections().isEmpty());
        assertEquals("Beat eggs with salt", recipe.getDirections().get(0));
    }

    @Test
    void removeDirection() {
        Recipe recipe = new Recipe("omelette");
        recipe.addDirection("Heat oil in a frying pan");
        recipe.removeDirection(0);
        assertTrue(recipe.getDirections().isEmpty());
    }

    @Test
    void isVegetarian() throws IllegalQuantityException, IllegalUnitException {
        Recipe recipe1 = new Recipe("testRecipe");
        RecipeIngredient ingredient1 = new RecipeIngredient(Ingredient.Hazelnut, MassUnit.Decagram, 25);
        recipe1.addIngredient(ingredient1);
        Recipe recipe2 = new Recipe("testRecipe");
        RecipeIngredient ingredient2 = new RecipeIngredient(Ingredient.ChorizoSausage, MassUnit.Gram, 100);
        recipe2.addIngredient(ingredient2);

        assertTrue(recipe1.isVegetarian());
        assertFalse(recipe2.isVegetarian());
    }

    @Test
    void equals() throws IllegalQuantityException, IllegalUnitException {
        Recipe recipe1 = new Recipe("muffin");
        RecipeIngredient ingredient1 = new RecipeIngredient(Ingredient.Butter, VolumeUnit.TeaSpoon, 4);
        recipe1.addIngredient(ingredient1);
        Recipe recipe2 = new Recipe("burger");
        RecipeIngredient ingredient2 = new RecipeIngredient(Ingredient.Beef, MassUnit.Decagram, 30);
        recipe2.addIngredient(ingredient2);
        Recipe recipe3 = new Recipe("muffin");
        RecipeIngredient ingredient3 = new RecipeIngredient(Ingredient.Butter, VolumeUnit.TeaSpoon, 4);
        recipe3.addIngredient(ingredient3);
        Recipe recipe4 = new Recipe("muffin");
        RecipeIngredient ingredient4 = new RecipeIngredient(Ingredient.Egg, OtherUnit.Unit, 3);
        recipe4.addIngredient(ingredient4);

        assertTrue(recipe1.equals(recipe3));
        assertTrue(recipe1.equals(recipe1));
        assertFalse(recipe2.equals(recipe3));
        assertFalse(recipe3.equals(recipe4));
    }

}