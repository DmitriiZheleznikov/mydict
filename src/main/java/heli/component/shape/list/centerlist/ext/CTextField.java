package heli.component.shape.list.centerlist.ext;

import heli.helper.NodeStyleHelper;
import heli.helper.ResourceHelper;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CTextField extends TextField implements ITweenableShape {
    private String fontUrl;
    private double fontSizeDef;

    public CTextField(String text, Color color, String fontUrl, double fontSizeDef) {
        super(text);
        this.fontUrl = fontUrl;
        this.fontSizeDef = fontSizeDef;
        setFont(ResourceHelper.font(CText.class, fontUrl, fontSizeDef));
        setTextColor(color);
    }

    public void resize(double scaleFactor) {
        setFont(ResourceHelper.font(CText.class, fontUrl, getFontSize(scaleFactor)));
    }

    public void setTextColor(Color color) {
        setStyle(NodeStyleHelper.addProperty(getStyle(), "-fx-text-inner-color", color));
    }

    public void setBgColor(Color color) {
        setStyle(NodeStyleHelper.addProperty(getStyle(), "-fx-control-inner-background", color));
    }

    private void removeStyle(String prop) {
        setStyle(NodeStyleHelper.removeProperty(getStyle(), prop));
    }

    protected double getFontSize(double scaleFactor) {
        if (scaleFactor == 1) return fontSizeDef;

        return Math.round(10*(fontSizeDef*scaleFactor)-0.49)/10;
    }

    @Override
    public double getX() {
        return getLayoutX();
    }

    @Override
    public void setX(double x) {
        setLayoutX(x);
    }

    @Override
    public double getY() {
        return getLayoutY();
    }

    @Override
    public void setY(double y) {
        setLayoutY(y);
    }

    @Override
    public void setWidth(double value) {
        super.setWidth(value);
    }

    @Override
    public void setHeight(double value) {
        super.setHeight(value);
    }
}
