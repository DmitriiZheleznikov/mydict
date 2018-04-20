package md.layout.content.view.helper.visibility.impl;

import heli.htweener.fx.ext.HText;
import heli.htweener.tween.ICallable;
import md.layout.content.view.AbstractMDContentBase;

public class TopVisibilityHelper extends AbstractVisibilityHelper {
    public TopVisibilityHelper(AbstractMDContentBase content) {
        super(content);
    }

    @Override
    public void hide(HText text, ICallable onComplete) {
        hide(text, getHidePosYUp(text), onComplete);
    }

    @Override
    public void show(HText text, ICallable onComplete) {
        show(text, getHidePosYUp(text), onComplete);
    }
}
