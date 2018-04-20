package heli.htweener.ease.impl.quad;

import heli.htweener.ease.IEaseFunction;

public class QuadOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1- Math.sqrt(1-ratio);
    }
}
