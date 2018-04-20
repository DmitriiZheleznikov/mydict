package md.skin.colorschema.impl.pojo.bottom;

import md.skin.colorschema.i.bg.IMDBgColorSchema;
import md.skin.colorschema.i.bottom.IMDBottomColorSchema;
import md.skin.colorschema.i.part.IMDButtonColorSchema;
import md.skin.colorschema.impl.pojo.bg.MDBgColorSchema;
import md.skin.colorschema.impl.pojo.part.MDButtonColorSchema;

public class MDBottomColorSchema implements IMDBottomColorSchema {
    MDBgColorSchema bg;
    MDButtonColorSchema button;

    public MDBottomColorSchema bg(MDBgColorSchema value) {
        this.bg = value;
        return this;
    }

    public MDBottomColorSchema button(MDButtonColorSchema value) {
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
