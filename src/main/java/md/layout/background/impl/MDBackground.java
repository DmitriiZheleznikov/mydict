package md.layout.background.impl;

import heli.htweener.fx.ext.HRectangle;
import javafx.scene.Group;
import javafx.scene.Scene;
import md.layout.ISceneAddable;
import md.layout.ISceneResizable;
import md.layout.background.IBackground;
import md.skin.colorschema.i.IMDColorSchema;

abstract public class MDBackground implements IBackground, ISceneAddable, ISceneResizable {
    protected HRectangle topRect;
    protected HRectangle centerRect;
    protected HRectangle bottomRect;
    protected Group group;
    protected Group groupTop;
    protected Group groupCenter;
    protected Group groupBottom;
    protected IMDColorSchema colorSchema;

    protected Scene scene;

    public MDBackground(IMDColorSchema colorSchema) {
        topRect = new HRectangle();
        centerRect = new HRectangle();
        bottomRect = new HRectangle();
        groupTop = new Group(topRect);
        groupCenter = new Group(centerRect);
        groupBottom = new Group(bottomRect);
        group = new Group(groupBottom, groupCenter, groupTop);
        this.colorSchema = colorSchema;
    }

    @Override
    public void addToScene(Scene scene) {
        this.scene = scene;
        ((Group)scene.getRoot()).getChildren().add(this.group);
        locate();
    }

    @Override
    public void resize(double newHeight, double newWidth) {
        locate();
    }

    abstract public void locate();

    public HRectangle getTopRect() {
        return topRect;
    }

    public HRectangle getCenterRect() {
        return centerRect;
    }

    public HRectangle getBottomRect() {
        return bottomRect;
    }

    public Group getGroup() {
        return group;
    }

    public Scene getScene() {
        return scene;
    }

    public Group getGroupTop() {
        return groupTop;
    }

    public Group getGroupCenter() {
        return groupCenter;
    }

    public Group getGroupBottom() {
        return groupBottom;
    }
}
