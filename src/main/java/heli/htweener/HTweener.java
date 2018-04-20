package heli.htweener;

import heli.htweener.constant.Constants;
import heli.htweener.ease.IEaseFunction;
import heli.htweener.tween.HTween;
import heli.htweener.tween.TweenList;
import heli.htweener.tweenable.ITweenableShape;

/**
 * Base class for animation and any other changes that are performed in time with easeing. <br>
 * "Easeing" is a special function (all required already implemented) for transformation of factor.<br>
 * Basic usage is to call "tweenObject" function and then adjust "tween" object with function for transformation.<br>
 * For example you have Circle object drawn in your program and you want to change its x,y position with animation.
 * <br>HT.to(circle, 1000, Ease.sinein).x(500).y(300);<br>
 * It will move your "circle" from current position to (500,300) using Sine function (slow in the first 300ms, the fast 400ms, then slow 300ms) withing a second (not exact time and depends on you system processor and OS tick value)
 * <br><br>Do not forget to initiate and start AnimationTimer, refer HTLoop class
 */
public class HTweener {
    public static final HTweener I = new HTweener();

    protected TweenList tweens;
    protected long time;
    protected boolean isPaused = false;

    public HTweener() {
        this.tweens = new TweenList(Constants.MAX_NUMBER_OF_TWEENS, Constants.MAX_NUMBER_OF_PROPERTIES_IN_TWEEN);
    }

    public HTweener(int maxNumberOfTweens, int maxNumberOfPropsInTweens) {
        this.tweens = new TweenList(maxNumberOfTweens, maxNumberOfPropsInTweens);
    }

    /** Creates tween for you object */
    public HTween tweenObject(Object target, int duration, IEaseFunction ease) {
        return to(target, duration, ease);
    }

    /** Creates tween for you object - short name*/
    public HTween to(Object target, int duration, IEaseFunction ease, String name) {
        return tweens.add(target, duration, ease, name);
    }

    public HTween to(Object target, int duration, IEaseFunction ease) {
        return tweens.add(target, duration, ease, null);
    }

    public HTween to(Object target, int duration) {
        return tweens.add(target, duration, Ease.defaults, null);
    }

    public HTween to(int duration) {
        return tweens.add(null, duration, Ease.linear, null);
    }

    public HTween to(int duration, String name) {
        return tweens.add(null, duration, Ease.linear, name);
    }

    /** Should be called from AnimationTimer initiation, refer HTLoop class*/
    public void initTime() {
        this.time = System.nanoTime();
    }

    public boolean step() {
        return step(System.nanoTime());
    }

    /** Should be called from AnimationTimer, refer HTLoop class */
    public boolean step(long now) {
        double delta = (now - time) / 1000000;
        time = now;
        return step(delta);
    }

    public boolean step(double dt) {
        if (isPaused) return false;
        return tweens.step(dt);
    }

    /** Stops animation and any changes for all tweens */
    public HTweener killAll() {
        return kill (null, null);
    }

    /** Stops animation and any changes for particular tweens */
    public HTweener kill(Object target) {
        return kill (target, null);
    }

    /** Stops animation and any changes for particular tweens */
    public HTweener kill(String name) {
        return kill (null, name);
    }

    /** Stops animation and any changes for particular tweens */
    public HTweener kill(Object target, String name) {
        tweens.interrupt(target, name);
        return this;
    }

    public void pause() {
        isPaused = true;
    }

    public void resume () {
        isPaused = false;
        initTime();
    }
}
