package heli.component.shape.list.centerlist.view.effect.appear.impl;

import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.property.function.IPropFunction;
import heli.htweener.tween.ICallable;
import heli.htweener.tweenable.TwC;
import heli.component.shape.list.centerlist.view.effect.appear.IListAppearEffect;
import md.shape.mdcenterlist.view.MDListLineView;
import md.shape.mdcenterlist.view.MDListView;

abstract public class AbstractAppearEffect implements IListAppearEffect {
    static final String NAME = "appear_list";
    static final TwC[] SAVE = new TwC[MDListView.SIZE];
    static {
        for (int i = 0; i < SAVE.length; i++) {
            SAVE[i] = new TwC();
        }
    }

    public static final IPropFunction lineCoreOpacity = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((MDListLineView)target).setCoreOpacity(value);
        }
    };

    void complete(ICallable onComplete) {
        if (onComplete != null) onComplete.call();
    }

    protected void saveState(CListView list) {
        for (int i = 0; i < CListView.SIZE; i++) {
            SAVE[i].x = list.lines()[i].getX();
            SAVE[i].y = list.lines()[i].getY();
            SAVE[i].h = list.lines()[i].getHeight();
            SAVE[i].w = list.lines()[i].getWidth();
            SAVE[i].alpha = list.lines()[i].getOpacity();
            //System.out.println(SAVE[i]);
        }
    }
}
