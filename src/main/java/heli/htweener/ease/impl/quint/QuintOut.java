package heli.htweener.ease.impl.quint;

import heli.htweener.ease.IEaseFunction;

public class QuintOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1+(ratio-=1)*ratio*ratio*ratio*ratio;
    }
}