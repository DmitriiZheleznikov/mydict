package heli.component.shape.list.centerlist.view;

import heli.component.shape.list.centerlist.ext.CButton;
import heli.component.shape.list.centerlist.ext.CText;
import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.htweener.fx.ext.HRectangle;
import heli.htweener.fx.ext.HText;
import heli.htweener.fx.ext.effect.HDropShadow;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import static heli.component.shape.list.centerlist.adj.CListFontConstants.*;

/**
 * Represents single line of "view" part of CList. It contains some basics like background, delete button and text
 */
public class CListLineView implements ITweenableShape {
    public enum Status { ACTIVE, INACTIVE, INVISIBLE }

    public static final double SF_ACTIVE_BLOCK_HIGH = 2.5;

    protected int modelLineNum;
    protected CListView parent;
    protected CListLineView.Status status;

    protected HRectangle bg;
    protected HDropShadow dropShadow;
    protected HRectangle bgClip;

    protected CText tText;
    protected HRectangle tTextClip;

    protected CText tOldText;
    protected HRectangle tOldTextClip;

    protected CButton bDelete;
    protected HRectangle bDeleteClip;

    protected CButton bEdit;
    protected HRectangle bEditClip;

    protected CListLineViewFuturePosition futurePosition;

    public CListLineView(CListView parent) {
        this.futurePosition = new CListLineViewFuturePosition();
        this.parent = parent;
        this.bg = new HRectangle();
        this.dropShadow = new HDropShadow();
        this.dropShadow.setColor(parent.getColorSchema().bgShadow());
        this.bg.setEffect(dropShadow);
        this.bgClip = new HRectangle();
        this.tTextClip = new HRectangle();
        this.tOldTextClip = new HRectangle();
        this.bDeleteClip = new HRectangle();
        this.bEditClip = new HRectangle();
        this.tText = new CText("", parent.getColorSchema().text(), FONT_UBUTNU_R_URL, FONT_UBUTNU_R_SIZE_DEFAULT);
        this.tOldText = new CText("", parent.getColorSchema().oldText(), FONT_UBUTNU_R_URL, FONT_UBUTNU_R_SIZE_DEFAULT-2);
        tOldText.setStrikethrough(true);
        this.bDelete = new CButton("X", parent.getColorSchema().buttonDeleteNormal(),
                parent.getColorSchema().buttonDeleteInactive(),
                parent.getColorSchema().buttonDeleteHovered(), FONT_ROBOTO_B_SIZE_DEFAULT - 4);
        this.bEdit = new CButton("E", parent.getColorSchema().buttonEditNormal(),
                parent.getColorSchema().buttonEditInactive(),
                parent.getColorSchema().buttonEditHovered(), FONT_ROBOTO_B_SIZE_DEFAULT - 4);
        tText.setColor(parent.getColorSchema().text());
        tOldText.setColor(parent.getColorSchema().oldText());
        bDelete.setColor(parent.getColorSchema().buttonDeleteNormal());
        bEdit.setColor(parent.getColorSchema().buttonEditNormal());
        bg.setStroke(getStrokeColor());
        bg.setFill(parent.getColorSchema().bg());

        hide();
    }

    public void show() {
        showImmediately(bg, tText, tOldText, bDelete, bEdit);
    }

    public void hide() {
        hideImmediately(bg, tText, tOldText, bDelete, bEdit);
    }

    protected void showImmediately(ITweenableShape... objects) {
        for (ITweenableShape object : objects) {
            showImmediately(object);
        }
    }

    public CButton getbDelete() {
        return bDelete;
    }

    public CButton getbEdit() {
        return bEdit;
    }

    public CListLineView.Status getStatus() {
        return status;
    }

    public HRectangle getBg() {
        return bg;
    }

    public HText gettText() {
        return tText;
    }

    public CText gettOldText() {
        return tOldText;
    }

    protected void showImmediately(ITweenableShape object) {
        object.setVisible(true);
    }

    protected void hideImmediately(ITweenableShape... objects) {
        for (ITweenableShape object : objects) {
            hideImmediately(object);
        }
    }

    protected void hideImmediately(ITweenableShape object) {
        if (object != null) {
            object.setVisible(false);
            object.setOpacity(0);
        }
    }

