package md.skin.colorschema.i;

import md.skin.colorschema.i.bg.IMDBgColorSchema;
import md.skin.colorschema.i.bottom.IMDBottomColorSchema;
import md.skin.colorschema.i.center.IMDCenterColorSchema;
import md.skin.colorschema.i.top.IMDTopColorSchema;

public interface IMDColorSchema {
//    IMDBgColorSchema bg();
    IMDTopColorSchema top();
    IMDCenterColorSchema center();
    IMDBottomColorSchema bottom();
}
