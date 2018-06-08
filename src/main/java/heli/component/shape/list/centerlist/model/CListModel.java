package heli.component.shape.list.centerlist.model;

/**
 * Model part of CList. This class provides function to control list (searching, navigation, calculation of window for draw)
 */
public class CListModel {
    /** Size is required for window calculation and depends on view. Two of lines are usually invisible (one before visible list and one after) */
    public static final int SIZE = 7;

    /** Very first text, in spite of its status (enabled/disabled) */
    private CListLineModel first;
    /** Current text list is staying it */
    private CListLineModel current;
    /** Number of active (enabled) lines */
    private int length;
    /** Total number of lines */
    private int lengthTotal;

    /**
     * Constructor takes any model line, it will find first by itself,
     * it also calculates total number of elements and number of active (enabled) ones
     */
    public CListModel(CListLineModel any) {
        if (any == null) {
            current = null;
            return;
        }

        //Search first text
        first = any;
        while (first.prev() != null) {
            first = first.prev();
        }

        //Search last text and compute count
        lengthTotal = 1;
        length = first.isEnabled() ? 1 : 0;
        CListLineModel last = first;
        while (last.next() != null) {
            last = last.next();
            if (last.isEnabled()) length++;
            lengthTotal++;
        }

        //Current text
        initCurrent();
    }

    private void initCurrent() {
        current = first;
        if (current == null) return;
        if (current.isDisabled()) {
            next();
        }
    }

    /** How far current in total list in percent */
    public double getProgressInPercent() {
        if (current == null) return 0;
        return 100*current.num/(lengthTotal-1);
    }

    public int getLengthTotal() {
        return lengthTotal;
    }

    public int getLengthEnabled() {
        return length;
    }

    public CListLineModel getFirstLine() {
        return first;
    }

    public CListLineModel getCurrentLine() {
        return current;
    }

    private CListLineModel searchPrev(CListLineModel startLine) {
        if (startLine == null) return null;

        CListLineModel line = startLine;
        do {
            line = line.prev();
        } while (line != null && line.isDisabled());

        return line;
    }

    private CListLineModel searchNext(CListLineModel startLine) {
        if (startLine == null) return null;

        CListLineModel line = startLine;
        do {
            line = line.next();
        } while (line != null && line.isDisabled());

        return line;
    }

    private CListLineModel searchByNum(int num) {
        if (num < 0 || current == null) return null;

        if (current.num == num) return current;

        //search backward as most expected
        CListLineModel temp = current;
        while (temp.prev() != null) {
            temp = temp.prev();
            if (temp.num == num) return temp;
        }

        //Search forward
        temp = current;
        while (temp.next() != null) {
            temp = temp.next();
            if (temp.num == num) return temp;
        }

        return null;
    }

    private CListLineModel searchByText(String textToFind) {
        if (textToFind == null || textToFind.length() == 0 || current == null) return null;

        if (textToFind.equals(current.getText())) return current;

        //search forward as most expected
        CListLineModel temp = current;
        while (temp.next() != null) {
            temp = temp.next();
            if (textToFind.equals(temp.getText())) return temp;
        }

        //Search backward
        temp = current;
        while (temp.prev() != null) {
            temp = temp.prev();
            if (textToFind.equals(temp.getText())) return temp;
        }

        return null;
    }

