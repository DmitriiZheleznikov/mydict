package heli.htweener.ease.impl.circ;

import heli.htweener.ease.IEaseFunction;

public class CircInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ((ratio *= 2) < 1) ? Math.sqrt(0.25 - (Math.pow(1 - ratio, 2) * 0.25)) : 1 - Math.sqrt(1 - Math.pow(ratio-1, 2))*0.5;
    }
}
