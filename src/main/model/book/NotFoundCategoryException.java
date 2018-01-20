package main.model.book;

public class NotFoundCategoryException extends Exception {
    public NotFoundCategoryException() {
        super("Category with given CategoryName not found.");
    }
}
