package heli.htweener;

import heli.htweener.ease.IEaseFunction;
import heli.htweener.tween.HTween;
import heli.htweener.tweenable.ITweenableShape;

/**
 * This is Facade for HTweener just for shorten names, not logic inside
 */
public class HT {
    public static void initTime() {
        HTweener.I.initTime();
    }

    public static boolean step(long now) {
        return HTweener.I.step(now);
    }

    public static HTween tweenObject(Object target, int d, IEaseFunction ease) {
        return HTweener.I.tweenObject(target, d, ease);
    }

    public static HTween to(Object target, int d, IEaseFunction ease, String name) {
        return HTweener.I.to(target, d, ease, name);
    }

    public static HTween to(Object target, int d, IEaseFunction ease) {
        return HTweener.I.to(target, d, ease);
    }

    public static HTween to(Object target, int d) {
        return HTweener.I.to(target, d);
    }

    public static HTween to(int d) {
        return HTweener.I.to(d);
    }

    public static HTween to(int d, String name) {
        return HTweener.I.to(d, name);
    }

    public static HTweener kill(Object target) {
        return HTweener.I.kill(target);
    }

    public static HTweener kill(String name) {
        return HTweener.I.kill(name);
    }

    public static HTweener killAll() {
        return HTweener.I.killAll();
    }

    public static HTweener kill(Object target, String name) {
        return HTweener.I.kill(target, name);
    }
}
