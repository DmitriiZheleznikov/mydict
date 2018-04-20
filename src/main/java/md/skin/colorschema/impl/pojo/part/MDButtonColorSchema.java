package md.skin.colorschema.impl.pojo.part;

import javafx.scene.paint.Color;
import md.skin.colorschema.i.part.IMDButtonColorSchema;

public class MDButtonColorSchema implements IMDButtonColorSchema {
    Color normal;
    Color hovered;
    Color inactive;

    public MDButtonColorSchema normal(Color value) {
        this.normal = value;
        return this;
    }

    public MDButtonColorSchema hovered(Color value) {
        this.hovered = value;
        return this;
    }

    public MDButtonColorSchema inactive(Color value) {
        this.inactive = value;
        return this;
    }

    @Override
    public Color normal() {
        return normal;
    }

    @Override
    public Color hovered() {
        return hovered;
    }

    @Override
    public Color inactive() {
        return inactive;
    }
}
