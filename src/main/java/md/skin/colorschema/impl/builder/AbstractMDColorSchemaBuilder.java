package md.skin.colorschema.impl.builder;

import md.skin.colorschema.impl.pojo.MDColorSchema;
import md.skin.colorschema.impl.pojo.bg.MDBgColorSchema;
import md.skin.colorschema.impl.pojo.bottom.MDBottomColorSchema;
import md.skin.colorschema.impl.pojo.center.MDCenterColorSchema;
import md.skin.colorschema.impl.pojo.part.MDButtonColorSchema;
import md.skin.colorschema.impl.pojo.part.MDListColorSchema;
import md.skin.colorschema.impl.pojo.part.MDProgressColorSchema;
import md.skin.colorschema.impl.pojo.top.MDTopColorSchema;

abstract public class AbstractMDColorSchemaBuilder {
    public MDColorSchema build() {
        return new MDColorSchema().top(buildTop()).center(buildCenter()).bottom(buildBottom());
    }

    protected MDBgColorSchema buildBg() {
        return new MDBgColorSchema();
    }

    protected MDButtonColorSchema buildButton() {
        return new MDButtonColorSchema();
    }

    protected MDProgressColorSchema buildProgress() {
        return new MDProgressColorSchema();
    }

    protected MDListColorSchema buildList() {
        return new MDListColorSchema();
    }

    protected MDTopColorSchema buildTop() {
        return new MDTopColorSchema().bg(buildBg()).button(buildButton());
    }

    protected MDCenterColorSchema buildCenter() {
        return new MDCenterColorSchema().bg(buildBg()).startButton(buildButton()).progress(buildProgress()).list(buildList());
    }

    protected MDBottomColorSchema buildBottom() {
        return new MDBottomColorSchema().bg(buildBg()).button(buildButton());
    }
}
