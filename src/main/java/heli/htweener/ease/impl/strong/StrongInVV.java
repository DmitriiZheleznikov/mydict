package heli.htweener.ease.impl.strong;

import heli.htweener.ease.IEaseFunction;

public class StrongInVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.pow(ratio, 0.2);
    }
}
