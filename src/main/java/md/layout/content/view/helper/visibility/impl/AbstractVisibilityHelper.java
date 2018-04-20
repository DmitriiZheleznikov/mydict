package md.layout.content.view.helper.visibility.impl;

import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.fx.ext.HText;
import heli.htweener.tween.ICallable;
import md.layout.content.view.AbstractMDContentBase;
import md.layout.content.view.helper.visibility.ISpecificVisibilityHelper;

public abstract class AbstractVisibilityHelper implements ISpecificVisibilityHelper{
    private AbstractMDContentBase content;

    public AbstractVisibilityHelper(AbstractMDContentBase content) {
        this.content = content;
    }

    @Override
    public void hide(HText text) {
        hide(text, null);
    }

    @Override
    public void show(HText text) {
        show(text, null);
    }

    protected void hide(HText object, double targetY, ICallable onComplete) {
        if (object.isVisible()) {
            object.setLocked(true);
            //locate();
            HT.to(object, 300, Ease.quintIn).y(targetY).o(0).s(0.9).onComplete(onComplete);
        }
    }

    protected void show(HText object, double initialY, ICallable onComplete) {
        if (!object.isVisible()) {
            content.locate(object);
            double targetY = object.getY();
            object.setY(initialY);
            HT.to(object, 300, Ease.quadOut).y(targetY).o(1).s(1).onComplete(() -> {
                if (onComplete != null) onComplete.call();
                object.setLocked(false);
            });
        }
    }

    protected double getHidePosYUp(HText object) {
        return -object.getHeight() / 2 - 1;
    }

    protected double getHidePosYDown(HText object) {
        return content.getScene().getHeight() /*+ object.getHeight() / 2*/ + 1;
    }
}
