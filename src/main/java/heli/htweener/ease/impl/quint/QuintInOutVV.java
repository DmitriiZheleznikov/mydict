package heli.htweener.ease.impl.quint;

import heli.htweener.ease.IEaseFunction;

public class QuintInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? Math.pow(ratio*0.0625, 0.2) : 1 - Math.pow(0.0625 - ratio*0.0625, 0.2);
    }
}
