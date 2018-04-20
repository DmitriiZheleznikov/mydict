package heli.htweener.ease.impl.circ;

import heli.htweener.ease.IEaseFunction;

public class CircOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.sqrt(1-(ratio-=1)*ratio);
    }
}
