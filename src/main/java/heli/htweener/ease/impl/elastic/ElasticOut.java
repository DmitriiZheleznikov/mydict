package heli.htweener.ease.impl.elastic;

import heli.htweener.ease.IEaseFunction;

public class ElasticOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if (ratio == 0 || ratio == 1) { return ratio; }
        return Math.pow(2, -10 * ratio) *  Math.sin((ratio - 0.075) * _2PI_3) + 1;
    }
}
