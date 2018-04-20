package heli.component.shape.list.centerlist.view.effect.scroll.impl;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;

public class RemoveScrollEffect extends CommonScrollEffect {
    @Override
    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op) {
        CListLineView lineC = listView.getCurrentLine();
        HT.to(lineC, duration(), Ease.cubicIn, NAME).addX(lineC.getWidth()*1.5).o(0.5).prop(1, 0, lineCoreOpacity).onComplete(()-> {
            super.performInternal(listView, modelWindow, op, lineC);
        });
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
