package main.controler.executor;


import main.controler.Conductor;
import main.model.category.*;
import main.model.ingredient.Ingredient;
import main.model.recipe.*;
import main.model.unit.*;

import java.time.Duration;
import java.util.List;

public class RecipeExecutor extends UnfocusedExecutor implements IExecutionStrategy {

    public RecipeExecutor(Conductor conductor) {
        super(conductor);
    }

    @Override
    protected void showSupport() {
        viewManager.supportViewer.printRecipeSupport();
    }

    @Override
    protected void executeContextCommand(List<String> commandLine) {
        switch (commandLine.get(0)) {
            case "setRecipeName": {
                setRecipeName(commandLine);
                break;
            }
            case "showRecipe": {
                viewManager.dataViewer.showRecipe((Recipe) conductor.getFocusedObject());
                break;
            }
            case "addDirection": {
                addDirection(commandLine);
                break;
            }
            case "removeDirection": {
                removeDirection(commandLine);
                break;
            }
            case "addIngredient": {
                addIngredient(commandLine);
                break;
            }
            case "removeIngredient": {
                removeIngredient(commandLine);
                break;
            }
            case "setPreparationTime": {
                setPreparationTime(commandLine);
                break;
            }
            case "setCookingTime": {
                setCookingTime(commandLine);
                break;
            }
            case "selectIngredient": {
                selectIngredient(commandLine);
                break;
            }
            case "return": {
                returnToCategory();
                break;
            }
            case "showMeasures": {
                viewManager.dataViewer.showMeasures();
                break;
            }
            case "showIngredients": {
                viewManager.dataViewer.showIngredients();
                break;
            }
            default: {
                viewManager.noteViewer.printErrorNote("Wrong command. Use .support to get available commands.");
            }

        }
    }

    private void setRecipeName(List<String> commandLine) {
        try {
            String oldName = ((Recipe) conductor.getFocusedObject()).getRecipeName();
            Category category = (Category) conductor.getParentObject();
            category.renameRecipe(oldName, commandLine.get(1));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to set RecipeName, because name was not defined.");
        } catch (DuplicateRecipeException e) {
            viewManager.noteViewer.printErrorNote("Failed to set RecipeName, because recipe with that name already" +
                    "exists in that category.");
        } catch (NotFoundRecipeException e) {
            viewManager.noteViewer.printErrorNote("Failed to set RecipeName, because recipe with given oldName" +
                    "doesn't exists in that category.");
        }
    }

    private void addDirection(List<String> commandLine) {
        Recipe recipe = (Recipe) conductor.getFocusedObject();
        commandLine.remove(0);
        if (commandLine.isEmpty())
            viewManager.noteViewer.printErrorNote("Failed to add Direction, because direction was not defined.");
        String direction = "";
        for (String str : commandLine)
            direction = direction + " " + str;
        recipe.addDirection(direction);
    }

    private void removeDirection(List<String> commandLine) {
        try {
            Recipe recipe = (Recipe) conductor.getFocusedObject();
            recipe.removeDirection(Integer.valueOf(commandLine.get(1)) - 1);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to remove Direction, because proper index was not defined.");
        } catch (NumberFormatException e) {
            viewManager.noteViewer.printErrorNote("Failed to remove Direction, because index is not a number.");
        }
    }

    private void addIngredient(List<String> commandLine) {
        try {
            Recipe recipe = (Recipe) conductor.getFocusedObject();
            String ingredientName = commandLine.get(3);
            Ingredient ingredient = Ingredient.valueOf(ingredientName);
            String unitName = commandLine.get(2);
            ICalculable unit;
            if (MassUnit.isValueOf(unitName))
                unit = MassUnit.valueOf(unitName);
            else if (VolumeUnit.isValueOf(unitName))
                unit = VolumeUnit.valueOf(unitName);
            else
                unit = OtherUnit.valueOf(unitName);

            double quantity = Double.valueOf(commandLine.get(1));
            RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, unit, quantity);
            recipe.addIngredient(recipeIngredient);

        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to add Ingredient, because of too few arguments.");
        } catch (NumberFormatException e) {
            viewManager.noteViewer.printErrorNote("Failed to add Ingredient, because quantity is not a number.");
        } catch (IllegalArgumentException e) {
            viewManager.noteViewer.printErrorNote("Failed to add Ingredient, check if you wrote proper arguments.");
        } catch (IllegalUnitException e) {
            viewManager.noteViewer.printErrorNote(e.getMessage());
        } catch (IllegalQuantityException e) {
            viewManager.noteViewer.printErrorNote("Failed to add Ingredient:\n" + e.getMessage());
        }
    }

    private void removeIngredient(List<String> commandLine) {
        try {
            Recipe recipe = (Recipe) conductor.getFocusedObject();
            recipe.removeIngredient(Integer.valueOf(commandLine.get(1)) - 1);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to remove Ingredient, because proper index was not defined.");
        } catch (NumberFormatException e) {
            viewManager.noteViewer.printErrorNote("Failed to remove Ingredient, because index is not a number.");
        }
    }

    private void setPreparationTime(List<String> commandLine) {
        Duration duration = getDuration(commandLine);
        if (duration != null)
            ((Recipe) conductor.getFocusedObject()).setPreparationTime(duration);
    }

    private void setCookingTime(List<String> commandLine) {
        Duration duration = getDuration(commandLine);
        if (duration != null)
            ((Recipe) conductor.getFocusedObject()).setCookingTime(duration);
    }

    private Duration getDuration(List<String> commandLine) {
        try {
            int hours = Integer.valueOf(commandLine.get(1));
            int minutes = Integer.valueOf(commandLine.get(2));
            Duration duration = Duration.ofMinutes(hours * 60 + minutes);
            return duration;
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to set Time, because of to few arguments.\n" +
                    "Use .support to get information about proper arguments.");
        } catch (NumberFormatException e) {
            viewManager.noteViewer.printErrorNote("Failed to set Time, because argument is not a number.");
        }
        return null;
    }

    private void selectIngredient(List<String> commandLine) {
        try {
            Recipe recipe = (Recipe) conductor.getFocusedObject();
            int index = Integer.valueOf(commandLine.get(1));
            conductor.setFocusedObject(recipe.getRecipeIngredientList().get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to select Ingredient, Ingredient with that index does not exist");
        } catch (NumberFormatException e) {
            viewManager.noteViewer.printErrorNote("Failed to select Ingredient, because index is not a number.");
        } catch (IllegalArgumentException e) {
            viewManager.noteViewer.printErrorNote("Failed to select Ingredient, check if you wrote proper arguments.");
        }
    }

    private void returnToCategory() {
        conductor.setFocusedObject(conductor.getParentObject());
    }


}
