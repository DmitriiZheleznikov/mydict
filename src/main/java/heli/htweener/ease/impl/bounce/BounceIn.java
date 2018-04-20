package heli.htweener.ease.impl.bounce;

import heli.htweener.ease.IEaseFunction;

public class BounceIn implements IEaseFunction {
    private BounceOut bounceOut = new BounceOut();

    @Override
    public double transform(double ratio) {
        return 1 - bounceOut.transform(1 - ratio);
    }
}
