package md.shape.mdcenterlist;

import heli.component.shape.list.centerlist.CList;
import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.htweener.fx.ext.HRectangle;
import javafx.scene.Group;
import md.layout.content.view.center.MDContentCenter;
import md.shape.mdcenterlist.adj.IMDListColorSchema;
import md.shape.mdcenterlist.model.MDListModel;
import md.shape.mdcenterlist.view.MDListView;

public class MDList extends CList {
    public MDList(MDContentCenter parentContent, CListLineModel line, Group group, HRectangle rect, IMDListColorSchema colorSchema) {
        super(new MDListView(parentContent, group, rect, colorSchema));
        setNewModel(new MDListModel(line));
    }
}
