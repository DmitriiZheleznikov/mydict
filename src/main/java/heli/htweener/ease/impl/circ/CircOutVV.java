package heli.htweener.ease.impl.circ;

import heli.htweener.ease.IEaseFunction;

public class CircOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1 - Math.sqrt(1 - ratio * ratio);
    }
}
