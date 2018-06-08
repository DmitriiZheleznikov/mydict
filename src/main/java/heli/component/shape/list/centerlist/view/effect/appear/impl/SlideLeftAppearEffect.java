package heli.component.shape.list.centerlist.view.effect.appear.impl;

import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.tween.ICallable;

public class SlideLeftAppearEffect extends AbstractAppearEffect {
    @Override
    public void appear(CListView listView, CListModelWindow window, ICallable onComplete) {
        listView.locate(window);
        saveState(listView);
        listView.setVisible(false);

        for (CListLineView line : listView.lines()) {
            line.setX(line.getX() + listView.getBgRect().getWidth()*1.5);
            line.setOpacity(line.getOpacity()/2);
            if (line.isActive()) line.setCoreOpacity(0);
        }

        int finishTime = 0;

        for (int i = 0; i < CListView.SIZE; i++) {
            HT.to(listView.lines()[i], 500 + i*100, Ease.cubicOut, NAME).x(SAVE[i].x).o(SAVE[i].alpha);
            final int ii = i;
            if (listView.lines()[i].isActive()) HT.to(400).onComplete(() -> {
                HT.to(listView.lines()[ii], 250, Ease.cubicOut, NAME).prop(0, 1, lineCoreOpacity);
            });
            finishTime = i*100;
        }

        HT.to(600+finishTime).onComplete(onComplete);
    }

    @Override
    public void disappear(CListView listView, ICallable onComplete) {
        int finishTime = 0;
        listView.clip();

        for (CListLineView line : listView.lines()) {
            if (line.isActive())
                HT.to(line, 250).prop(1, 0, lineCoreOpacity);
        }

        for (int i = 0; i < CListView.SIZE ; i++) {
            HT.to(listView.lines()[i], 500 + i*100, Ease.cubicIn, NAME).addX(listView.getBgRect().getWidth()*1.5)
                    .o(listView.lines()[i].getOpacity()/2);
            finishTime = i*100;
        }

        HT.to(600+finishTime).onComplete(onComplete);
    }
}
