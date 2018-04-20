package md.layout.content.view.helper.visibility;

import heli.htweener.tweenable.ITweenableShape;
import md.layout.content.view.AbstractMDContentBase;
import md.layout.content.view.helper.visibility.impl.BottomVisibilityHelper;
import md.layout.content.view.helper.visibility.impl.CenterVisibilityHelper;
import md.layout.content.view.helper.visibility.impl.TopVisibilityHelper;

public class VisibilityHelper {
    public enum Type {TOP, CENTER, BOTTOM}

    private AbstractMDContentBase content;
    private TopVisibilityHelper topVisibilityHelper;
    private CenterVisibilityHelper centerVisibilityHelper;
    private BottomVisibilityHelper bottomVisibilityHelper;

    public VisibilityHelper(AbstractMDContentBase content) {
        this.content = content;
        topVisibilityHelper = new TopVisibilityHelper(content);
        centerVisibilityHelper = new CenterVisibilityHelper(content);
        bottomVisibilityHelper = new BottomVisibilityHelper(content);
    }

    public void hideImmediately(ITweenableShape... objects) {
        for (ITweenableShape object : objects) {
            hideImmediately(object);
        }
    }

    public void hideImmediately(ITweenableShape object) {
        object.setVisible(false);
        object.setOpacity(0);
    }

    public ISpecificVisibilityHelper getSpecificHelper(Type type) {
        switch (type) {
            case TOP: return topVisibilityHelper;
            case CENTER: return centerVisibilityHelper;
            case BOTTOM: return bottomVisibilityHelper;
        }
        return null;
    }

}
