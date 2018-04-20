package heli.htweener.ease.impl.sine;

import heli.htweener.ease.IEaseFunction;

public class SineOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.asin(ratio) * _2PI;
    }
}
