package main.model.book;

public class DuplicateCategoryException extends Exception {
    public DuplicateCategoryException() {
        super("Detected possibility of duplication, categories cannot have the same names.");
    }
}
