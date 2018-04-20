package md.shape.mdcenterlist.view;

import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.fx.ext.HRectangle;
import javafx.scene.Group;
import md.layout.ISceneAddable;
import md.layout.content.view.center.MDContentCenter;
import md.shape.mdcenterlist.adj.IMDListColorSchema;

public class MDListView extends CListView implements ISceneAddable {
    private MDContentCenter parentContent;

    public MDListView(MDContentCenter parentContent, Group group, HRectangle rect, IMDListColorSchema colorSchema) {
        super(group, rect, colorSchema);
        this.parentContent = parentContent;
    }

    protected CListLineView createNewLineView() {
        return new MDListLineView(this);
    }

    public MDContentCenter getParentContent() {
        return parentContent;
    }

    public IMDListColorSchema getColorSchema() {
        return (IMDListColorSchema)super.getColorSchema();
    }
}
