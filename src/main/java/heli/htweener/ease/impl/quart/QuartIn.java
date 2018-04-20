package heli.htweener.ease.impl.quart;

import heli.htweener.ease.IEaseFunction;

public class QuartIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return ratio*ratio*ratio*ratio;
    }
}
