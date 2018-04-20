package heli.htweener.ease.impl.quad;

import heli.htweener.ease.IEaseFunction;

public class QuadInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.sqrt(ratio);
    }
}
