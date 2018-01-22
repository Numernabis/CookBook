package main.controler.executor;

import main.controler.Conductor;
import main.model.category.*;
import main.model.book.*;
import main.model.recipe.Recipe;

import java.util.List;

public class CategoryExecutor extends UnfocusedExecutor implements IExecutionStrategy {

    public CategoryExecutor(Conductor conductor) {
        super(conductor);
    }

    @Override
    protected void showSupport() {
        viewManager.supportViewer.printCategorySupport();
    }

    @Override
    protected void executeContextCommand(List<String> commandLine) {
        switch (commandLine.get(0)) {
            case "showTableOfContents": {
                viewManager.dataViewer.showTableOfContents((Category) conductor.getFocusedObject());
                break;
            }
            case "setCategoryName": {
                setCategoryName(commandLine);
                break;
            }
            case "addRecipe": {
                addRecipe(commandLine);
                break;
            }
            case "removeRecipe": {
                removeRecipe(commandLine);
                break;
            }
            case "selectRecipe": {
                selectRecipe(commandLine);
                break;
            }
            case "showVegetarianMeals": {
                showVegetarianMeals();
                break;
            }
            case "showIngredients": {
                viewManager.dataViewer.showIngredients();
                break;
            }
            default: {
                viewManager.noteViewer.printErrorNote("Wrong command. Use /support to get available commands.");
            }

        }
    }

    private void setCategoryName(List<String> commandLine) {
        try {
            String oldName = ((Category) conductor.getFocusedObject()).getCategoryName();
            conductor.getCookBook().renameCategory(oldName, commandLine.get(1));
        } catch (NotFoundCategoryException e) {
            viewManager.noteViewer.printErrorNote("Failed to set CategoryName, because there is no such category.");
        } catch (DuplicateCategoryException e) {
            viewManager.noteViewer.printErrorNote("Failed to set CategoryName, because category " +
                    "with that name already exists in that CookBook.");
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to set CategoryName, because name was not defined.");
        }
    }

    private void addRecipe(List<String> commandLine) {
        try {
            String name = commandLine.get(1);
            Category category = (Category) conductor.getFocusedObject();
            category.addRecipe(new Recipe(name));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to add Recipe, because name was not defined.");
        } catch (DuplicateRecipeException e) {
            viewManager.noteViewer.printErrorNote("Failed to add Recipe, because book with that name already exists.");

        }
    }

    private void removeRecipe(List<String> commandLine) {
        try {
            Category category = (Category) conductor.getFocusedObject();
            category.removeRecipe(commandLine.get(1));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to remove Recipe, because name was not defined.");
        }
    }

    private void selectRecipe(List<String> commandLine) {
        try {
            String recipeName = commandLine.get(1);
            Category category = (Category) conductor.getFocusedObject();
            Recipe recipe = category.getRecipe(recipeName);
            if (recipe == null)
                viewManager.noteViewer.printErrorNote("Failed to select Recipe because " +
                        " there is no such recipe, check name spelling.");
            else
                conductor.setFocusedObject(recipe);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to select Recipe, because name was not defined.");
        }
    }

    private void showVegetarianMeals() {
        Category category = (Category) conductor.getFocusedObject();
        Category vegetarian = new Category(category.getCategoryName() + " vegetarian",
                category.getRecipes(Recipe::isVegetarian));
        viewManager.dataViewer.showTableOfContents(vegetarian);
    }

}

