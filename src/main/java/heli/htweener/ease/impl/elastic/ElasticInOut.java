package heli.htweener.ease.impl.elastic;

import heli.htweener.ease.IEaseFunction;

public class ElasticInOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if (ratio == 0 || ratio == 1) { return ratio; }
        ratio = ratio * 2 - 1;

        if (ratio < 0) {
            return -0.5 * Math.pow(2, 10 * ratio) * Math.sin((ratio - 0.1125) * _2PI_45);
        }
        return 0.5 * Math.pow(2, -10 * ratio) * Math.sin((ratio - 0.1125) * _2PI_45) + 1;
    }
}