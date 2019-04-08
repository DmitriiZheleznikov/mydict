package md.layout.content.view.center;

import heli.component.shape.list.centerlist.control.ICListCustomEvent;
import heli.component.shape.list.centerlist.control.ICListLength;
import heli.component.shape.list.centerlist.control.ICListPercent;
import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tween.ICallable;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.Group;
import javafx.scene.Scene;
import md.layout.content.view.AbstractMDContentBase;
import md.shape.MDButton;
import md.shape.MDProgressText;
import md.shape.MDText;
import md.shape.mdcenterlist.MDList;
import md.skin.colorschema.i.IMDColorSchema;

import static md.layout.content.view.helper.visibility.VisibilityHelper.Type.TOP;
import static md.layout.content.view.helper.visibility.VisibilityHelper.Type.CENTER;

public class MDContentCenter extends AbstractMDContentBase {
    private String HINT_TEXT = "\"My Dictionary\" file will created automatically";

    private MDText hint;
    private MDButton bStart;
    private MDProgressText progressText;
    private MDList list;

    public MDContentCenter(Group group, HRectangle rect, IMDColorSchema colorSchema) {
        super(group, rect, colorSchema);
        hint = new MDText(HINT_TEXT, colorSchema.center().hint());
        bStart = new MDButton("START", colorSchema.center().startButton().normal(),
                colorSchema.center().startButton().inactive(), colorSchema.center().startButton().hovered(), 30);
        bStart.setRegular();
        progressText = new MDProgressText("0%", colorSchema.center().progress().min(),
                colorSchema.center().progress().max(), colorSchema.center().progress().fin(), 30);
        visibilityHelper().hideImmediately(progressText, bStart, hint);
        list = new MDList(this, null, group, rect, colorSchema.center().list());
    }

    public MDList getList() {
        return list;
    }

    public void setOnStartEvent(ICallable onStart) {
        bStart.setOnMouseClicked(event -> {
            if (onStart != null) onStart.call();
        });
    }

    public void startProgressAnimation(ICallable onUpdate) {
        progressText.run(onUpdate);
    }

    public void setProgressText(String text) {
        progressText.setText(text);
        locate(progressText);
    }

    public void finishProgressAnimation(ICallable onComplete) {
        progressText.stop(onComplete);
    }

    public void setOnListUpEvent(ICListCustomEvent onListUp) {
        list.setOnListUpCustom(onListUp);
    }

    public void setOnListDownEvent(ICListCustomEvent onListDown) {
        list.setOnListDownCustom(onListDown);
    }

    public void setOnListDeleteEvent(ICListCustomEvent onListDelete) {
        list.setOnListDeleteCustom(onListDelete);
    }

    public void setOnListPercentCompleted(ICListPercent action) {
        list.setOnListPercentCompleted(action);
    }

    public void setOnListLengthEnabled(ICListLength action) {
        list.setOnListLengthEnabled(action);
    }

    public void setOnListLengthTotal(ICListLength action) {
        list.setOnListLengthTotal(action);
    }

    @Override
    public void addToScene(Scene scene) {
        super.addToScene(scene);
        group.getChildren().addAll(hint, bStart, progressText);
        list.addToScene(scene);
    }

    @Override
    public void locate(ITweenableShape object) {
        shapeHelper().locateObject(object, hint,
                () -> (scene == null ? 0 : scene.getWidth()) - hint.getWidth() - 10,
                () -> rect.getY() + hint.getHeight() + 5);

        shapeHelper().locateObject(object, bStart,
                () -> (scene == null ? 0 : scene.getWidth()) / 2 - bStart.getWidth() / 2,
                () -> (scene == null ? 0 : scene.getHeight()) / 2 - bStart.getHeight() / 2);

        shapeHelper().locateObject(object, progressText,
                () -> (scene == null ? 0 : scene.getWidth()) / 2 - progressText.getWidth() / 2,
                () -> (scene == null ? 0 : scene.getHeight()) / 2 - progressText.getHeight() / 2);

        if (scene != null) {
            list.locateIfNotAnimated();
        }
    }

    @Override
    public void resize(double newHeight, double newWidth) {
        double sf = shapeHelper().getScaleFactor();
        hint.resize(sf);
        bStart.resize(sf);
        progressText.resize(sf);
        list.getView().resize(sf);
        locate();
    }

    public void setStateHidden() {
        hint.setVisible(false);
        bStart.setVisible(false);
        progressText.setVisible(false);
        list.getView().setVisible(false);
    }

    public void setStateFailed(String e) {
        progressText.stop(() -> {
            visibilityHelper(CENTER).hide(progressText, () -> progressText.setText("0%"));
            hint.setText("Error: " + e);
            hint.setVisible(false);
            visibilityHelper(TOP).show(hint);
            list.getView().disappear(() -> visibilityHelper(CENTER).hide(bStart, () -> bStart.setText("START")));
        });
    }

    public void setStateInitial() {
        visibilityHelper(CENTER).hide(progressText, () -> progressText.setText("0%"));
        visibilityHelper(TOP).hide(hint);
        list.getView().disappear(() -> visibilityHelper(CENTER).hide(bStart, () -> bStart.setText("START")));
    }

    public void setStateFileSelected() {
        visibilityHelper(CENTER).hide(progressText);
        hint.setText(HINT_TEXT);
        if (hint.isVisible()) visibilityHelper(TOP).hide(hint, (() -> visibilityHelper(TOP).show(hint)));
        else visibilityHelper(TOP).show(hint);
        list.getView().disappear(() -> visibilityHelper(CENTER).show(bStart));
    }

    public void setStateMFFileSelected() {
        visibilityHelper(CENTER).hide(progressText);
        visibilityHelper(TOP).hide(hint);
        list.getView().disappear(() -> visibilityHelper(CENTER).show(bStart));
    }

    public void setStateAnalysisInProgress() {
        visibilityHelper(TOP).hide(hint);
        visibilityHelper(CENTER).hide(bStart, () -> {
            visibilityHelper(CENTER).show(progressText);
        });
    }

    public void setStateAnalysisCompleted() {
        visibilityHelper(TOP).hide(hint);
        visibilityHelper(CENTER).hide(bStart);
        visibilityHelper(CENTER).hide(progressText, () -> {
            list.getView().appear(list.getCurrentWindow());
        });
    }

    public void setStateListProcessed() {
        visibilityHelper(CENTER).hide(progressText);
        visibilityHelper(TOP).hide(hint);
        visibilityHelper(CENTER).hide(bStart);
    }
}
