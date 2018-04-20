package heli.htweener.fx.ext;

import heli.htweener.tweenable.ITweenableColor;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HRectangle extends Rectangle implements ITweenableShape, ITweenableColor {
    @Override
    public Color getColor() {
        return (Color)getFill();
    }

    @Override
    public void setColor(Color value) {
        setFill(value);
    }
}
