package heli.component.shape.list.centerlist.view.effect.appear.impl;

import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.tween.HTween;
import heli.htweener.tween.ICallable;

public class FadeListAppearEffect extends AbstractAppearEffect {

    @Override
    public void appear(CListView listView, CListModelWindow window, ICallable onComplete) {
        listView.locate(window);
        saveState(listView);
        listView.setVisible(false);

        for (int i = 0; i < CListView.SIZE; i++) {
            HTween tween = HT.to(listView.lines()[i], 800, Ease.cubicOut, NAME).o(SAVE[i].alpha);
            if (listView.lines()[i].isActive()) {
                tween.prop(0, 1, lineCoreOpacity);
            }
        }

        HT.to(900).onComplete(onComplete);
    }

    @Override
    public void disappear(CListView listView, ICallable onComplete) {
        for (int i = 0; i < CListView.SIZE; i++) {
            HTween tween = HT.to(listView.lines()[i], 800, Ease.cubicIn, NAME).o(0);
            if (listView.lines()[i].isActive()) {
                tween.prop(1, 0, lineCoreOpacity);
            }
        }

        HT.to(900).onComplete(onComplete);
    }

}
