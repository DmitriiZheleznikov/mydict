package heli.htweener.property.list;

import heli.htweener.constant.Constants;
import heli.htweener.property.TweenPropertyCommon;
import heli.htweener.property.function.IPropComFunction;

public class TweenPropComList {
    private TweenPropertyCommon[] list;
    private int size = 0;

    public TweenPropComList(int maxNumberOfProps) {
        this.list = new TweenPropertyCommon[maxNumberOfProps];
    }

    public void add(Object initValue, Object targetValue, IPropComFunction updateValueFunction) {
        TweenPropertyCommon property = list[size];
        if (property == null) {
            property = new TweenPropertyCommon();
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
