package heli.htweener.ease.impl.circ;

import heli.htweener.ease.IEaseFunction;

public class CircInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.sqrt(1 - Math.pow(1 - ratio, 2));
    }
}
