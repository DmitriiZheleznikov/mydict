package heli.htweener.tweenable;

public class TwC implements ITweenableShape {
    public double x;
    public double y;
    public double h;
    public double w;
    public double alpha = 1;
    public double rotation = 0;
    public double scaleX = 1;
    public double scaleY = 1;
    public boolean isVisible = true;

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getOpacity() {
        return alpha;
    }

    @Override
    public void setOpacity(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public double getRotate() {
        return rotation;
    }

    @Override
    public void setRotate(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public double getScaleX() {
        return scaleX;
    }

    @Override
    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    @Override
    public double getScaleY() {
        return scaleY;
    }

    @Override
    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public double getWidth() {
        return w;
    }

    @Override
    public void setWidth(double value) {
        w = value;
    }

    @Override
    public double getHeight() {
        return h;
    }

    @Override
    public void setHeight(double value) {
        h = value;
    }

    @Override
    public String toString() {
        return "TwC[xy=" + x + "," + y + ", wh=" + w + "," + h + ", v=" + isVisible + "]";
    }
}
