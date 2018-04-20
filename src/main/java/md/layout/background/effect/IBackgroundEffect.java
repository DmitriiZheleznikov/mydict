package md.layout.background.effect;

import heli.htweener.tween.ICallable;

public interface IBackgroundEffect<T> {
    void appear(T background, ICallable onComplete);
    void disappear(T background, ICallable onComplete);
}
