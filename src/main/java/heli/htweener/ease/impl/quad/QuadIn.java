package heli.htweener.ease.impl.quad;

import heli.htweener.ease.IEaseFunction;

public class QuadIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio*ratio;
    }
}
