package heli.htweener.ease.impl.quint;

import heli.htweener.ease.IEaseFunction;

public class QuintInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? 16*ratio*ratio*ratio*ratio*ratio : 16*(ratio-=1)*ratio*ratio*ratio*ratio+1;
    }
}
