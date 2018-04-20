package heli.htweener.ease.impl.elastic;

import heli.htweener.ease.IEaseFunction;

public class ElasticIn implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if (ratio == 0 || ratio == 1) { return ratio; }
        return -1 * Math.pow(2, 10 * (ratio -= 1)) * Math.sin((ratio - 0.075) * _2PI_3);
    }
}
