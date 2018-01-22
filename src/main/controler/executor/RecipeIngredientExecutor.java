package main.controler.executor;

import main.controler.Conductor;
import main.model.recipe.*;
import main.model.unit.*;

import java.util.List;

public class RecipeIngredientExecutor extends UnfocusedExecutor implements IExecutionStrategy {

    public RecipeIngredientExecutor(Conductor conductor) {
        super(conductor);
    }

    @Override
    protected void showSupport() {
        viewManager.supportViewer.printRecipeIngredientSupport();
    }

    @Override
    protected void executeContextCommand(List<String> commandLine) {
        switch (commandLine.get(0)) {
            case "convertTo": {
                convertTo(commandLine);
                break;
            }
            case "setQuantity": {
                setQuantity(commandLine);
                break;
            }
            case "return": {
                returnToRecipe();
                break;
            }
            default: {
                viewManager.noteViewer.printErrorNote("Wrong command. Use .support to get available commands.");
            }
        }
    }

    private void convertTo(List<String> commandLine) {
        try {
            RecipeIngredient ingredient = (RecipeIngredient) conductor.getFocusedObject();
            String measureName = commandLine.get(1);
            ICalculable unit;
            if (VolumeUnit.isValueOf(measureName))
                unit = VolumeUnit.valueOf(measureName);
            else if (MassUnit.isValueOf(measureName))
                unit = MassUnit.valueOf(measureName);
            else
                unit = OtherUnit.valueOf(measureName);

            ingredient.convertToUnit(unit);

        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to convert unit, because unit name was not provided.");
        } catch (IllegalArgumentException e) {
            viewManager.noteViewer.printErrorNote("Failed to convert unit, check if you wrote proper arguments.");
        } catch (UnitConversionException e) {
            viewManager.noteViewer.printErrorNote(e.getMessage());
        }

    }

    private void setQuantity(List<String> commandLine) {
        try {
            double quantity = Double.valueOf(commandLine.get(1));
            ((RecipeIngredient) conductor.getFocusedObject()).setQuantity(quantity);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to set quantity, quantity was not provided.");
        } catch (IllegalArgumentException e) {
            viewManager.noteViewer.printErrorNote("Failed to set quantity, check if you wrote proper arguments.");
        } catch (IllegalQuantityException e) {
            viewManager.noteViewer.printErrorNote("Failed to set quantity, quantity must be positive.");
        }
    }

    private void returnToRecipe() {
        conductor.setFocusedObject(conductor.getParentObject());
    }
}

