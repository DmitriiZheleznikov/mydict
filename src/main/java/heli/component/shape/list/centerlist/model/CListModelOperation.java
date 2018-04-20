package heli.component.shape.list.centerlist.model;

public class CListModelOperation {
    public enum Action {REMOVE, RESTORE, NULL}
    public enum Scroll {NEXT, PREV, NULL}

    private int lineNum;
    private Action action;
    private Scroll scroll;

    public CListModelOperation(int lineNum, Action action, Scroll scroll) {
        this.lineNum = lineNum;
        this.action = action;
        this.scroll = scroll;
    }

    public int getLineNum() {
        return lineNum;
    }

    public Action getAction() {
        return action;
    }

    public Scroll getScroll() {
        return scroll;
    }

    public CListModelOperation getReverseOperation() {
        Action rAction = Action.NULL;
        Scroll rScroll = Scroll.NULL;

        if (action == Action.REMOVE) rAction = Action.RESTORE;
        if (action == Action.RESTORE) rAction = Action.REMOVE;

        if (scroll == Scroll.NEXT) rScroll = Scroll.PREV;
        if (scroll == Scroll.PREV) rScroll = Scroll.NEXT;

        return new CListModelOperation(lineNum, rAction, rScroll);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CListModelOperation)) return false;

        CListModelOperation that = (CListModelOperation) o;

        if (lineNum != that.lineNum) return false;
        if (action != that.action) return false;
        return scroll == that.scroll;
    }

    @Override
    public int hashCode() {
        int result = lineNum;
        result = 31 * result + action.hashCode();
        result = 31 * result + scroll.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LMOp{" +
                "" + lineNum +
                ", " + action +
                ", " + scroll +
                '}';
    }
}
