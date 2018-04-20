package md.skin.colorschema.impl.pojo;

import md.skin.colorschema.i.IMDColorSchema;
import md.skin.colorschema.impl.pojo.bg.MDBgColorSchema;
import md.skin.colorschema.impl.pojo.bottom.MDBottomColorSchema;
import md.skin.colorschema.impl.pojo.center.MDCenterColorSchema;
import md.skin.colorschema.impl.pojo.top.MDTopColorSchema;

public class MDColorSchema implements IMDColorSchema {
//    MDBgColorSchema bg;
    MDTopColorSchema top;
    MDCenterColorSchema center;
    MDBottomColorSchema bottom;

//    public MDColorSchema bg(MDBgColorSchema value) {
//        this.bg = value;
//        return this;
//    }

    public MDColorSchema top(MDTopColorSchema value) {
        this.top = value;
        return this;
    }

    public MDColorSchema center(MDCenterColorSchema value) {
        this.center = value;
        return this;
    }

    public MDColorSchema bottom(MDBottomColorSchema value) {
        this.bottom = value;
        return this;
    }

//    @Override
//    public MDBgColorSchema bg() {
//        return bg;
//    }

    @Override
    public MDTopColorSchema top() {
        return top;
    }

    @Override
    public MDCenterColorSchema center() {
        return center;
    }

    @Override
    public MDBottomColorSchema bottom() {
        return bottom;
    }
}
