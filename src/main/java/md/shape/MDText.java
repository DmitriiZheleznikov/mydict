package md.shape;

import heli.helper.ResourceHelper;
import heli.htweener.fx.ext.HText;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static md.FontConstant.FONT_UBUTNU_R_SIZE_DEFAULT;
import static md.FontConstant.FONT_UBUTNU_R_URL;

public class MDText extends HText {
    private static final Font FONT_UBUTNU_R_DEFAULT = ResourceHelper.font(MDText.class, FONT_UBUTNU_R_URL, FONT_UBUTNU_R_SIZE_DEFAULT);
    private String fontUrl = FONT_UBUTNU_R_URL;
    private double fontSizeDef = FONT_UBUTNU_R_SIZE_DEFAULT;

    public MDText(String text, Color color) {
        super(text);
        setFont(FONT_UBUTNU_R_DEFAULT);
        setFill(color);
    }

    public MDText(String text, Color color, String fontUrl) {
        super(text);
        this.fontUrl = fontUrl;
        setFont(ResourceHelper.font(MDText.class, fontUrl, FONT_UBUTNU_R_SIZE_DEFAULT));
        setFill(color);
    }

    public MDText(String text, Color color, String fontUrl, double fontSizeDef) {
        super(text);
        this.fontUrl = fontUrl;
        this.fontSizeDef = fontSizeDef;
        setFont(ResourceHelper.font(MDText.class, fontUrl, fontSizeDef));
        setFill(color);
    }

    public void resize(double scaleFactor) {
        setFont(ResourceHelper.font(MDText.class, fontUrl, getFontSize(scaleFactor)));
    }

    protected double getFontSize(double scaleFactor) {
        if (scaleFactor == 1) return fontSizeDef;

        return Math.round(10*(fontSizeDef*scaleFactor)-0.49)/10;
    }
}
