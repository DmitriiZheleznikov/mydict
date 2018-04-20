package heli.htweener.property.list;

import heli.htweener.constant.Constants;
import heli.htweener.property.TweenProperty;
import heli.htweener.property.function.IPropFunction;

public class TweenPropList {
    private TweenProperty[] list;
    private int size = 0;

    public TweenPropList(int maxNumberOfProps) {
        this.list = new TweenProperty[maxNumberOfProps];
    }

    public void add(double initValue, double targetValue, IPropFunction updateValueFunction) {
        TweenProperty property = list[size];
        if (property == null) {
            property = new TweenProperty();
            list[size] = property;
        }

        property.init(initValue, targetValue, updateValueFunction);
        size++;
    }

    public void updateValue(Object targetObject, double ratio) {
        for (int i = 0; i < size; i++) {
            list[i].updateValue(targetObject, ratio);
        }
    }

    public void dispose() {
        size = 0;
    }
}
