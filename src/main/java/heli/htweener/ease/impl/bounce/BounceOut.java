package heli.htweener.ease.impl.bounce;

import heli.htweener.ease.IEaseFunction;

public class BounceOut implements IEaseFunction {
    @Override
    public double transform(double ratio) {
        if (ratio < 0.36364) {
            return 7.5625 * ratio * ratio;
        } else if (ratio < 0.727273) {
            return 7.5625 * (ratio -= 0.545455) * ratio + .75;
        } else if (ratio < 0.909091) {
            return 7.5625 * (ratio -= 0.818182) * ratio + .9375;
        } else {
            return 7.5625 * (ratio -= 0.9545455) * ratio + .984375;
        }
    }
}
