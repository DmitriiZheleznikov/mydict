package heli.component.shape.list.centerlist.view.effect.scroll.impl;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;

public class MoveScrollEffect extends CommonScrollEffect {
    @Override
    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op) {
        int currentLineNum = listView.getCurrentLineNumber();

        //if (isDirectionUp(listView, modelWindow)) {
        if (op.getScroll() == CListModelOperation.Scroll.PREV) {
            CListLineView lineP = listView.lines()[currentLineNum];
            CListLineView lineN = listView.lines()[currentLineNum - 1];

            double difY = lineP.getHeight() - lineN.getHeight();

            HT.to(lineN, 40, Ease.cubicOut, NAME).o(lineP.getOpacity()).prop(0, 1, lineCoreOpacity).h(lineP.getHeight());

            HT.to(lineP, 60, Ease.sineOut, NAME)
                    .o(1 - listView.calcDistance(currentLineNum-1, currentLineNum))
                    .prop(1, 0, lineCoreOpacity).addY(difY).h(lineN.getHeight());

            super.performInternal(listView, modelWindow, op, lineP, lineN);
        } else {
            CListLineView lineP = listView.lines()[currentLineNum];
            CListLineView lineN = listView.lines()[currentLineNum + 1];

            double difY = lineN.getHeight() - lineP.getHeight();

            HT.to(lineN, 40, Ease.cubicOut, NAME).addY(difY).h(lineP.getHeight()).o(lineP.getOpacity()).prop(0, 1, lineCoreOpacity);

            HT.to(lineP, 60, Ease.sineOut, NAME)
                    .o(1 - listView.calcDistance(currentLineNum+1, currentLineNum))
                    .h(lineN.getHeight()).prop(1, 0, lineCoreOpacity);

            super.performInternal(listView, modelWindow, op, lineP, lineN);
        }

    }

    @Override
    protected int duration() {
        return 80;
    }
}
