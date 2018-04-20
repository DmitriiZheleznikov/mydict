package md.shape.mdcenterlist.view;

import heli.component.shape.list.centerlist.ext.CText;
import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.htweener.fx.ext.HRectangle;
import javafx.scene.Scene;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.shape.mdcenterlist.view.helper.ExampleViewHelper;

import static md.FontConstant.*;
import static md.FontConstant.FONT_UBUTNU_RI_SIZE_DEFAULT;
import static md.FontConstant.FONT_UBUTNU_RI_URL;

public class MDListLineView extends CListLineView {
    private CText tCount;
    private HRectangle tCountClip;

    private CText tExample1;
    private HRectangle tExample1Clip;

    private CText tExample2;
    private HRectangle tExample2Clip;

    public MDListLineView(MDListView parent) {
        super(parent);
        this.tCount = new CText("", parent.getColorSchema().text(), FONT_UBUTNU_R_URL, FONT_UBUTNU_R_SIZE_DEFAULT-4);
        this.tExample1 = new CText("", parent.getColorSchema().example1(), FONT_UBUTNU_RI_URL, FONT_UBUTNU_RI_SIZE_DEFAULT);
        this.tExample2 = new CText("", parent.getColorSchema().example2(), FONT_UBUTNU_RI_URL, FONT_UBUTNU_RI_SIZE_DEFAULT);
        this.tCountClip = new HRectangle();
        this.tExample1Clip = new HRectangle();
        this.tExample2Clip = new HRectangle();

        tExample1.setClip(tExample1Clip);
        tExample2.setClip(tExample2Clip);

        tCount.setColor(parent.getColorSchema().countNumber());
        bDelete.setColor(parent.getColorSchema().buttonDeleteNormal());
        tExample1.setColor(parent.getColorSchema().example1());
        tExample2.setColor(parent.getColorSchema().example2());

        hide();
    }

    @Override
    public void show() {
        showImmediately(bg, tText, tCount, bDelete, tExample1, tExample2);
    }

    @Override
    public void hide() {
        hideImmediately(bg, tText, tCount, bDelete, tExample1, tExample2);
    }

    public CText gettCount() {
        return tCount;
    }

    public CText gettExample1() {
        return tExample1;
    }

    public CText gettExample2() {
        return tExample2;
    }

    @Override
    public void renewContent(CListLineModel modelLine) {
        super.renewContent(modelLine);
        MDListLineModel lineModel = (MDListLineModel) modelLine;

        if (lineModel == null) {
            tCount.setText("");
            tExample1.setText("");
            tExample2.setText("");
        } else {
            tCount.setText(Integer.valueOf(lineModel.getCount()).toString());
            double size = tText.getScene().getWidth() - parent.getSizeBetween() * 8;
            ExampleViewHelper.setExampleText(tExample1, ExampleViewHelper.getExampleText(lineModel.getExamples(), 0), size);
            ExampleViewHelper.setExampleText(tExample2, ExampleViewHelper.getExampleText(lineModel.getExamples(), 1), size);
        }
    }

    @Override
    public void renewContentBy(CListLineView lineView) {
        super.renewContentBy(lineView);
        MDListLineView row = (MDListLineView) lineView;
        tCount.setText((row.gettCount().getText() == null || "".equals(row.gettCount().getText())) ? "" : row.gettCount().getText());
        tExample1.setText(row.gettExample1().getText());
        tExample2.setText(row.gettExample2().getText());
    }

    @Override
    public void resize(double sf) {
        ExampleViewHelper.resetCache();
        dropShadow.setRadius(3f * sf);
        dropShadow.setOffsetX(2f * sf);
        dropShadow.setOffsetY(2f * sf);
        bg.setStrokeWidth(Math.round(2f * sf - 0.49));

        tText.resize(sf);
        bDelete.resize(sf);
        tCount.resize(sf);
        tExample1.resize(sf);
        tExample2.resize(sf);
    }

    @Override
    public void addToScene(Scene scene) {
        parent.getGroup().getChildren().addAll(bg, tText, tCount, bDelete, tExample1, tExample2);
    }

    @Override
    public void clip() {
        super.clip();
        clipSize(tCountClip);
        tCount.setClip(tCountClip);
    }

    @Override
    public void unsetClip() {
        super.unsetClip();
        tExample1.setClip(null);
        tExample2.setClip(null);
        tCount.setClip(null);
    }

    @Override
    public void setClip() {
        super.setClip();
        tExample1.setClip(tExample1Clip);
        tExample2.setClip(tExample2Clip);
        tCount.setClip(tCountClip);
    }

    @Override
    protected void setXInner(double x) {
        super.setXInner(x);
        tCount.setX(x + parent.getSizeBetween() * 2);
        tExample1.setX(x + parent.getSizeBetween() * 2);
        tExample2.setX(x + parent.getSizeBetween() * 2);
        tExample1Clip.setX(x);
        tExample2Clip.setX(x);
    }

    @Override
    protected void setYInner(double y) {
        super.setYInner(y);
        tCount.setY(y + tText.getHeight() * 1.5);
        tExample1.setY(tText.getY() + tText.getHeight() * 1.2);
        tExample2.setY(tExample1.getY() + tExample1.getHeight());
        tExample1Clip.setY(y);
        tExample2Clip.setY(y);
    }

    @Override
    public void setCoreOpacity(double alpha) {
        super.setCoreOpacity(alpha);
        tExample1.setOpacity(alpha);
        tExample2.setOpacity(alpha);
        tCount.setOpacity(alpha);
        if (alpha > 0f) {
            tExample1.setVisible(true);
            tExample2.setVisible(true);
            tCount.setVisible(true);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        tCount.setVisible(visible);
        tExample1.setVisible(visible);
        tExample2.setVisible(visible);
    }

    @Override
    protected void setWidthInner(double value) {
        super.setWidthInner(value);
        tExample1Clip.setWidth(value);
        tExample2Clip.setWidth(value);
    }

    @Override
    protected void setHeightInner(double value) {
        super.setHeightInner(value);
        tExample1Clip.setHeight(value);
        tExample2Clip.setHeight(value);
    }
}
