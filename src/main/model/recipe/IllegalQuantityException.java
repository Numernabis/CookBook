package main.model.recipe;

public class IllegalQuantityException extends Exception {
    public IllegalQuantityException () {
        super("Quantity cannot be negative. Only positive values are allowed.");
    }
}
