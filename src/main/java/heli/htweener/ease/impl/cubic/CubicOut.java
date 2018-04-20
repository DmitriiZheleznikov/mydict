package heli.htweener.ease.impl.cubic;

import heli.htweener.ease.IEaseFunction;

public class CubicOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio-=1)*ratio*ratio+1;
    }
}
