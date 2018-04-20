package heli.htweener.fx.ext;

import heli.htweener.tweenable.ITweenableColor;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HCircle extends Circle implements ITweenableShape, ITweenableColor {
    @Override
    public double getX() {
        return getCenterX();
    }

    @Override
    public void setX(double x) {
        setCenterX(x);
    }

    @Override
    public double getY() {
        return getCenterY();
    }

    @Override
    public void setY(double y) {
        setCenterY(y);
    }

    @Override
    public double getWidth() {
        return getRadius();
    }

    @Override
    public void setWidth(double value) {
        setRadius(value);
    }

    @Override
    public double getHeight() {
        return getHeight();
    }

    @Override
    public void setHeight(double value) {
        setRadius(value);
    }

    @Override
    public Color getColor() {
        return (Color)getFill();
    }

    @Override
    public void setColor(Color value) {
        setFill(value);
    }
}
