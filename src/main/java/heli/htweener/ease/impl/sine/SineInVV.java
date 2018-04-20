package heli.htweener.ease.impl.sine;

import heli.htweener.ease.IEaseFunction;

public class SineInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.acos(1 - ratio) * _2PI;
    }
}
