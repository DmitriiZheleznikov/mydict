package heli.htweener;

import heli.htweener.ease.IEaseFunction;
import heli.htweener.ease.impl.*;
import heli.htweener.ease.impl.circ.*;
import heli.htweener.ease.impl.back.*;
import heli.htweener.ease.impl.bounce.*;
import heli.htweener.ease.impl.cubic.*;
import heli.htweener.ease.impl.elastic.*;
import heli.htweener.ease.impl.expo.*;
import heli.htweener.ease.impl.linear.*;
import heli.htweener.ease.impl.quad.*;
import heli.htweener.ease.impl.quart.*;
import heli.htweener.ease.impl.quint.*;
import heli.htweener.ease.impl.sine.*;
import heli.htweener.ease.impl.strong.*;

/**
 * Ease function that is mostly used
 */
public class Ease {
    public static final IEaseFunction defaults = new Default();

    public static final IEaseFunction linear = new Linear();

    public static final IEaseFunction bounceIn = new BounceIn();
    public static final IEaseFunction bounceOut = new BounceOut();
    public static final IEaseFunction bounceInOut = new BounceInOut();

    public static final IEaseFunction backIn = new BackIn();
    public static final IEaseFunction backOut = new BackOut();
    public static final IEaseFunction backInOut = new BackInOut();

    public static final IEaseFunction circIn = new CircIn();
    public static final IEaseFunction circOut = new CircOut();
    public static final IEaseFunction circInOut = new CircInOut();
    //public static final IEaseFunction circInVV = new CircInVV();
    //public static final IEaseFunction circOutVV = new CircOutVV();
    //public static final IEaseFunction circInOutVV = new CircInOutVV();

    public static final IEaseFunction cubicIn = new CubicIn();
    public static final IEaseFunction cubicOut = new CubicOut();
    public static final IEaseFunction cubicInOut = new CubicInOut();
    //public static final IEaseFunction cubicInVV = new CubicInVV();
    //public static final IEaseFunction cubicOutVV = new CubicOutVV();
    //public static final IEaseFunction cubicInOutVV = new CubicInOutVV();

    public static final IEaseFunction elasticIn = new ElasticIn();
    public static final IEaseFunction elasticOut = new ElasticOut();
    public static final IEaseFunction elasticInOut = new ElasticInOut();

    public static final IEaseFunction expoIn = new ExpoIn();
    public static final IEaseFunction expoOut = new ExpoOut();
    public static final IEaseFunction expoInOut = new ExpoInOut();
    //public static final IEaseFunction expoInVV = new ExpoInVV();
    //public static final IEaseFunction expoOutVV = new ExpoOutVV();
    //public static final IEaseFunction expoInOutVV = new ExpoInOutVV();

    public static final IEaseFunction quadIn = new QuadIn();
    public static final IEaseFunction quadOut = new QuadOut();
    public static final IEaseFunction quadInOut = new QuadInOut();
    //public static final IEaseFunction quadInVV = new QuadInVV();
    //public static final IEaseFunction quadOutVV = new QuadOutVV();
    //public static final IEaseFunction quadInOutVV = new QuadInOutVV();

    public static final IEaseFunction quartIn = new QuartIn();
    public static final IEaseFunction quartOut = new QuartOut();
    public static final IEaseFunction quartInOut = new QuartInOut();
    //public static final IEaseFunction quartInVV = new QuartInVV();
    //public static final IEaseFunction quartOutVV = new QuartOutVV();
    //public static final IEaseFunction quartInOutVV = new QuartInOutVV();

    public static final IEaseFunction quintIn = new QuintIn();
    public static final IEaseFunction quintOut = new QuintOut();
    public static final IEaseFunction quintInOut = new QuintInOut();
    //public static final IEaseFunction quintInVV = new QuintInVV();
    //public static final IEaseFunction quintOutVV = new QuintOutVV();
    //public static final IEaseFunction quintInOutVV = new QuintInOutVV();

    public static final IEaseFunction sineIn = new SineIn();
    public static final IEaseFunction sineOut = new SineOut();
    public static final IEaseFunction sineInOut = new SineInOut();
    //public static final IEaseFunction sineInVV = new SineInVV();
    //public static final IEaseFunction sineOutVV = new SineOutVV();
    //public static final IEaseFunction sineInOutVV = new SineInOutVV();

    public static final IEaseFunction strongIn = new StrongIn();
    public static final IEaseFunction strongOut = new StrongOut();
    public static final IEaseFunction strongInOut = new StrongInOut();
    //public static final IEaseFunction strongInVV = new StrongInVV();
    //public static final IEaseFunction strongOutVV = new StrongOutVV();
    //public static final IEaseFunction strongInOutVV = new StrongInOutVV();
}
