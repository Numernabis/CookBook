package main.model.unit;

public class UnitConversionException extends Exception {
    // Parameterless Constructor
    public UnitConversionException() {
    }

    // Constructor that accepts a message
    public UnitConversionException(String note) {
        super(note);
    }
}
