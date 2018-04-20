package heli.htweener.ease.impl.expo;

import heli.htweener.ease.IEaseFunction;

public class ExpoOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio == 1) ? 1 : 1-Math.pow(2, -10 * ratio);
    }
}
