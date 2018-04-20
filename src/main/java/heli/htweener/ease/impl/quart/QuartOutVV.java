package heli.htweener.ease.impl.quart;

import heli.htweener.ease.IEaseFunction;

public class QuartOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1 - Math.pow(1 - ratio, 0.25);
    }
}