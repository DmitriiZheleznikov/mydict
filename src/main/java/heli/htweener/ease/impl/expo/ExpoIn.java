package heli.htweener.ease.impl.expo;

import heli.htweener.ease.IEaseFunction;

public class ExpoIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio == 0) ? 0 : Math.pow(2, 10 * (ratio - 1));
    }
}
