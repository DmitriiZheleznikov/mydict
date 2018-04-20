package heli.htweener.ease.impl.strong;

import heli.htweener.ease.IEaseFunction;

public class StrongOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio -= 1) * ratio * ratio * ratio * ratio + 1;
    }
}
