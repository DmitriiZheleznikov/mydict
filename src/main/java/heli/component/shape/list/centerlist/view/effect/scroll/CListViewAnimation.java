package heli.component.shape.list.centerlist.view.effect.scroll;

import heli.component.shape.list.centerlist.model.CListModel;
import heli.component.shape.list.centerlist.model.CListModelOperation;

public class CListViewAnimation {
    public enum Animation {END, COMMON, SCROLL, MOVE, REMOVE, RESTORE}

    public static Animation calcAnimation(CListModelOperation op, int prevWindowPos, int nextWindowPos) {
        if (op.getAction() == CListModelOperation.Action.NULL) {
            if (prevWindowPos == nextWindowPos) {
                if (prevWindowPos == ((CListModel.SIZE-1)/2) && CListModelOperation.Scroll.NULL != op.getScroll()) {
                    return Animation.SCROLL;
                }
                return Animation.END;
            }
            return Animation.MOVE;
        }

        if (op.getAction() == CListModelOperation.Action.REMOVE) {
            return Animation.REMOVE;
        }

        if (op.getAction() == CListModelOperation.Action.RESTORE) {
            return Animation.RESTORE;
        }

        return Animation.COMMON;
    }
}
