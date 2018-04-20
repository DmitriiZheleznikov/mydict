package heli.component.shape.list.centerlist.ext;

import heli.helper.ResourceHelper;
import heli.htweener.fx.ext.HText;
import javafx.scene.paint.Color;

public class CText extends HText {
    private String fontUrl;
    private double fontSizeDef;

    public CText(String text, Color color, String fontUrl, double fontSizeDef) {
        super(text);
        this.fontUrl = fontUrl;
        this.fontSizeDef = fontSizeDef;
        setFont(ResourceHelper.font(CText.class, fontUrl, fontSizeDef));
        setFill(color);
    }

    public void resize(double scaleFactor) {
        setFont(ResourceHelper.font(CText.class, fontUrl, getFontSize(scaleFactor)));
    }

    protected double getFontSize(double scaleFactor) {
        if (scaleFactor == 1) return fontSizeDef;

        return Math.round(10*(fontSizeDef*scaleFactor)-0.49)/10;
    }
}

