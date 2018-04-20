package heli.htweener.ease.impl.strong;

import heli.htweener.ease.IEaseFunction;

public class StrongIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio * ratio * ratio * ratio * ratio;
    }
}
