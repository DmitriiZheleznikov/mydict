package heli.htweener.tweenable;

public interface ITweenableShape {
    double getX();
    void setX(double x);
    double getY();
    void setY(double y);
    double getOpacity();
    void setOpacity(double alpha);
    double getRotate();
    void setRotate(double rotation);
    double getScaleX();
    void setScaleX(double scaleX);
    double getScaleY();
    void setScaleY(double scaleY);
    boolean isVisible();
    void setVisible(boolean visible);
    double getWidth();
    void setWidth(double value);
    double getHeight();
    void setHeight(double value);
}
