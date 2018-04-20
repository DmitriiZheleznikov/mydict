package heli.component.shape.list.centerlist.view;

/**
 * This class is a middle point of "location" of list representation on screen. It's required because of animation.
 */
public class CListLineViewFuturePosition {
    private double width;
    private double height;
    private double x;
    private double y;
    private double opacity;
    private double coreOpacity;
    private CListLineView.Status status;
    private int modelLineNumber;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public double getCoreOpacity() {
        return coreOpacity;
    }

    public void setCoreOpacity(double coreOpacity) {
        this.coreOpacity = coreOpacity;
    }

    public CListLineView.Status getStatus() {
        return status;
    }

    public void setStatus(CListLineView.Status status) {
        this.status = status;
    }

    public int getModelLineNumber() {
        return modelLineNumber;
    }

    public void setModelLineNumber(int modelLineNumber) {
        this.modelLineNumber = modelLineNumber;
    }

    @Override
    public String toString() {
        return "ViewWinLine[xy=" + x + "," + y + ", wh=" + width + "," + height + "]";
    }
}
