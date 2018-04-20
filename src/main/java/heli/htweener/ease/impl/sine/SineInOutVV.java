package heli.htweener.ease.impl.sine;

import heli.htweener.ease.IEaseFunction;

public class SineInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return Math.acos(1-2*ratio)*0.5*_2PI;
    }
}
