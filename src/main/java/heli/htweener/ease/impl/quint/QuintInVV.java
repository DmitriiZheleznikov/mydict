package heli.htweener.ease.impl.quint;

import heli.htweener.ease.IEaseFunction;

public class QuintInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.pow(ratio, 0.2);
    }
}
