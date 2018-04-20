package heli.htweener.property;

import heli.htweener.property.function.IPropFunction;

public class TweenProperty {
    /** Value "from". Animation from this value */
    protected double initValue = 0;
    /** Value "range". Animation to value "initValue+rangeValue" */
    protected double rangeValue = 0;
    /** Function that updates object properties with next value in range ["initValue", "initValue+rangeValue"] */
    protected IPropFunction updateValueFunction = null;

    public TweenProperty() {
    }

    public void init(double initValue, double targetValue, IPropFunction updateValueFunction) {
        this.initValue = initValue;
        this.rangeValue = targetValue - initValue;
        this.updateValueFunction = updateValueFunction;
    }

    public void updateValue(Object targetObject, double ratio) {
        updateValueFunction.updateValue(targetObject, getValueByRatio(ratio));
    }

    public double getValueByRatio(double ratio) {
        return initValue + ratio * rangeValue;
    }

    public void dispose() {
    }

    public double getInitValue() {
        return initValue;
    }

    public double getRangeValue() {
        return rangeValue;
    }

    public IPropFunction getUpdateValueFunction() {
        return updateValueFunction;
    }
}
