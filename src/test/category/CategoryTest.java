package test.category;

import main.model.category.*;
import main.model.ingredient.Ingredient;
import main.model.recipe.*;
import main.model.unit.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void addRecipe() throws DuplicateRecipeException {
        Category testCategory = new Category("testCategory");
        Recipe recipe = new Recipe("omelette");
        testCategory.addRecipe(recipe);
        assertEquals(testCategory.getRecipe("omelette"), recipe);
        assertThrows(DuplicateRecipeException.class, () -> testCategory.addRecipe(recipe));
    }

    @Test
    void removeRecipe() throws DuplicateRecipeException {
        Category testCategory = new Category("testCategory");
        Recipe recipe = new Recipe("muffin");
        testCategory.addRecipe(recipe);
        assertTrue(testCategory.getRecipe("muffin") != null);
        testCategory.removeRecipe("muffin");
        assertTrue(testCategory.getRecipe("muffin") == null);
    }

    @Test
    void renameRecipe() throws IllegalQuantityException, IllegalUnitException,
            DuplicateRecipeException, NotFoundRecipeException {
        Category testCategory = new Category("testCategory");
        Recipe recipe = new Recipe("muffin");
        testCategory.addRecipe(recipe);
        Recipe recipe2 = new Recipe("chilli");
        testCategory.addRecipe(recipe2);
        testCategory.renameRecipe("muffin", "chocolate muffin");
        assertEquals(testCategory.getRecipe("chocolate muffin"), recipe);
        assertThrows(DuplicateRecipeException.class,
                () -> testCategory.renameRecipe("chocolate muffin", "chilli"));
    }

    @Test
    void getRecipes() throws DuplicateRecipeException, IllegalQuantityException, IllegalUnitException {
        Category testCategory = new Category("testCategory");
        Recipe recipe = new Recipe("burger");
        recipe.addIngredient(new RecipeIngredient(Ingredient.Beef, MassUnit.Kilogram, 1));
        testCategory.addRecipe(recipe);
        Recipe recipe2 = new Recipe("pancake");
        recipe2.addIngredient(new RecipeIngredient(Ingredient.Egg, OtherUnit.Unit, 1));
        testCategory.addRecipe(recipe2);
        Recipe recipe3 = new Recipe("omelette");
        recipe.addIngredient(new RecipeIngredient(Ingredient.Butter, VolumeUnit.TableSpoon, 1));
        testCategory.addRecipe(recipe3);

        assertEquals(testCategory.getRecipes(Recipe::isVegetarian).size(), 2);
        assertNotEquals(testCategory.getRecipes(Recipe::isVegetarian).size(), 1);
    }

    @Test
    void equals() throws DuplicateRecipeException, IllegalQuantityException, IllegalUnitException {
        Category testCat1 = new Category("testCat1");
        Category testCat2 = new Category("testCat2");
        Category testCat3 = new Category("testCat3");
        Category testCat4 = new Category("testCat1");
        Recipe recipe1 = new Recipe("muffin");
        recipe1.addIngredient(new RecipeIngredient(Ingredient.Hazelnut, MassUnit.Kilogram, 1));
        testCat1.addRecipe(recipe1);
        Recipe recipe2 = new Recipe("burger");
        recipe2.addIngredient(new RecipeIngredient(Ingredient.Beef, MassUnit.Kilogram, 1));
        testCat2.addRecipe(recipe2);
        Recipe recipe3 = new Recipe("rissoto");
        recipe3.addIngredient(new RecipeIngredient(Ingredient.Rice, MassUnit.Kilogram, 1));
        testCat3.addRecipe(recipe3);
        Recipe recipe4 = new Recipe("muffin");
        recipe4.addIngredient(new RecipeIngredient(Ingredient.Hazelnut, MassUnit.Kilogram, 1));
        testCat4.addRecipe(recipe4);


        assertTrue(testCat1.equals(testCat4));
        assertFalse(testCat1.equals(testCat3));
        assertFalse(testCat1.equals(testCat2));
        assertTrue(testCat1.equals(testCat1));
        assertFalse(testCat1.equals(new Object()));
        assertFalse(testCat1.equals(new Category("testCatX")));
    }

}