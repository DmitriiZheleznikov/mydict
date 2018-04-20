package md.layout.content.view.helper.visibility;

import heli.htweener.fx.ext.HText;
import heli.htweener.tween.ICallable;

public interface ISpecificVisibilityHelper {
    void hide(HText text);
    void hide(HText text, ICallable onComplete);
    void show(HText text);
    void show(HText text, ICallable onComplete);
}
