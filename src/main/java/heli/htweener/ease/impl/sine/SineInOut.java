package heli.htweener.ease.impl.sine;

import heli.htweener.ease.IEaseFunction;

public class SineInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 0.5 - 0.5 * Math.cos(ratio * Math.PI);
    }
}
