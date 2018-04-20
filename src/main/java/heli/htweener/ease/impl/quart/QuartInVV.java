package heli.htweener.ease.impl.quart;

import heli.htweener.ease.IEaseFunction;

public class QuartInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.pow(ratio, 0.25);
    }
}