package heli.component.shape.list.centerlist.model;

/**
 * Single line (model part) of CList
 */
public class CListLineModel {
    enum CListLineModelStatus {ENABLED, DISABLED}

    protected int num;
    protected String text;
    protected CListLineModel prev;
    protected CListLineModel next;
    protected CListLineModelStatus status;

    public CListLineModel(String text, CListLineModel prev) {
        this.next = null;
        this.prev = prev;
        this.text = text;
        if (prev != null) {
            prev.next = this;
            this.num = prev.num + 1;
        } else {
            this.num = 0;
        }
        enable();
    }

    public int getNum() {
        return num;
    }

    public String getText() {
        return text;
    }

    public CListLineModel prev() {
        return prev;
    }

    public CListLineModel next() {
        return next;
    }

    public boolean isEnabled() {
        return status == CListLineModelStatus.ENABLED;
    }

    public boolean isDisabled() {
        return status == CListLineModelStatus.DISABLED;
    }

    public void setPrev(CListLineModel prev) {
        this.prev = prev;
    }

    public void setNext(CListLineModel next) {
        this.next = next;
    }

    public void disable() {
        status = CListLineModelStatus.DISABLED;
    }

    public void enable() {
        status = CListLineModelStatus.ENABLED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CListLineModel)) return false;

        CListLineModel listLine = (CListLineModel) o;

        return text.equals(listLine.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return "LL" + num + "[" + text + "]" + (status == CListLineModelStatus.ENABLED ? "E" : "D");
    }
}
