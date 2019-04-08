package heli.component.shape.list.centerlist;

import heli.component.shape.list.centerlist.control.*;
import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.component.shape.list.centerlist.model.CListModel;
import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.list.centerlist.view.CListView;
import heli.component.shape.list.centerlist.view.effect.scroll.CListViewAnimation;
import heli.htweener.fx.ext.HText;
import javafx.scene.Scene;

/**
 * Control part of CList.<br>
 * It contains everything that is required for creation, control and drawing CList with basic features.
 */
public class CList {
    protected CListModel listModel;
    protected CListView listView;
    protected CListModelWindow currentWindow;
    protected OperationsStack opStack;

    protected ICListEvent onListUp;
    protected ICListCustomEvent onListUpCustom;

    protected ICListEvent onListDown;
    protected ICListCustomEvent onListDownCustom;

    protected ICListEvent onListDelete;
    protected ICListCustomEvent onListDeleteCustom;

    protected ICListCustomEvent onListRestoreCustom;

    protected ICListPercent onListPercentCompleted;
    protected ICListLength onListLengthEnabled;
    protected ICListLength onListLengthTotal;

    protected boolean isDirty = false;
    protected boolean isLocked = false;

    public CList(CListView listView) {
        this.listView = listView;
        opStack = new OperationsStack();
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    /** Dirty any of operation applied on list (next, prev, remove, restore). Become clear during "save" (should be implemented externally) */
    public boolean isDirty() {
        return isDirty;
    }

    protected void setupListEvents() {
        onListUp = () -> {
            isDirty = true;
            CListLineModel prevLineModel = listModel.getCurrentLine();
            CListModelOperation op = listModel.prev();
            if (op != null) currentWindow = getWindow(currentWindow);
            if (onListUpCustom != null) onListUpCustom.call(prevLineModel, listModel.getCurrentLine(), op, currentWindow);
            return op;
        };

        onListDown = () -> {
            isDirty = true;
            CListLineModel prevLineModel = listModel.getCurrentLine();
            CListModelOperation op = listModel.next();
            if (op != null) currentWindow = getWindow(currentWindow);
            if (onListDownCustom != null) onListDownCustom.call(prevLineModel, listModel.getCurrentLine(), op, currentWindow);
            return op;
        };

        onListDelete = () -> {
            isDirty = true;
            CListLineModel prevLineModel = listModel.getCurrentLine();
            CListModelOperation op = listModel.disableCurrent();
            if (op != null) currentWindow = getWindow(currentWindow);
            if (onListDeleteCustom != null) onListDeleteCustom.call(prevLineModel, listModel.getCurrentLine(), op, currentWindow);
            return op;
        };
    }

    public CListModel getModel() {
        return listModel;
    }

    public CListView getView() {
        return listView;
    }

    public void setNewModel(CListLineModel line) {
        setNewModel(new CListModel(line));
    }

    public void setNewModel(CListModel listModel) {
        this.listModel = listModel;
        currentWindow = listModel.getWindow();
        listView.locate(currentWindow);
        setupListEvents();
    }

    /** Action for changing "Percent completed" property */
    public void setOnListPercentCompleted(ICListPercent action) {
        this.onListPercentCompleted = action;
    }

    /** Action for changing "Number of enabled lines" property */
    public void setOnListLengthEnabled(ICListLength action) {
        this.onListLengthEnabled = action;
    }

    /** Action for changing "Total number of lines" property */
    public void setOnListLengthTotal(ICListLength action) {
        this.onListLengthTotal = action;
    }

    public void addToScene(Scene scene) {
        listView.addToScene(scene);
        setupKeyEvents(scene);
        setupMouseEvents(scene);
    }

    /** Lists up, both "model" and "view" with animation and all */
    public void operationListUp() {
        listAction(onListUp);
    }

    /** Lists down, both "model" and "view" with animation and all */
    public void operationListDown() {
        listAction(onListDown);
    }

    /** Remove/Disable current line, both "model" and "view" with animation and all */
    public void operationRemoveCurrent() {
        listAction(onListDelete);
    }

    public void operationShowEditDialog() {
        if (!listView.isLocked()) {
            listView.appearEditDialog();
        }
    }

    public void operationSaveEditedText() {
        String newText = listView.getEditDialog().getTfNewValue().getText();
        if (newText != null && newText.trim().length() == 0) {
            newText = null;
        }
        listModel.getCurrentLine().setEditedText(newText);
        listView.getCurrentLine().renewContent(listModel.getCurrentLine());
        listView.getCurrentLine().locate();
    }

    public void operationHideEditDialog() {
        listView.disappearEditDialog();
    }

    /** Performs opposite action taken from in-memory history, both "model" and "view" with animation and all */
    public void operationStepBack() {
        if (listModel.getCurrentLine() == null) return;

        if (!listView.isLocked()) {
            int prevWindowPos = currentWindow.getCurrent();
            CListModelOperation op = opStack.poll();
            if (op != null) {
                isDirty = true;
                CListLineModel prevLine = listModel.getCurrentLine();
                CListModelOperation reverseOp = op.getReverseOperation();
                reverseOp = listModel.applyOperation(reverseOp);
                currentWindow = getWindow(currentWindow);
                if (onListRestoreCustom != null && reverseOp.getAction() == CListModelOperation.Action.RESTORE) {
                    onListRestoreCustom.call(prevLine, listModel.getCurrentLine(), reverseOp, currentWindow);
                }
                applyAnimation(reverseOp, prevWindowPos);
                onTotals();
            }
        }
    }

    private void onTotals() {
        if (onListPercentCompleted != null) onListPercentCompleted.call(listModel.getProgressInPercent());
        if (onListLengthEnabled != null) onListLengthEnabled.call(listModel.getLength());
        if (onListLengthTotal != null) onListLengthTotal.call(listModel.getLengthTotal());
    }

    /** Lists to particular line if possible, both "model" and "view" with animation and all */
    public void operationGoTo(String text) {
        getModel().goTo(text);
        currentWindow = getWindow(currentWindow);
        locate();
        onTotals();
    }

    private void listAction(ICListEvent event) {
        if (listView.isLocked()) return;
        if (listModel.getCurrentLine() == null) return;

        int prevWindowPos = currentWindow.getCurrent();
        CListModelOperation op = event.call();
        CListViewAnimation.Animation animation = applyAnimation(op, prevWindowPos);
        opStack.push(op, animation);
        onTotals();
    }

    private CListViewAnimation.Animation applyAnimation(CListModelOperation op,  int prevWindowPos) {
        CListViewAnimation.Animation animation = CListViewAnimation.calcAnimation(op, prevWindowPos, currentWindow.getCurrent());
        listView.applyAnimation(op, animation, currentWindow);
        return animation;
    }

    protected void setupKeyEvents(Scene scene) {
        scene.setOnKeyPressed(ke -> {
            switch (ke.getCode()) {
                case UP: {
                    operationListUp();
                    break;
                }
                case DOWN: {
                    operationListDown();
                    break;
                }
            }
        });
        scene.setOnKeyReleased(ke -> {
            switch (ke.getCode()) {
                case RIGHT:
                case LEFT: {
                    operationRemoveCurrent();
                    break;
                }
            }
        });
    }

    protected void setupMouseEvents(Scene scene) {
        for (int i = 1; i < listView.lines().length - 1; i++) {
            final CListLineView line = listView.lines()[i];
            line.getbDelete().setOnMouseClicked(event -> {
                if (event.getSource() instanceof HText && ((HText)event.getSource()).isLocked()) return;
                operationRemoveCurrent();
            });
            line.getbEdit().setOnMouseClicked(event -> {
                if (event.getSource() instanceof HText && ((HText)event.getSource()).isLocked()) return;
                operationShowEditDialog();
            });
        }
        listView.getGroup().setOnMouseClicked(event -> {
            operationList(event.getX(), event.getY());
        });
        listView.getEditDialog().setOnCancel(() -> {
            operationHideEditDialog();
        });
        listView.getEditDialog().setOnSave(() -> {
            operationHideEditDialog();
            operationSaveEditedText();
        });
    }

    protected void operationList(double x, double y) {
        if (y < listView.getCurrentLine().getY()) operationListUp();
        if (y > (listView.getCurrentLine().getY() + listView.getCurrentLine().getHeight())) operationListDown();
    }

    public CListModelWindow getCurrentWindow() {
        if (currentWindow == null && listModel != null) {
            currentWindow = listModel.getWindow();
        }
        return currentWindow;
    }

    public CListModelWindow getWindow(CListModelWindow currentWindow) {
        if (listModel != null) {
            return listModel.getWindow(currentWindow);
        }
        return null;
    }

    /** Calls after "model" update, but before "view" */
    public void setOnListUpCustom(ICListCustomEvent onListUpCustom) {
        this.onListUpCustom = onListUpCustom;
    }

    /** Calls after "model" update, but before "view" */
    public void setOnListDownCustom(ICListCustomEvent onListDownCustom) {
        this.onListDownCustom = onListDownCustom;
    }

    /** Calls after "model" update, but before "view" */
    public void setOnListDeleteCustom(ICListCustomEvent onListDeleteCustom) {
        this.onListDeleteCustom = onListDeleteCustom;
    }

    /** Calls after "model" update, but before "view" */
    public void setOnListRestoreCustom(ICListCustomEvent onListRestoreCustom) {
        this.onListRestoreCustom = onListRestoreCustom;
    }

    //----------------------

    public void locate() {
        getView().locate(getCurrentWindow());
    }

    public void locateIfNotAnimated() {
        if (!getView().isAnimated()) {
            locate();
        }
    }
}
