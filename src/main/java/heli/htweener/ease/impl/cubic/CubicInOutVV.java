package heli.htweener.ease.impl.cubic;

import heli.htweener.ease.IEaseFunction;

public class CubicInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? Math.pow(ratio*0.25, 1/3) : 1-Math.pow((1-ratio)*0.25, 1/3);
    }
}
