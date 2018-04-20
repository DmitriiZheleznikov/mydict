package heli.htweener.ease.impl.quint;

import heli.htweener.ease.IEaseFunction;

public class QuintIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio*ratio*ratio*ratio*ratio;
    }
}
