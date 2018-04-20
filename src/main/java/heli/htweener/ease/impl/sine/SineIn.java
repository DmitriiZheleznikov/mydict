package heli.htweener.ease.impl.sine;

import heli.htweener.ease.IEaseFunction;

public class SineIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1 - Math.cos(ratio * _PI2);
    }
}
