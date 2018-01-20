package main.model.category;

public class DuplicateRecipeException extends Exception {
    public DuplicateRecipeException() {
        super("Detected possibility of duplication, recipes cannot have the same names.");
    }
}