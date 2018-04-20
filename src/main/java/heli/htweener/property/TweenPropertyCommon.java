package heli.htweener.property;

import heli.htweener.property.function.IPropComFunction;

public class TweenPropertyCommon {
    protected Object initValue = null;
    protected Object targetValue = null;
    protected IPropComFunction updateValueFunction = null;

    public TweenPropertyCommon() {
    }

    public void init(Object initValue, Object targetValue, IPropComFunction updateValueFunction) {
        this.initValue = initValue;
        this.targetValue = targetValue;
        this.updateValueFunction = updateValueFunction;
    }

    public void updateValue(Object targetObject, double ratio) {
        updateValueFunction.updateValue(targetObject, initValue, targetValue, ratio);
    }

    public Object getInitValue() {
        return initValue;
    }

    public Object getTargetValue() {
        return targetValue;
    }

    public IPropComFunction getUpdateValueFunction() {
        return updateValueFunction;
    }
}
