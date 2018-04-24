package md.layout.content.control;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import md.layout.ISceneAddable;
import md.layout.ISceneResizable;
import md.layout.background.impl.MDBackground;
import md.layout.content.control.helper.ContentHelper;
import md.layout.content.view.bottom.MDContentBottom;
import md.layout.content.view.center.MDContentCenter;
import md.layout.content.view.top.MDContentTop;
import md.settings.Settings;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.shape.mdcenterlist.model.MDListModel;
import md.skin.colorschema.i.IMDColorSchema;
import md.textanalysis.MyDict;
import md.textanalysis.TextAnalyser;

import java.io.File;
import java.io.IOException;

import static md.layout.content.control.MDContentState.*;

public class MDContent implements ISceneResizable, ISceneAddable {
    private MDContentTop contentTop;
    private MDContentCenter contentCenter;
    private MDContentBottom contentBottom;
    private MDBackground bg;

    private File fileToAnalyse;
    private File fileMyDict;
    private MyDict myDict;

    private MDContentState state;
    /*
    1 - initial (after welcome), should be visible: "open file" (active)
    2 - file selected, should be visible: "open file" (inactive), "open md file" (active), "start" (active)
    3 - md file selected, should be visible: "open file" (inactive), "open md file" (inactive), "start" (active)
    4 - after analisys, should be visible: "open file" (inactive), "open md file" (inactive), all bottom (inactive) + effect
    5 - after analisys and all rows processed (user went to the end of effect),
        should be visible: "open file" (inactive), "open md file" (inactive), all bottom (inactive/active(finish)) + effect
    - - new file selected - state 3
    - - new md file selected - state 3

     */

    public MDContent(MDBackground bg, IMDColorSchema colorSchema) {
        this.bg = bg;
        contentTop = new MDContentTop(bg.getGroupTop(), bg.getTopRect(), colorSchema);
        contentCenter = new MDContentCenter(bg.getGroupCenter(), bg.getCenterRect(), colorSchema);
        contentBottom = new MDContentBottom(bg.getGroupBottom(), bg.getBottomRect(), colorSchema);
        prerequisiteFile();
        prerequisiteMDFile();

        contentTop.setOnLoadFileEvent(file -> {
            fileToAnalyse = file;
            if (fileMyDict == null) {
                setStateFileSelected();
            } else {
                setStateMFFileSelected();
            }
        });
        contentTop.setOnLoadMDFileEvent(file -> {
            fileMyDict = file;
            setStateMFFileSelected();
        });

        contentCenter.setOnStartEvent(() -> {
            setStateAnalysisInProgress();
            this.myDict = new MyDict(fileMyDict);
            Task<Void> task = new TextAnalyser(fileToAnalyse, myDict) {
                @Override
                protected void successAction(MDListLineModel mdListLineModel) {
                    contentCenter.finishProgressAnimation(() -> {
                        contentCenter.getList().setNewModel(mdListLineModel);
                        contentCenter.getList().operationGoTo(ContentHelper.checkSaveSate(fileToAnalyse));
                        contentCenter.getList().getView().setVisible(false);
                        setStateAnalysisCompleted();
                        myDict.runScheduler(() -> contentBottom.saveStateWithAnimation(MDContent.this::saveState));
                    });
                }
                @Override
                protected void failAction(Exception e) {
                    setStateFailed(e.getClass().getSimpleName()+ ": " + e.getMessage());
                }
            };
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();

            contentCenter.startProgressAnimation(() -> {
                contentCenter.setProgressText(Math.round(task.getProgress()*100) + "%");
            });
        });
        contentCenter.getList().setOnListDownCustom((prevLineModel, currLineModel, action, currentWindow) -> {
            if (state != LIST_PROCESSED && currentWindow.getCurrent() == (MDListModel.SIZE-2)) {
                setStateListProcessed();
            }
        });
        contentCenter.getList().setOnListDeleteCustom((prevLineModel, currLineModel, action, currentWindow) -> {
            myDict.add(prevLineModel.getText());
            if (state != LIST_PROCESSED && currentWindow.getCurrent() == (MDListModel.SIZE-2)) {
                setStateListProcessed();
            }
        });
        contentCenter.getList().setOnListRestoreCustom((prevLineModel, currLineModel, op, currentWindow) -> {
            myDict.remove(currLineModel.getText());
        });
        contentCenter.setOnListPercentCompleted(value -> contentTop.setProgressPercent(value));
        contentCenter.setOnListLengthTotal(value -> contentBottom.setLengthTotal(value));
        contentCenter.setOnListLengthEnabled(value -> contentBottom.setLengthEnabled(value));

        contentBottom.setOnBackEvent(() -> {
            contentCenter.getList().operationStepBack();
            return null;
        });
        contentBottom.setOnSaveState(this::saveState);
        contentBottom.setOnFinish(this::finish);
    }

