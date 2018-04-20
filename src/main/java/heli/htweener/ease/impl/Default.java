package heli.htweener.ease.impl;

import heli.htweener.ease.IEaseFunction;

public class Default implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1 - (ratio = 1 - ratio) * ratio;
    }
}
