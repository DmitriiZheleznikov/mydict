package heli.htweener.ease.impl.cubic;

import heli.htweener.ease.IEaseFunction;

public class CubicIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio*ratio*ratio;
    }
}
