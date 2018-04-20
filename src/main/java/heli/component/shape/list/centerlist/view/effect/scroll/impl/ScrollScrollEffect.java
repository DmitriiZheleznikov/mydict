package heli.component.shape.list.centerlist.view.effect.scroll.impl;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;

public class ScrollScrollEffect extends CommonScrollEffect {
    @Override
    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op) {
        int currentLineNum = listView.getCurrentLineNumber();

        CListLineView lineP = listView.lines()[currentLineNum - 1];
        CListLineView lineC = listView.lines()[currentLineNum];
        CListLineView lineN = listView.lines()[currentLineNum + 1];

        //if (isDirectionUp(listView, modelWindow)) {
        if (op.getScroll() == CListModelOperation.Scroll.PREV) {
            CListLineView temp = lineP;
            lineP = lineN;
            lineN = temp;
        }

        HT.to(lineC, duration(), Ease.sineOut, NAME).y(lineP.getY()).h(lineP.getHeight()).o(lineP.getOpacity()).prop(1, 0, lineCoreOpacity);
        HT.to(lineN, duration(), Ease.linear, NAME).y(lineC.getY()).h(lineC.getHeight()).o(lineC.getOpacity()).prop(0, 1, lineCoreOpacity).onComplete(() -> {
            listView.hideFakeLine();
        });
        listView.showFakeLine(lineC, lineN);

        super.performInternal(listView, modelWindow, op, lineC, lineN);
    }

    @Override
    protected int duration() {
        return 120;
    }
}
