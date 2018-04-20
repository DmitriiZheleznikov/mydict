package heli.htweener.ease.impl.quart;

import heli.htweener.ease.IEaseFunction;

public class QuartOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return 1-(ratio-=1)*ratio*ratio*ratio;
    }
}