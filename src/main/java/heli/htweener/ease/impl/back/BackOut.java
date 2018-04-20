package heli.htweener.ease.impl.back;

import heli.htweener.ease.IEaseFunction;

public class BackOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio -= 1) * ratio * (2.70158 * ratio + 1.70158) + 1;
    }
}
