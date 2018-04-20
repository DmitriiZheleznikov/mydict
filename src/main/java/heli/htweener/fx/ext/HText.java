package heli.htweener.fx.ext;

import heli.htweener.tweenable.ITweenableColor;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HText extends Text implements ITweenableShape, ITweenableColor {
    private boolean isLocked = false;

    public HText() {
    }

    public HText(Font font, Color color) {
        setFont(font);
        setFill(color);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public HText(String text) {
        super(text);
    }

    public HText(double x, double y, String text) {
        super(x, y, text);
    }

    @Override
    public Color getColor() {
        return (Color)getFill();
    }

    @Override
    public void setColor(Color value) {
        setFill(value);
    }

    @Override
    public double getWidth() {
        return getBoundsInParent().getWidth();
    }

    @Override
    public void setWidth(double value) {
        throw new RuntimeException("Not Supported");
    }

    @Override
    public double getHeight() {
        return getBoundsInParent().getHeight();
    }

    @Override
    public void setHeight(double value) {
        throw new RuntimeException("Not Supported");
    }
}
