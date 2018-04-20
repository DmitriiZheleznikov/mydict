package heli.htweener.ease.impl.linear;

import heli.htweener.ease.IEaseFunction;

public class Linear implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio;
    }
}
