package heli.htweener.ease.impl.quart;

import heli.htweener.ease.IEaseFunction;

public class QuartInOutVV implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        return (ratio < 0.5) ? Math.pow(ratio*0.125, 0.25) : 1 - Math.pow(0.125 - ratio*0.125, 0.25);
    }
}