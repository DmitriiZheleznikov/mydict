package md.skin.colorschema.impl.pojo.top;

import md.skin.colorschema.i.bg.IMDBgColorSchema;
import md.skin.colorschema.i.part.IMDButtonColorSchema;
import md.skin.colorschema.i.top.IMDTopColorSchema;
import md.skin.colorschema.impl.pojo.bg.MDBgColorSchema;
import md.skin.colorschema.impl.pojo.part.MDButtonColorSchema;

public class MDTopColorSchema implements IMDTopColorSchema {
    MDBgColorSchema bg;
    MDButtonColorSchema button;

    public MDTopColorSchema bg(MDBgColorSchema value) {
        this.bg = value;
        return this;
    }

    public MDTopColorSchema button(MDButtonColorSchema value) {
        this.button = value;
        return this;
    }

    @Override
    public MDBgColorSchema bg() {
        return bg;
    }

    @Override
    public MDButtonColorSchema button() {
        return button;
    }
}
