package heli.htweener.ease.impl.cubic;

import heli.htweener.ease.IEaseFunction;

public class CubicOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1 - Math.pow(1-ratio, 1/3);
    }
}
