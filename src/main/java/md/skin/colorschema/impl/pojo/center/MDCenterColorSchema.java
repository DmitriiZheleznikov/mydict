package md.skin.colorschema.impl.pojo.center;

import javafx.scene.paint.Color;
import md.skin.colorschema.i.center.IMDCenterColorSchema;
import md.skin.colorschema.impl.pojo.bg.MDBgColorSchema;
import md.skin.colorschema.impl.pojo.part.MDButtonColorSchema;
import md.skin.colorschema.impl.pojo.part.MDListColorSchema;
import md.skin.colorschema.impl.pojo.part.MDProgressColorSchema;

public class MDCenterColorSchema implements IMDCenterColorSchema {
    MDBgColorSchema bg;
    Color hint;
    MDButtonColorSchema startButton;
    MDProgressColorSchema progress;
    MDListColorSchema list;

    public MDCenterColorSchema bg(MDBgColorSchema value) {
        this.bg = value;
        return this;
    }

    public MDCenterColorSchema hint(Color value) {
        this.hint = value;
        return this;
    }

    public MDCenterColorSchema startButton(MDButtonColorSchema value) {
        this.startButton = value;
        return this;
    }

    public MDCenterColorSchema progress(MDProgressColorSchema value) {
        this.progress = value;
        return this;
    }

    public MDCenterColorSchema list(MDListColorSchema value) {
        this.list = value;
        return this;
    }

    @Override
    public MDBgColorSchema bg() {
        return bg;
    }

    @Override
    public Color hint() {
        return hint;
    }

    @Override
    public MDButtonColorSchema startButton() {
        return startButton;
    }

    @Override
    public MDProgressColorSchema progress() {
        return progress;
    }

    @Override
    public MDListColorSchema list() {
        return list;
    }
}
