package heli.htweener.ease.impl.strong;

import heli.htweener.ease.IEaseFunction;

public class StrongInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if ((ratio *= 2) < 1) return 0.5 * ratio * ratio * ratio * ratio * ratio;
        return 0.5 * (ratio -= 2) * ratio * ratio * ratio * ratio + 1;
    }
}
