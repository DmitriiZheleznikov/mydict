package md.layout.background;

import heli.htweener.tween.ICallable;

public interface IBackground {
    void appear(ICallable onComplete);
    void disappear(ICallable onComplete);
    void lock();
    void unlock();
}