    private void prerequisiteMDFile() {
        if (Settings.getMDFilePath().length() > 0) {
            if (fileMyDict == null) fileMyDict = new File(Settings.getMDFilePath());
            contentTop.prerequisiteMDFile(fileMyDict.getName());
        }
    }

    private void prerequisiteFile() {
        if (Settings.getFilePath().length() > 0) {
            if (fileToAnalyse == null) fileToAnalyse = new File(Settings.getFilePath());
            contentTop.prerequisiteFile(fileToAnalyse.getName());
        }
    }

    public void finish() throws IOException {
        ContentHelper.finish(fileToAnalyse, contentCenter.getList());
        saveDict();
        ContentHelper.removeState(fileToAnalyse);
        Settings.saveFPath("");
        myDict.stopScheduler();
    }

    public void saveState() throws IOException {
        if (!contentCenter.getList().isDirty()) return;

        contentCenter.getList().setDirty(false);
        ContentHelper.saveState(fileToAnalyse, contentCenter.getList());
        saveDict();
    }

    private void saveDict() throws IOException {
        if (myDict == null) {
            myDict = new MyDict(fileMyDict);
            myDict.init();
        }
        myDict.saveState();
    }

    public void setStateHidden() {
        contentTop.setStateHidden();
        contentCenter.setStateHidden();
        contentBottom.setStateHidden();
    }

    public void setStateFailed(String e) {
        state = FILE_SELECTED;
        contentTop.setStateFailed();
        contentCenter.setStateFailed(e);
        contentBottom.setStateFileSelected();
    }

    public void initState() {
        setStateInitial();
        if (fileToAnalyse != null && fileMyDict != null) setStateMFFileSelected();
        if (fileToAnalyse != null && fileMyDict == null) setStateFileSelected();
    }

    public void setStateInitial() {
        state = INITIAL;
        contentTop.setStateInitial();
        contentCenter.setStateInitial();
        contentBottom.setStateInitial();
        prerequisiteFile();
        prerequisiteMDFile();
    }

    public void setStateFileSelected() {
        state = FILE_SELECTED;
        contentTop.setStateFileSelected();
        contentCenter.setStateFileSelected();
        contentBottom.setStateFileSelected();
        if (myDict != null) myDict.stopScheduler();
        Settings.saveFPath(fileToAnalyse.getAbsolutePath());
    }

    public void setStateMFFileSelected() {
        state = MD_FILE_SELECTED;
        contentTop.setStateMFFileSelected();
        contentCenter.setStateMFFileSelected();
        contentBottom.setStateMFFileSelected();
        if (myDict != null) myDict.stopScheduler();
        Settings.saveFPath(fileToAnalyse.getAbsolutePath());
        Settings.saveMDPath(fileMyDict.getAbsolutePath());
    }

    public void setStateAnalysisInProgress() {
        state = ANALYSIS_IN_PROGRESS;
        contentTop.setStateAnalysisInProgress();
        contentCenter.setStateAnalysisInProgress();
        contentBottom.setStateAnalysisInProgress();
    }

    public void setStateAnalysisCompleted() {
        state = ANALYSIS_COMPLETED;
        contentTop.setStateAnalysisCompleted();
        contentCenter.setStateAnalysisCompleted();
        contentBottom.setStateAnalysisCompleted();
    }

    public void setStateListProcessed() {
        state = LIST_PROCESSED;
        contentTop.setStateListProcessed();
        contentCenter.setStateListProcessed();
        contentBottom.setStateListProcessed();
    }

    @Override
    public void addToScene(Scene scene) {
        contentTop.addToScene(scene);
        contentCenter.addToScene(scene);
        contentBottom.addToScene(scene);
        //setStateHidden();
    }

    @Override
    public void resize(double newHeight, double newWidth) {
        contentTop.resize(newHeight, newWidth);
        contentCenter.resize(newHeight, newWidth);
        contentBottom.resize(newHeight, newWidth);
    }
}
