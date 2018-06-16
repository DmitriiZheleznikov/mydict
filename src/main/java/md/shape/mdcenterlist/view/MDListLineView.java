package md.shape.mdcenterlist.view;

import heli.component.shape.list.centerlist.ext.CText;
import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.component.shape.list.centerlist.view.CListLineView;
import heli.component.shape.text.htext.HStringFlow;
import heli.component.shape.text.htext.HTextFlow;
import heli.htweener.fx.ext.HRectangle;
import javafx.scene.Scene;
import md.shape.mdcenterlist.model.MDListLineModel;

import java.util.List;

import static md.FontConstant.*;

public class MDListLineView extends CListLineView {
    private CText tCount;
    private HRectangle tCountClip;
    private HTextFlow tExample1;
    private HTextFlow tExample2;

    public MDListLineView(MDListView parent) {
        super(parent);
        this.tCount = new CText("", parent.getColorSchema().text(), FONT_UBUTNU_R_URL, FONT_UBUTNU_R_SIZE_DEFAULT-4);
        this.tExample1 = new HTextFlow(parent.getColorSchema().example1(), FONT_UBUTNU_RI_URL, FONT_UBUTNU_BI_URL, FONT_UBUTNU_RI_SIZE_DEFAULT);
        this.tExample2 = new HTextFlow(parent.getColorSchema().example2(), FONT_UBUTNU_RI_URL, FONT_UBUTNU_BI_URL, FONT_UBUTNU_RI_SIZE_DEFAULT);
        this.tCountClip = new HRectangle();

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

    public HTextFlow gettExample1() {
        return tExample1;
    }

    public HTextFlow gettExample2() {
        return tExample2;
    }

    @Override
    public void renewContent(CListLineModel modelLine) {
        super.renewContent(modelLine);
        MDListLineModel lineModel = (MDListLineModel) modelLine;

        tExample1.clear();
        tExample2.clear();
        if (lineModel == null) {
            tCount.setText("");
        } else {
            tCount.setText(Integer.valueOf(lineModel.getCount()).toString());
            List<HStringFlow> examplesFlow = ((MDListLineModel) modelLine).getExamples();
            if (examplesFlow.size() > 0) tExample1.renewContent(examplesFlow.get(0));
            if (examplesFlow.size() > 1) tExample2.renewContent(examplesFlow.get(1));
        }
    }

    @Override
    public void renewContentBy(CListLineView lineView) {
        super.renewContentBy(lineView);
        MDListLineView row = (MDListLineView) lineView;
        tCount.setText((row.gettCount().getText() == null || "".equals(row.gettCount().getText())) ? "" : row.gettCount().getText());
        tExample1.renewContentBy(row.gettExample1());
        tExample2.renewContentBy(row.gettExample2());
    }

    @Override
    public void resize(double sf) {
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
        parent.getGroup().getChildren().addAll(bg, tText, tCount, bDelete, tExample1.getGroup(), tExample2.getGroup());
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
        tCount.setClip(null);
    }

    @Override
    public void setClip() {
        super.setClip();
        tCount.setClip(tCountClip);
    }

    @Override
    protected void setXInner(double x) {
        super.setXInner(x);
        tCount.setX(x + parent.getSizeBetween() * 2);
        tExample1.setX(x + parent.getSizeBetween() * 2);
        tExample2.setX(x + parent.getSizeBetween() * 2);
    }

    @Override
    protected void setYInner(double y) {
        super.setYInner(y);
        tCount.setY(y + tText.getHeight() * 1.5);
        tExample1.setY(tText.getY() + tText.getHeight() * 1.2);
        tExample2.setY(tExample1.getY() + tExample1.getHeight());
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
        double size = getExampleWidth(value);
        tExample1.getFlow().setMaxWidth(size);
        tExample2.getFlow().setMaxWidth(size);
    }

    private double getExampleWidth(double value) {
        if (tText != null && tText.getScene() != null && parent != null) {
            return tText.getScene().getWidth() - parent.getSizeBetween() * 8;
        }
        return value;
    }

    @Override
    protected void setHeightInner(double value) {
        super.setHeightInner(value);
    }
}
