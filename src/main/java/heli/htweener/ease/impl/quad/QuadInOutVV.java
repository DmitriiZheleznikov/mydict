package heli.htweener.ease.impl.quad;

import heli.htweener.ease.IEaseFunction;

public class QuadInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ((ratio*=0.5) < 0.25) ? Math.sqrt(ratio) : 1 - Math.sqrt(0.5 - ratio);
    }
}
