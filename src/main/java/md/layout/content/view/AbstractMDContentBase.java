package md.layout.content.view;

import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.Group;
import javafx.scene.Scene;
import md.layout.ISceneAddable;
import md.layout.ISceneResizable;
import md.layout.content.view.helper.FileHelper;
import md.layout.content.view.helper.ShapeHelper;
import md.layout.content.view.helper.visibility.ISpecificVisibilityHelper;
import md.layout.content.view.helper.visibility.VisibilityHelper;
import md.skin.colorschema.i.IMDColorSchema;

abstract public class AbstractMDContentBase implements ISceneResizable, ISceneAddable {
    protected Scene scene;
    protected Group group;
    protected HRectangle rect;
    protected IMDColorSchema colorSchema;

    private VisibilityHelper visibility;
    private ShapeHelper shape;
    private FileHelper fileHelper;

    public AbstractMDContentBase(Group group, HRectangle rect, IMDColorSchema colorSchema) {
        this.group = group;
        this.rect = rect;
        this.colorSchema = colorSchema;
        visibility = new VisibilityHelper(this);
        shape = new ShapeHelper(this);
        fileHelper = new FileHelper(this);
    }

    public void locate() {
        locate(null);
    }

    abstract public void locate(ITweenableShape object);

    @Override
    public void addToScene(Scene scene) {
        this.scene = scene;
        shape.initSceneInitialSize();
        locate();
    }

    public Scene getScene() {
        return scene;
    }

    public ISpecificVisibilityHelper visibilityHelper(VisibilityHelper.Type type) {
        return visibilityHelper().getSpecificHelper(type);
    }

    public VisibilityHelper visibilityHelper() {
        return visibility;
    }

    public ShapeHelper shapeHelper() {
        return shape;
    }

    public FileHelper fileHelper() {
        return fileHelper;
    }
}
