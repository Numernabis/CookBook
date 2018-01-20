package main.model.category;

public class NotFoundRecipeException extends Exception{
    public NotFoundRecipeException() {
        super("Recipe with given RecipeName not found.");
    }
}
