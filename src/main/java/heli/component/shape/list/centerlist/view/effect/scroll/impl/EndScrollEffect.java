package heli.component.shape.list.centerlist.view.effect.scroll.impl;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;

public class EndScrollEffect extends CommonScrollEffect {
    @Override
    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op) {
        double difY = listView.lines()[0].getHeight() / 10;
        final double diff = modelWindow.isFirst() ? difY * -1 : difY;

        final CListLineView line = listView.getCurrentLine();

        HT.to(line, 30, Ease.sineOut, NAME).addY(diff).onComplete(() -> {
            HT.to(line, 30, Ease.sineInOut, NAME).addY(-diff);
        });
    }

    @Override
    protected int duration() {
        return 30;
    }
}
