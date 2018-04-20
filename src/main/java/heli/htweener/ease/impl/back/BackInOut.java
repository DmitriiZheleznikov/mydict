package heli.htweener.ease.impl.back;

import heli.htweener.ease.IEaseFunction;

public class BackInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if ((ratio *= 2) < 1) return 0.5 * (ratio * ratio * (3.5949095 * ratio - 2.5949095));
        else return 0.5 * ((ratio -= 2) * ratio * (3.5949095 * ratio + 2.5949095) + 2);
    }
}
