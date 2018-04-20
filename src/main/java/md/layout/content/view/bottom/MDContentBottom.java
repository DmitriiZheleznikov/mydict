package md.layout.content.view.bottom;

import heli.component.shape.list.centerlist.control.ICListEvent;
import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.Group;
import javafx.scene.Scene;
import md.layout.content.control.ISaveFileEvent;
import md.layout.content.view.AbstractMDContentBase;
import md.shape.MDButton;
import md.shape.MDText;
import md.skin.colorschema.i.IMDColorSchema;

import static md.layout.content.view.helper.visibility.VisibilityHelper.Type.BOTTOM;

public class MDContentBottom extends AbstractMDContentBase {
    protected MDButton bBack;
    protected MDButton bSaveState;
    protected MDButton bFinish;

    protected MDText tLength;

    public MDContentBottom(Group groupBottom, HRectangle rect, IMDColorSchema colorSchema) {
        super(groupBottom, rect, colorSchema);

        bBack = new MDButton("BACK", colorSchema.bottom().button().normal(),
                colorSchema.bottom().button().inactive(), colorSchema.bottom().button().hovered());
        bSaveState = new MDButton("SAVE STATE", colorSchema.bottom().button().normal(),
                colorSchema.bottom().button().inactive(), colorSchema.bottom().button().hovered());
        bFinish = new MDButton("FINISH", colorSchema.bottom().button().normal(),
                colorSchema.bottom().button().inactive(), colorSchema.bottom().button().hovered());
        tLength = new MDText("/", colorSchema.bottom().button().inactive());

        visibilityHelper().hideImmediately(bBack, bSaveState, bFinish, tLength);
    }

    public void setOnBackEvent(final ICListEvent action) {
        bBack.setOnMouseClicked(event -> {
            action.call();
        });
    }

    public void setOnSaveState(final ISaveFileEvent action) {
        bSaveState.setOnMouseClicked(event -> {
            saveStateWithAnimation(action);
        });
    }

    public void saveStateWithAnimation(final ISaveFileEvent action) {
        fileHelper().setOnSaveFileEvent(BOTTOM, bSaveState, action);
    }

    public void setOnFinish(final ISaveFileEvent action) {
        bFinish.setOnMouseClicked(event -> {
            fileHelper().setOnSaveFileEvent(BOTTOM, bFinish, action);
        });
    }

    @Override
    public void addToScene(Scene scene) {
        super.addToScene(scene);
        group.getChildren().addAll(bBack, bSaveState, bFinish, tLength);
    }

    @Override
    public void resize(double newHeight, double newWidth) {
        double sf = shapeHelper().getScaleFactor();
        bBack.resize(sf);
        bSaveState.resize(sf);
        bFinish.resize(sf);
        tLength.resize(sf);
        locate();
    }

    public void locate(ITweenableShape object) {
        shapeHelper().locateObject(object, bBack,
                () -> 10,
                () -> rect.getY() + (scene == null ? 0 : rect.getHeight() - bBack.getHeight() + 5));

        shapeHelper().locateObject(object, bSaveState,
                () -> (scene == null ? 0 : scene.getWidth() / 2 - bSaveState.getWidth() / 2),
                () -> rect.getY() + (scene == null ? 0 : rect.getHeight() - bSaveState.getHeight() + 5));

        shapeHelper().locateObject(object, bFinish,
                () -> (scene == null ? 0 : scene.getWidth()) - bFinish.getWidth() - 10,
                () -> rect.getY() + (scene == null ? 0 : rect.getHeight() - bFinish.getHeight() + 5));

        shapeHelper().locateObject(object, tLength,
                () -> (scene == null ? 0 : scene.getWidth()) - tLength.getWidth() - 10,
                () -> rect.getY() + (scene == null ? 0 : tLength.getHeight() + 5));
    }

    public void setLengthEnabled(int value) {
        int i = tLength.getText().indexOf("/");

        if (i > -1) {
            //System.out.println("setLengthEnabled(" + value + "): " + tLength.getText().substring(i));
            tLength.setText(value + tLength.getText().substring(i));
        } else {
            tLength.setText(value + "/");
        }
    }

    public void setLengthTotal(int value) {
        int i = tLength.getText().indexOf("/");

        if (i > -1) {
            //System.out.println("setLengthTotal(" + value + "): " + tLength.getText().substring(0, i+1));
            tLength.setText(tLength.getText().substring(0, i+1) + value);
        } else {
            tLength.setText("/" + value);
        }
    }

    public void setStateHidden() {
        bBack.setVisible(false);
        bSaveState.setVisible(false);
        bFinish.setVisible(false);
        tLength.setVisible(false);
    }

    public void setStateInitial() {
        visibilityHelper(BOTTOM).hide(bBack);
        visibilityHelper(BOTTOM).hide(bSaveState);
        visibilityHelper(BOTTOM).hide(bFinish);
        visibilityHelper(BOTTOM).hide(tLength);
    }

    public void setStateFileSelected() {
        setStateInitial();
    }

    public void setStateMFFileSelected() {
        setStateInitial();
    }

    public void setStateAnalysisInProgress() {
        setStateInitial();
    }

    public void setStateAnalysisCompleted() {
        visibilityHelper(BOTTOM).show(bBack);
        visibilityHelper(BOTTOM).show(bSaveState);
        visibilityHelper(BOTTOM).show(bFinish);
        visibilityHelper(BOTTOM).show(tLength);
        bBack.setInactive();
        bSaveState.setInactive();
        bFinish.setInactive();
    }

    public void setStateListProcessed() {
        setStateAnalysisCompleted();
        bFinish.setRegular();
    }
}