    /** Searches previous enabled line if possible and calculates operation to perform and save in history */
    public CListModelOperation prev() {
        CListLineModel cur = current;
        CListLineModel line = searchPrev(current);
        if (line != null) {
            current = line;
            return new CListModelOperation(cur.num, CListModelOperation.Action.NULL, CListModelOperation.Scroll.PREV);
        }
        return new CListModelOperation(cur.num, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
    }

    /** Searches next enabled line if possible and calculates operation to perform and save in history */
    public CListModelOperation next() {
        CListLineModel cur = current;
        CListLineModel line = searchNext(current);
        if (line != null) {
            current = line;
            return new CListModelOperation(cur.num, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NEXT);
        }
        return new CListModelOperation(cur.num, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
    }

    /** Disables current line and searcher next or previous enabled line, then generates operation to perform and save in history */
    public CListModelOperation disableCurrent() {
        if (current == null) {
            return new CListModelOperation(0, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
        }
        CListLineModel cur = current;
        current.disable();
        length--;

        //Search enabled
        CListLineModel line = searchNext(current);
        if (line != null) {
            current = line;
            return new CListModelOperation(cur.num, CListModelOperation.Action.REMOVE, CListModelOperation.Scroll.NEXT);
        } else {
            line = searchPrev(current);
            if (line != null) {
                current = line;
                return new CListModelOperation(cur.num, CListModelOperation.Action.REMOVE, CListModelOperation.Scroll.PREV);
            } else {
                current = null;
            }
        }

        return new CListModelOperation(cur.num, CListModelOperation.Action.REMOVE, CListModelOperation.Scroll.NULL);
    }

    /** Restores line by number and set it as current, then generates operation to perform and save in history */
    public CListModelOperation restore(int num) {
        int prevNum = current.num;
        CListLineModel foundLine = searchByNum(num);

        if (foundLine != null && foundLine.isDisabled()) {
            current = foundLine;
            current.enable();
            length++;

            if (prevNum > num)
                return new CListModelOperation(num, CListModelOperation.Action.RESTORE, CListModelOperation.Scroll.PREV);
            if (prevNum < num)
                return new CListModelOperation(num, CListModelOperation.Action.RESTORE, CListModelOperation.Scroll.NEXT);

            return new CListModelOperation(num, CListModelOperation.Action.RESTORE, CListModelOperation.Scroll.NULL);
        }

        return new CListModelOperation(prevNum, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
    }

    /** Usually is used for initial setting of position, for example after restoration of program where list should be opened in the middle */
    public void goTo(String text) {
        CListLineModel line = searchByText(text);
        if (line != null) {
            if (line.isDisabled()) {
                CListLineModel temp = searchNext(line);
                if (temp == null) temp = searchPrev(line);
                line = temp;
            }

            current = line;
        }
    }

    /** Usually is used for performing backtracked operation when "undo" happens */
    public CListModelOperation applyOperation(CListModelOperation op) {
        if (op == null) return null;

        if (op.getAction() == CListModelOperation.Action.REMOVE) disableCurrent();
        if (op.getAction() == CListModelOperation.Action.RESTORE) return restore(op.getLineNum());

        if (op.getScroll() == CListModelOperation.Scroll.NEXT) next();
        if (op.getScroll() == CListModelOperation.Scroll.PREV) prev();

        return op;
    }

    public int getLength() {
        return length;
    }

    public int getCurrentNum() {
        return current.num;
    }

    public int getMiddlePos() {
        return (SIZE - 1) / 2;
    }

    public CListModelWindow getWindow() {
        return getWindow(new CListModelWindow());
    }

    /** Calculates window to use in "view" for drawing list */
    public CListModelWindow getWindow(CListModelWindow window) {
        if (window == null) window = new CListModelWindow();
        CListLineModel[] lines = window.getLines();

        if (current == null) {
            for (int i = 0; i < SIZE; i++) lines[i] = null;
            window.setCurrent(1);
            return window;
        }

        int curPos = SIZE - 2; //Two invisible lines (before list and after)
        int midPos = getMiddlePos();

        //Initial fill
        lines[curPos] = current;
        lines[curPos+1] = searchNext(current);
        for (int i = curPos; i > 0; i--) lines[i-1] = searchPrev(lines[i]);

        //Shift left until curPos=middle or "searchNext" is null
        if (lines[SIZE-1] != null) {
            while (curPos > midPos) {
                if (lines[SIZE-1] == null) break;
                shiftLeft(lines);
                lines[SIZE-1] = searchNext(lines[SIZE-1]);
                curPos--;
            }
        }

        //Shift left until pos 1 is filled
        while (lines[1] == null) {
            shiftLeft(lines);
            lines[SIZE-1] = searchNext(lines[SIZE-1]);
            curPos--;
        }

        window.setCurrent(curPos);

        return window;
    }

    protected void shiftLeft(CListLineModel[] lines) {
        for (int i = 0; i < SIZE-1; i++) lines[i] = lines[i+1];
    }

    /** Mostly for testing purpose */
    public String getTextRepresentationOfActualState() {
        return "LM{cur=\"" + current +
                "\" " + length +
                "/" + lengthTotal +
                '}';
    }

    @Override
    public String toString() {
        return getTextRepresentationOfActualState();
    }
}
