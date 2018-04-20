package heli.htweener.ease.impl.back;

import heli.htweener.ease.IEaseFunction;

public class BackIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio * ratio * (2.70158 * ratio - 1.70158);
    }
}
