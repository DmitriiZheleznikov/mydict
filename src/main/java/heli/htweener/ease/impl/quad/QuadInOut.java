package heli.htweener.ease.impl.quad;

import heli.htweener.ease.IEaseFunction;

public class QuadInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? 2*ratio*ratio : -2*ratio*(ratio-2)-1;
    }
}