    public double calcFuturePosition(int lineNum, double x, double y, double distance) {
        futurePosition.setStatus(getStatusByDistance(distance));
        futurePosition.setWidth(parent.getBlockWidth());
        futurePosition.setHeight(futurePosition.getStatus() == CListLineView.Status.ACTIVE ? SF_ACTIVE_BLOCK_HIGH*parent.getBlockHight() : parent.getBlockHight());
        futurePosition.setX(x);
        futurePosition.setY(y);
        futurePosition.setOpacity(1 - distance);
        futurePosition.setCoreOpacity(futurePosition.getStatus() == CListLineView.Status.ACTIVE ? 1 : 0);
        futurePosition.setModelLineNumber(lineNum);

        return futurePosition.getHeight();
    }

    public double locate() {
        status = futurePosition.getStatus();
        clip();
        setWidth(futurePosition.getWidth());
        setHeight(futurePosition.getHeight());
        setX(futurePosition.getX());
        setY(futurePosition.getY());
        setOpacity(futurePosition.getOpacity());
        setCoreOpacity(futurePosition.getCoreOpacity());
        modelLineNum = futurePosition.getModelLineNumber();

        if (CListLineView.Status.INVISIBLE == status) clip();

        return bg.getHeight();
    }

    public double locate(int lineNum, double x, double y, double distance) {
        calcFuturePosition(lineNum, x, y, distance);
        return locate();
    }

    protected void locateBy(CListLineViewFuturePosition row) {
        futurePosition.setStatus(row.getStatus());
        futurePosition.setWidth(row.getWidth());
        futurePosition.setHeight(row.getHeight());
        futurePosition.setX(row.getX());
        futurePosition.setY(row.getY());
        futurePosition.setOpacity(row.getOpacity());
        futurePosition.setCoreOpacity(row.getCoreOpacity());
        futurePosition.setModelLineNumber(-1);
        locate();

        //if (CListLineView.Status.INVISIBLE == status) clip();
    }

    protected void locateBy(CListLineView row) {
        futurePosition.setStatus(row.getStatus());
        futurePosition.setWidth(row.getWidth());
        futurePosition.setHeight(row.getHeight());
        futurePosition.setX(row.getX());
        futurePosition.setY(row.getY());
        futurePosition.setOpacity(row.getOpacity());
        futurePosition.setCoreOpacity(row.getCoreOpacity());
        futurePosition.setModelLineNumber(-1);
        locate();

        //if (CListLineView.Status.INVISIBLE == status) clip();
    }

    public void renewContent(CListLineModel lineModel) {
        if (lineModel != null && lineModel.getEditedText() != null && lineModel.getEditedText().trim().length() > 0) {
            tText.setText(lineModel.getEditedText());
            tOldText.setText(lineModel.getText());
        } else {
            tText.setText(lineModel == null ? "" : lineModel.getText());
            tOldText.setText("");
        }
    }

    public void renewContentBy(CListLineView row) {
        tText.setText(row.gettText().getText());
        tOldText.setText(row.gettOldText().getText());
    }

    public void resize(double sf) {
        dropShadow.setRadius(3f * sf);
        dropShadow.setOffsetX(2f * sf);
        dropShadow.setOffsetY(2f * sf);
        bg.setStrokeWidth(Math.round(2f * sf - 0.49));

        tText.resize(sf);
        tOldText.resize(sf);
        bDelete.resize(sf);
        bEdit.resize(sf);
    }

    public void addToScene(Scene scene) {
        parent.getGroup().getChildren().addAll(bg, tText, tOldText, bDelete/*, bEdit*/);
    }

    public void clip() {
        clipSize(bgClip);
        clipSize(tTextClip);
        clipSize(tOldTextClip);
        clipSize(bDeleteClip);
        clipSize(bEditClip);
        bg.setClip(bgClip);
        tText.setClip(tTextClip);
        tOldText.setClip(tOldTextClip);
        bDelete.setClip(bDeleteClip);
        bEdit.setClip(bEditClip);
    }

    protected void clipSize(HRectangle rc) {
        rc.setX(parent.getBgRect().getX());
        rc.setY(parent.getBgRect().getY());
        rc.setWidth(parent.getBgRect().getWidth());
        rc.setHeight(parent.getBgRect().getHeight());
    }

    protected Color getStrokeColor() {
        if (CListLineView.Status.ACTIVE == status) return parent.getColorSchema().bgStroke();
        if (CListLineView.Status.INVISIBLE == status) return parent.getColorSchema().bgStrokeInactive();
        return parent.getColorSchema().bgStrokeInactive();
    }

