package heli.component.shape.list.centerlist.view.effect.scroll.impl;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;

public class RestoreScrollEffect extends CommonScrollEffect {
    @Override
    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op) {
        super.performInternal(listView, modelWindow, op);

        HT.to(duration()+20).onComplete(() -> {
            CListLineView line = getFutureCurrentLine(listView);
            if (line != null) {
                CListLineView fakeLine = listView.showFakeLine(line.getFuturePosition(), modelWindow.getLines()[modelWindow.getCurrent()]);
                final double x = fakeLine.getX();
                fakeLine.setX(fakeLine.getWidth()*1.5);
                fakeLine.setOpacity(0.5);
                fakeLine.setCoreOpacity(0);
                HT.kill(fakeLine).to(fakeLine, duration(), Ease.cubicOut, NAME).x(x).o(1).prop(0, 1, lineCoreOpacity).onComplete(() -> {
                    listView.hideFakeLine();
                });
            }
        });
    }

    public CListLineView getFutureCurrentLine(CListView listView) {
        for (CListLineView line : listView.lines()) {
            if (line.getFuturePosition().getStatus() == CListLineView.Status.ACTIVE) return line;
        }

        return null;
    }

    @Override
    protected int duration() {
        return 200;
    }

    @Override
    protected int onCompleteTime() {
        return 20 + duration()*2;
    }
}
