package heli.htweener.ease.impl.cubic;

import heli.htweener.ease.IEaseFunction;

public class CubicInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? 4*ratio*ratio*ratio : 4*(ratio-=1)*ratio*ratio+1;
    }
}