    protected CListLineView.Status getStatusByDistance(double distance) {
        if (distance <= 0f) return CListLineView.Status.ACTIVE;
        if (distance >= 1f) return CListLineView.Status.INVISIBLE;
        return CListLineView.Status.INACTIVE;
    }

    public boolean isActive() {
        return CListLineView.Status.ACTIVE == status;
    }

    public void unsetClip() {
        tText.setClip(null);
        tOldText.setClip(null);
        bg.setClip(null);
        bDelete.setClip(null);
        bEdit.setClip(null);
    }

    public void setClip() {
        tText.setClip(tTextClip);
        tOldText.setClip(tOldTextClip);
        bg.setClip(bgClip);
        bDelete.setClip(bDeleteClip);
        bEdit.setClip(bEditClip);
    }

    public CListLineViewFuturePosition getFuturePosition() {
        return futurePosition;
    }

    public int getModelLineNum() {
        return modelLineNum;
    }

    //----- ITweenableShape -----

    @Override
    public double getX() {
        return bg.getX();
    }

    @Override
    public void setX(double x) {
        unsetClip();
        setXInner(x);
        setClip();
    }

    protected void setXInner(double x) {
        bg.setX(x);
        tText.setX(x + (parent.getBlockWidth() - tText.getWidth()) / 2);
        tOldText.setX(x + (parent.getBlockWidth() - tOldText.getWidth()) / 2);
        bDelete.setX(x + parent.getBlockWidth() - parent.getSizeBetween()*2 - bDelete.getWidth());
        bEdit.setX(bDelete.getX() - bEdit.getWidth()*1.5);
    }

    @Override
    public double getY() {
        return bg.getY();
    }

    @Override
    public void setY(double y) {
        unsetClip();
        setYInner(y);
        setClip();
    }

    protected void setYInner(double y) {
        bg.setY(y);
        tText.setY(y + tText.getHeight() * 1.5);
        tOldText.setY(tText.getY() + tText.getHeight());// + tOldText.getHeight()/2);
        bDelete.setY(y + tText.getHeight() * 1.5);
        bEdit.setY(y + tText.getHeight() * 1.5);
    }

    @Override
    public double getOpacity() {
        return bg.getOpacity();
    }

    @Override
    public void setOpacity(double alpha) {
        if (alpha < 0) alpha = 0;
        bg.setOpacity(alpha);
        tText.setOpacity(alpha);
        tOldText.setOpacity(alpha);
        if (alpha > 0f) {
            bg.setVisible(true);
            tText.setVisible(true);
            tOldText.setVisible(true);
        }
    }

    public double getCoreOpacity() {
        return 0;
    }

    public void setCoreOpacity(double alpha) {
        bDelete.setOpacity(alpha);
        bEdit.setOpacity(alpha);
        if (alpha > 0f) {
            bDelete.setVisible(true);
            bDelete.setLocked(false);
            bEdit.setVisible(true);
            bEdit.setLocked(false);
        } else {
            bDelete.setVisible(false);
            bDelete.setLocked(true);
            bEdit.setVisible(false);
            bEdit.setLocked(true);
        }
    }

    @Override
    public double getRotate() {
        return bg.getRotate();
    }

    @Override
    public void setRotate(double rotation) {
        bg.setRotate(rotation);
    }

    @Override
    public double getScaleX() {
        return 1;
    }

    @Override
    public void setScaleX(double scaleX) {
        resize(scaleX);
    }

    @Override
    public double getScaleY() {
        return 1;
    }

    @Override
    public void setScaleY(double scaleY) {
        resize(scaleY);
    }

    @Override
    public boolean isVisible() {
        return bg.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        bg.setVisible(visible);
        tText.setVisible(visible);
        tOldText.setVisible(visible);
        bDelete.setVisible(visible);
        bEdit.setVisible(visible);
    }

    @Override
    public double getWidth() {
        return bg.getWidth();
    }

    @Override
    public void setWidth(double value) {
        unsetClip();
        setWidthInner(value);
        setClip();
    }

    protected void setWidthInner(double value) {
        bg.setWidth(value);
    }

    @Override
    public double getHeight() {
        return bg.getHeight();
    }

    @Override
    public void setHeight(double value) {
        unsetClip();
        setHeightInner(value);
        setClip();
    }

    protected void setHeightInner(double value) {
        bg.setHeight(value);
    }

    @Override
    public int hashCode() {
        return modelLineNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CListLineView)) return false;

        CListLineView that = (CListLineView) o;

        return modelLineNum == that.modelLineNum;
    }
}
