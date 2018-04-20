package heli.component.shape.list.centerlist.view.effect.appear.impl;

import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.tween.ICallable;

public class SlideUpAppearEffect extends AbstractAppearEffect {
    @Override
    public void appear(CListView listView, CListModelWindow window, ICallable onComplete) {
        listView.locate(window);
        saveState(listView);

        for (CListLineView line : listView.lines()) {
            line.setY(line.getY() + listView.getBgRect().getHeight()*1.5);
            line.setOpacity(line.getOpacity()/2);
            if (line.isActive()) line.setCoreOpacity(0);
        }

        int finishTime = 0;

        for (int i = 0; i < CListView.SIZE; i++) {
            HT.to(listView.lines()[i], 800 + i*100, Ease.cubicOut, NAME).y(SAVE[i].y).o(SAVE[i].alpha);
            final int ii = i;
            if (listView.lines()[i].isActive()) HT.to(400).onComplete(() -> {
                HT.to(listView.lines()[ii], 400, Ease.cubicOut, NAME).prop(0, 1, lineCoreOpacity);
            });
            finishTime = i*100;
        }

        HT.to(900+finishTime).onComplete(onComplete);
    }

    @Override
    public void disappear(CListView listView, ICallable onComplete) {
        int finishTime = 0;
        listView.clip();

        for (CListLineView line : listView.lines()) {
            if (line.isActive()) HT.to(line, 400).prop(1, 0, lineCoreOpacity);
        }

        for (int i = CListView.SIZE-1; i >=0 ; i--) {
            HT.to(listView.lines()[i], 800 + (CListView.SIZE-i)*100, Ease.cubicIn, NAME).addY(listView.getBgRect().getHeight()*1.5)
                    .o(listView.lines()[i].getOpacity()/2);
            finishTime = (CListView.SIZE-i)*100;
        }

        HT.to(900+finishTime).onComplete(onComplete);
    }
}
