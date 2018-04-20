package heli.htweener.property.function;

public interface IPropComFunction {
    void updateValue(Object initialValue, Object targetValue, double ratio);

    default void updateValue(Object target, Object initialValue, Object targetValue, double ratio) {
        updateValue(initialValue, targetValue, ratio);
    }
}
