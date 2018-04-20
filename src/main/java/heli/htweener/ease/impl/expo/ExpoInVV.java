package heli.htweener.ease.impl.expo;

import heli.htweener.ease.IEaseFunction;

public class ExpoInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio == 0) ? 0 : Math.log(ratio) / _10log2 + 1;
    }
}