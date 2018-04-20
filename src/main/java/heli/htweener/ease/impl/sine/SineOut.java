package heli.htweener.ease.impl.sine;

import heli.htweener.ease.IEaseFunction;

public class SineOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.sin(ratio * _PI2);
    }
}
