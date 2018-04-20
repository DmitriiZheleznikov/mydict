package heli.htweener.ease.impl.expo;

import heli.htweener.ease.IEaseFunction;

public class ExpoOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio == 1) ? 1 : - Math.log(1 - ratio) / _10log2;
    }
}