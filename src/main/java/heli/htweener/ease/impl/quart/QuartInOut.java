package heli.htweener.ease.impl.quart;

import heli.htweener.ease.IEaseFunction;

public class QuartInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? 8*ratio*ratio*ratio*ratio : -8*(ratio-=1)*ratio*ratio*ratio+1;
    }
}