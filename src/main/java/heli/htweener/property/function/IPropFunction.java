package heli.htweener.property.function;

public interface IPropFunction {
    void updateValue(double value);

    default void updateValue(Object target, double value) {
        updateValue(value);
    }
}
