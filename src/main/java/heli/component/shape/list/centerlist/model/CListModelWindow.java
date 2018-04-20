package heli.component.shape.list.centerlist.model;

import java.util.Arrays;

import static heli.component.shape.list.centerlist.model.CListModel.SIZE;

/** A bridge between "model" of list and "view". As "model" contains entire list and "view" only its active part, this class gives it */
public class CListModelWindow {
    private CListLineModel[] lines;
    private int current;

    public CListModelWindow() {
        this.lines = new CListLineModel[SIZE];
        this.current = -1;
    }

    public CListLineModel[] getLines() {
        return lines;
    }

    public void setLines(CListLineModel[] lines) {
        this.lines = lines;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public CListLineModel getLine(int n) {
        return lines[n];
    }

    public boolean isFirst() {
        return current == 1;
    }

    public boolean isLast() {
        return current == SIZE-2;
    }

    public void copyFrom(CListModelWindow from) {
        this.current = from.current;
        for (int i = 0; i < lines.length; i++) {
            this.lines[i] = from.lines[i];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CListModelWindow)) return false;

        CListModelWindow that = (CListModelWindow) o;

        if (current != that.current) return false;
        return Arrays.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(lines);
        result = 31 * result + current;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ModelWindow" + current + "[");
        for (CListLineModel l : lines) {
            sb.append(l == null ? "null" : l.num).append(",");
        }
        return sb.append("]").toString();
    }
}
