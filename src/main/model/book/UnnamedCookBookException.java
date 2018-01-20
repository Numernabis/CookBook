package main.model.book;

public class UnnamedCookBookException extends Exception {
    public UnnamedCookBookException() {
        super("Cannot serialize unnamed CookBook.");
    }
}
