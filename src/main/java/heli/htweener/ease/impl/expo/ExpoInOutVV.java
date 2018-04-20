package heli.htweener.ease.impl.expo;

import heli.htweener.ease.IEaseFunction;

public class ExpoInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if (ratio == 0 || ratio == 1) { return ratio; }
        if (0 > ((ratio *= 2) - 1)) { return Math.log(ratio) / _20log2 + 0.5; }
        return 0.5 - Math.log(2 - ratio) / _20log2;
    }
}