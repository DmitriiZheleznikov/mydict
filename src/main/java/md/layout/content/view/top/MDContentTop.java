package md.layout.content.view.top;

import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.Group;
import javafx.scene.Scene;
import md.layout.content.control.ILoadFileEvent;
import md.layout.content.view.AbstractMDContentBase;
import md.shape.MDButton;
import md.shape.MDText;
import md.skin.colorschema.i.IMDColorSchema;
import md.textanalysis.ctrl.MyDict;

import static md.layout.content.view.helper.visibility.VisibilityHelper.Type.TOP;

public class MDContentTop extends AbstractMDContentBase {
    public static final String LABEL_CHOOSE_MD_FILE = "CHOOSE \"MY DICT\" FILE";
    public static final String LABEL_CHOOSE_FILE = "CHOOSE FILE";

    protected MDButton bOpenFile;
    protected MDButton bOpenMDFile;
    protected MDText tPercent;

//    private static final Color COLOR_BTN = Color.color(0.2863, 0.451, 0.2706);
//    private static final Color COLOR_BTN_INACTIVE = Color.color(0.5608, 0.6824, 0.4706);
//    private static final Color COLOR_BTN_ACTIVE = Color.color(0.2588, 0.3255, 0.2353);

    public MDContentTop(Group groupTop, HRectangle rect, IMDColorSchema colorSchema) {
        super(groupTop, rect, colorSchema);
        bOpenFile = new MDButton("CHOOSE FILE", colorSchema.top().button().normal(),
                colorSchema.top().button().inactive(), colorSchema.top().button().hovered());
        bOpenMDFile = new MDButton("CHOOSE \"MY DICT\" FILE", colorSchema.top().button().normal(),
                colorSchema.top().button().inactive(), colorSchema.top().button().hovered());
        tPercent = new MDText("%", colorSchema.top().button().inactive());
        visibilityHelper().hideImmediately(bOpenFile, bOpenMDFile, tPercent);
    }

    public void setOnLoadFileEvent(ILoadFileEvent onLoadFileEvent) {
        fileHelper().setOnLoadFileEvent(TOP, bOpenFile, onLoadFileEvent);
    }

    public void setOnLoadMDFileEvent(ILoadFileEvent onLoadFileEvent) {
        fileHelper().setOnLoadFileEvent(TOP, bOpenMDFile, onLoadFileEvent);
    }

    public void prerequisiteMDFile(String name) {
        bOpenMDFile.setText(name);
    }

    public void prerequisiteFile(String name) {
        bOpenFile.setText(name);
    }

    @Override
    public void addToScene(Scene scene) {
        super.addToScene(scene);
        group.getChildren().addAll(bOpenFile, bOpenMDFile, tPercent);
    }

    @Override
    public void resize(double newHeight, double newWidth) {
        double sf = shapeHelper().getScaleFactor();
        bOpenFile.resize(sf);
        bOpenMDFile.resize(sf);
        tPercent.resize(sf);
        locate();
    }

    public void locate(ITweenableShape object) {
//        System.out.println("CT.locate begin");
        shapeHelper().locateObject(object, bOpenFile,
                () -> 10,
                () -> bOpenFile.getHeight() + 5);

        shapeHelper().locateObject(object, bOpenMDFile,
                () -> (scene == null ? 0 : scene.getWidth()) - bOpenMDFile.getWidth() - 10,
                () -> bOpenMDFile.getHeight() + 5);

        shapeHelper().locateObject(object, tPercent,
                () -> (scene == null ? 0 : scene.getWidth()) - tPercent.getWidth() - 10,
                () -> rect.getHeight() - tPercent.getHeight()/2);
    }

    public void setProgressPercent(double value) {
        tPercent.setText(((int)value) + "%");
    }

    public void setStateHidden() {
        bOpenFile.setVisible(false);
        bOpenMDFile.setVisible(false);
        tPercent.setVisible(false);
    }

    public void setStateInitial() {
        visibilityHelper(TOP).show(bOpenFile);
        visibilityHelper(TOP).hide(bOpenMDFile);
        visibilityHelper(TOP).hide(tPercent);
        bOpenFile.setText(LABEL_CHOOSE_FILE);
        bOpenFile.setRegular();
        bOpenMDFile.setText(LABEL_CHOOSE_MD_FILE);
    }

    public void setStateFailed() {
        bOpenFile.setVisible(false);
        bOpenMDFile.setVisible(false);
        tPercent.setVisible(false);
        setStateFileSelected();
    }

    public void setStateFileSelected() {
        visibilityHelper(TOP).show(bOpenFile);
        visibilityHelper(TOP).show(bOpenMDFile);
        visibilityHelper(TOP).hide(tPercent);
        bOpenFile.setRegular();
        bOpenMDFile.setRegular();
    }

    public void setStateMFFileSelected() {
        visibilityHelper(TOP).show(bOpenFile);
        visibilityHelper(TOP).show(bOpenMDFile);
        visibilityHelper(TOP).hide(tPercent);
        bOpenFile.setRegular();
        bOpenMDFile.setRegular();
    }

    public void setStateAnalysisInProgress() {
        visibilityHelper(TOP).hide(bOpenFile);
        visibilityHelper(TOP).hide(bOpenMDFile);
        visibilityHelper(TOP).hide(tPercent);
    }

    public void setStateAnalysisCompleted() {
        if (LABEL_CHOOSE_MD_FILE.equals(bOpenMDFile.getText())) bOpenMDFile.setText(MyDict.DEFAULT_FILE_NAME);
        setStateMFFileSelected();
        bOpenFile.setInactive();
        bOpenMDFile.setInactive();
        visibilityHelper(TOP).show(tPercent);
    }

    public void setStateListProcessed() {
        setStateAnalysisCompleted();
    }
}
