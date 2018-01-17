package main.model.unit;

public interface ICalculable {

    double getValueIn(ICalculable unit) throws UnitConversionException;

    double getFractionOfBasicUnit();
}
