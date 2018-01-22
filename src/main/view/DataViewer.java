package main.view;

import main.model.category.Category;
import main.model.ingredient.Ingredient;
import main.model.recipe.*;
import main.model.unit.*;

import java.util.List;

public class DataViewer {

    public void showCategories(List<String> categories) {
        int no = 1;
        if (categories.isEmpty())
            System.out.println("There are no available categories.");
        else {
            System.out.println("Available categories:");
            for (String categoryName : categories) {
                System.out.println(String.valueOf(no) + ". " + categoryName);
                no++;
            }
        }
    }

    public void showTableOfContents(Category focusedObject) {
        List<String> category = focusedObject.getTableOfContents();
        int no = 1;
        System.out.println("Category: " + focusedObject.toString());
        for (String recipeName : category) {
            System.out.println(String.valueOf(no) + ". " + recipeName);
            no++;
        }
    }

    public void showRecipe(Recipe recipe) {
        String recipeData = "Recipe: " + recipe.getRecipeName() + "\n\n";
        String preparationTime = "not known";
        if (recipe.getPreparationTime() != null) {
            preparationTime = recipe.getPreparationTime().toString();
            preparationTime = preparationTime.substring(2, preparationTime.length());
        }
        String cookingTime = "not known";
        if (recipe.getCookingTime() != null) {
            cookingTime = recipe.getCookingTime().toString();
            cookingTime = cookingTime.substring(2, cookingTime.length());
        }
        recipeData += "Preparation time: " + preparationTime + "\t";
        recipeData += "Cooking time: " + cookingTime + "\n\n";
        recipeData += "Ingredients:\n";
        int index = 1;
        for (RecipeIngredient ingredient : recipe.getRecipeIngredientList()) {
            recipeData += "\t " + String.valueOf(index) + ". " + ingredient.toString() + "\n";
            index++;
        }
        recipeData += "\nDirections:\n";
        index = 1;
        for (String direction : recipe.getDirections()) {
            recipeData += "\t" + String.valueOf(index) + ". " + direction + "\n";
            index++;
        }
        recipeData += "\nTotal cost of ingredients: " + recipe.countPrice() + "\n\n";

        System.out.println(recipeData);
    }

    public void showMeasures() {
        System.out.println("Available mass units: ");
        for (MassUnit measureUnit : MassUnit.values()) {
            System.out.println("+ " + measureUnit.toString());
        }
        System.out.println("Available volume units: ");
        for (VolumeUnit volumeUnit : VolumeUnit.values()) {
            System.out.println("+ " + volumeUnit.toString());
        }
        System.out.println("Available other units: ");
        for (OtherUnit otherUnit : OtherUnit.values()) {
            System.out.println("+ " + otherUnit.toString());
        }
    }

    public void showIngredients() {
        System.out.println("Available ingredients: ");
        for (Ingredient ingredient : Ingredient.values()) {
            System.out.println("+ " + ingredient.toString());
        }
    }
}

