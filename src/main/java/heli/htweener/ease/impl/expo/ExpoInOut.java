package heli.htweener.ease.impl.expo;

import heli.htweener.ease.IEaseFunction;

public class ExpoInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if (ratio == 0 || ratio == 1) { return ratio; }
        if (0 > (ratio = ratio*2-1)) { return 0.5*Math.pow(2, 10*ratio); }
        return 1-0.5*Math.pow(2, -10*ratio);
    }
}