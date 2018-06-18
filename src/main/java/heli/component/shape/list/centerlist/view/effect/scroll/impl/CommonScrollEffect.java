package heli.component.shape.list.centerlist.view.effect.scroll.impl;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListLineViewFuturePosition;
import heli.component.shape.list.centerlist.view.CListView;
import heli.component.shape.list.centerlist.view.effect.scroll.IScrollEffect;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.property.function.IPropFunction;
import heli.htweener.tween.ICallable;

public class CommonScrollEffect implements IScrollEffect {
    public static final String NAME = "ScrollEffect";

    public static final IPropFunction lineCoreOpacity = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((CListLineView)target).setCoreOpacity(value);
        }
    };

    void complete(ICallable onComplete) {
        if (onComplete != null) onComplete.call();
    }

    @Override
    public void perform(CListView listView, CListModelWindow modelWindow, CListModelOperation op, ICallable onComplete) {
        if (listView.isVisible()) {
            listView.setLocked(true);
            listView.calcWindow(modelWindow);

            performInternal(listView, modelWindow, op);

            HT.to(onCompleteTime()).onComplete(() -> {
                listView.locate(modelWindow);
                complete(onComplete);
                listView.setLocked(false);
            });
        }
    }

    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op) {
        performInternal(listView, modelWindow, null, null, null);
    }

    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op,
                                   CListLineView exclude1) {
        performInternal(listView, modelWindow, op, exclude1, null);
    }

    protected void performInternal(CListView listView, CListModelWindow modelWindow, CListModelOperation op,
                                   CListLineView exclude1, CListLineView exclude2) {
        for (CListLineView line : listView.lines()) {
            if (line.equals(exclude1)) continue;
            if (line.equals(exclude2)) continue;
            if (line.getModelLineNum() == -1) continue;

            CListLineViewFuturePosition wLine = line.getFuturePosition();
            CListLineView lineTo = lineTo(listView, line);

            HT.to(line, duration(), Ease.sineOut, NAME)
                    .x(wLineTo(lineTo, wLine).getX())
                    .y(wLineTo(lineTo, wLine).getY())
                    .w(wLineTo(lineTo, wLine).getWidth())
                    .h(wLineTo(lineTo, wLine).getHeight())
                    .o(wLineTo(lineTo, wLine).getOpacity())
                    .prop(line.getCoreOpacity(), wLineTo(lineTo, wLine).getCoreOpacity(), lineCoreOpacity);
        }
    }

    protected int duration() {
        return 120;
    }

    protected int onCompleteTime() {
        return duration() + 20;
    }

    protected CListLineViewFuturePosition wLineTo(CListLineView lineTo, CListLineViewFuturePosition wCurLine) {
        return lineTo == null ? wCurLine : lineTo.getFuturePosition();
    }

    protected CListLineView lineTo(CListView listView, CListLineView lineFrom) {
        for (CListLineView line : listView.lines()) {
            if (line.getFuturePosition().getModelLineNumber() == lineFrom.getModelLineNum()) {
                return line;
            }
        }
        return null;
    }
}
