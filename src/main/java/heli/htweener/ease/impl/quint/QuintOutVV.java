package heli.htweener.ease.impl.quint;

import heli.htweener.ease.IEaseFunction;

public class QuintOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1 - Math.pow(1 - ratio, 0.2);
    }
}
