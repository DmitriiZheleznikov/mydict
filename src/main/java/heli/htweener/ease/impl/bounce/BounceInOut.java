package heli.htweener.ease.impl.bounce;

import heli.htweener.ease.IEaseFunction;

public class BounceInOut implements IEaseFunction {
    private BounceOut bounceOut = new BounceOut();
    private BounceIn bounceIn = new BounceIn();

    @Override
    public double transform(double ratio) {
        return ((ratio *= 2) < 1) ? 0.5 * bounceIn.transform(ratio) : 0.5 * bounceOut.transform(ratio - 1) + 0.5;
    }
}
