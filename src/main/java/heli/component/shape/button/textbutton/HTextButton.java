package heli.component.shape.button.textbutton;

import heli.helper.ResourceHelper;
import heli.component.shape.IShapeResizable;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.fx.ext.HText;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

import static heli.component.shape.button.textbutton.HTextButton.State.INACTIVE;
import static heli.component.shape.button.textbutton.HTextButton.State.REGULAR;

public class HTextButton extends HText implements IShapeResizable {
    enum State {REGULAR, INACTIVE}

    protected Color colorRegular;
    protected Color colorInactive;
    protected Color colorHovered;

    private State state;
    private double defaultFontSize = 19;
    private String fontUrl;

    public HTextButton(String text, Color cRegular, Color cInactive, Color cHovered, String fontUrl, double size) {
        super(text);
        colorRegular = cRegular;
        colorInactive = cInactive;
        colorHovered = cHovered;
        defaultFontSize = size;
        this.fontUrl = fontUrl;
        setFont(ResourceHelper.font(HTextButton.class, fontUrl, size));
        setSmooth(true);
        setInactive();
        registerMouseEvents();
    }

    public void setRegular() {
        setFill(colorRegular);
        state = REGULAR;
        setCursor(Cursor.DEFAULT);
    }

    public void setInactive() {
        setFill(colorInactive);
        state = INACTIVE;
        setCursor(Cursor.DEFAULT);
    }

    public void setHovered() {
        setFill(colorHovered);
        setCursor(Cursor.HAND);
    }

    @Override
    public void resize(double scaleFactor) {
        setFont(ResourceHelper.font(HTextButton.class, fontUrl, getFontSize(scaleFactor)));
    }

    protected void registerMouseEvents() {
        setOnMouseEntered(event -> setHovered());
        setOnMouseExited(event -> {
            if (state == REGULAR) {
                setRegular();
            } else {
                setInactive();
            }
        });
        setOnMousePressed(e -> {
            if (!isLocked()) HT.kill(this, "clk").to(this, 50, Ease.defaults, "clk").s(0.9);
        });
        setOnMouseReleased(e -> {
            if (!isLocked()) HT.kill(this, "clk").to(this, 100, Ease.defaults, "clk").s(1);
        });
    }

    protected double getFontSize(double scaleFactor) {
        if (scaleFactor == 1) return defaultFontSize;

        return Math.round(10 * (defaultFontSize * scaleFactor) - 0.49) / 10;
    }
}