package md.skin.colorschema.impl.pojo.part;

import javafx.scene.paint.Color;
import md.skin.colorschema.i.part.IMDProgressColorSchema;

public class MDProgressColorSchema implements IMDProgressColorSchema {
    Color min;
    Color max;
    Color fin;

    public MDProgressColorSchema min(Color value) {
        this.min = value;
        return this;
    }

    public MDProgressColorSchema max(Color value) {
        this.max = value;
        return this;
    }

    public MDProgressColorSchema fin(Color value) {
        this.fin = value;
        return this;
    }

    @Override
    public Color min() {
        return min;
    }

    @Override
    public Color max() {
        return max;
    }

    @Override
    public Color fin() {
        return fin;
    }
}
