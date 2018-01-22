package main.view;

public class NoteViewer {

    public void printContext(Object focusedObject) {
        String context = "Console";
        if (focusedObject != null)
            context = focusedObject.getClass().getSimpleName() + ", " + focusedObject.toString();
        System.out.println("Working in context of: " + context);
    }

    public void printNote(String note) {
        System.out.println(note);
    }

    public void printErrorNote(String errorNote) {
        System.out.println(errorNote);
    }

    public void printEmptyCommandLineMessage() {
        System.out.println("Command line is empty. Use /support to see available options.");
    }
}
