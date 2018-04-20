package heli.component.shape.list.centerlist.control;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.view.effect.scroll.CListViewAnimation;

import java.util.LinkedList;

/**
 * Contains stack of operations performed in CList, implementing on-memory history
 */
public class OperationsStack {
    protected LinkedList<CListModelOperation> opStack;

    public OperationsStack() {
        opStack = new LinkedList<>();
    }

    public void push(CListModelOperation modelOperation, CListViewAnimation.Animation animation) {
        if (modelOperation == null || animation == null) return;

        if (modelOperation.getScroll() == CListModelOperation.Scroll.NULL) return;
        if (animation == CListViewAnimation.Animation.COMMON) return;
        if (animation == CListViewAnimation.Animation.END) return;

        opStack.push(modelOperation);
    }

    public CListModelOperation poll() {
        return opStack.poll();
    }

    public int size() {
        return opStack.size();
    }
}
