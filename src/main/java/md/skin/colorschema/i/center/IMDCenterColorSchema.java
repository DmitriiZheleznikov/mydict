package md.skin.colorschema.i.center;

import javafx.scene.paint.Color;
import md.shape.mdcenterlist.adj.IMDListColorSchema;
import md.skin.colorschema.i.bg.IMDBgColorSchema;
import md.skin.colorschema.i.part.IMDButtonColorSchema;
import md.skin.colorschema.i.part.IMDProgressColorSchema;

public interface IMDCenterColorSchema {
    IMDBgColorSchema bg();
    Color hint();
    IMDButtonColorSchema startButton();
    IMDProgressColorSchema progress();
    IMDListColorSchema list();
}
