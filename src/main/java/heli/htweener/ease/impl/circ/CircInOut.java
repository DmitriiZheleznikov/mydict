package heli.htweener.ease.impl.circ;

import heli.htweener.ease.IEaseFunction;

public class CircInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ((ratio *= 2) < 1) ? -0.5 * (Math.sqrt(1 - ratio * ratio) - 1) : 0.5 * (Math.sqrt(1 - (ratio -= 2) * ratio) + 1);
    }
}
