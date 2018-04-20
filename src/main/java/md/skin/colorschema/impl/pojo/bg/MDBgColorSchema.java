package md.skin.colorschema.impl.pojo.bg;

import javafx.scene.paint.Color;
import md.skin.colorschema.i.bg.IMDBgColorSchema;

public class MDBgColorSchema implements IMDBgColorSchema {
    private Color shadow;
    private Color background;

    public MDBgColorSchema shadow(Color value) {
        this.shadow = value;
        return this;
    }

    public MDBgColorSchema background(Color value) {
        this.background = value;
        return this;
    }

    @Override
    public Color shadow() {
        return shadow;
    }

    @Override
    public Color background() {
        return background;
    }
}
