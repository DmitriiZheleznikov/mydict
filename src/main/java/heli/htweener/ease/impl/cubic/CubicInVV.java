package heli.htweener.ease.impl.cubic;

import heli.htweener.ease.IEaseFunction;

public class CubicInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.pow(ratio, 1/3);
    }
}
