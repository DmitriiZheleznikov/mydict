package heli.component.shape.text.htext;

import heli.helper.ResourceHelper;
import heli.htweener.fx.ext.HText;
import heli.htweener.tweenable.ITweenableColor;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

public class HTextFlow implements ITweenableShape, ITweenableColor {
    private static final String EMPTY_STR = "";

    protected Group group;
    protected TextFlow flow;
    protected List<HText> partBold;
    protected List<HText> partRegular;
    protected List<HString.FontWeight> flowFontWeight;

    protected Font fontBold;
    protected Font fontRegular;
    protected Color color;
    protected double fontSizeDef;
    private String fontRegularUrl;
    private String fontBoldUrl;

    public HTextFlow(Color color, String fontRegularUrl, String fontBoldUrl, double fontSizeDef) {
        this.fontBoldUrl = fontBoldUrl;
        this.fontRegularUrl = fontRegularUrl;
        this.fontSizeDef = fontSizeDef;
        this.fontBold = ResourceHelper.font(HTextFlow.class, fontBoldUrl, fontSizeDef);
        this.fontRegular = ResourceHelper.font(HTextFlow.class, fontRegularUrl, fontSizeDef);
        this.color = color;
        this.flow = new TextFlow();
        this.group = new Group(flow);
        this.partRegular = new ArrayList<>();
        this.partBold = new ArrayList<>();
        this.flowFontWeight = new ArrayList<>();
    }

    public void resize(double scaleFactor) {
        this.fontBold = ResourceHelper.font(HTextFlow.class, fontBoldUrl, getFontSize(scaleFactor));
        this.fontRegular = ResourceHelper.font(HTextFlow.class, fontRegularUrl, getFontSize(scaleFactor));
    }

    protected double getFontSize(double scaleFactor) {
        if (scaleFactor == 1) return fontSizeDef;

        return Math.round(10*(fontSizeDef*scaleFactor)-0.49)/10;
    }

    public void clear() {
        clearPart(partRegular);
        clearPart(partBold);
        flow.getChildren().clear();
        flowFontWeight.clear();
    }

    public void renewContent(HStringFlow strFlow) {
        clear();
        for (HString str : strFlow.getData()) add(str);
    }

    public void add(HString value) {
        add(value.fontWeight, value.getString());
    }

    public void add(HString.FontWeight fontWeight, String value) {
        HText text = null;

        List<HText> part = fontWeight == HString.FontWeight.BOLD ? partBold : partRegular;
        Font font = fontWeight == HString.FontWeight.BOLD ? fontBold : fontRegular;

        for (HText nxt : part) {
            if (nxt.getText().length() == 0) text = nxt;
        }

        if (text == null) {
            text = new HText(font, color);
            part.add(text);
        }

        text.setText(value);
        flow.getChildren().add( text);
        flowFontWeight.add(fontWeight);
    }

    public void renewContentBy(HTextFlow thatFlow) {
        this.clear();
        for (int i = 0; i < flowFontWeight.size(); i++) {
            this.add(thatFlow.getFlowFontWeight().get(i), ((HText)thatFlow.getFlow().getChildren().get(i)).getText());
        }
    }

    public Group getGroup() {
        return group;
    }

    public TextFlow getFlow() {
        return flow;
    }

    public List<HString.FontWeight> getFlowFontWeight() {
        return flowFontWeight;
    }

    private void clearPart(List<HText> part) {
        for (HText txt : part) txt.setText(EMPTY_STR);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color value) {
        this.color = value;
    }

    @Override
    public double getX() {
        return group.getLayoutX();
    }

    @Override
    public void setX(double x) {
        group.setLayoutX(x);
    }

    @Override
    public double getY() {
        return group.getLayoutY();
    }

    @Override
    public void setY(double y) {
        group.setLayoutY(y);
    }

    @Override
    public double getOpacity() {
        return flow.getOpacity();
    }

    @Override
    public void setOpacity(double alpha) {
        flow.setOpacity(alpha);
        for (HText txt : partRegular) txt.setOpacity(alpha);
        for (HText txt : partBold) txt.setOpacity(alpha);
    }

    @Override
    public double getRotate() {
        return 0;
    }

    @Override
    public void setRotate(double rotation) {

    }

    @Override
    public double getScaleX() {
        return 0;
    }

    @Override
    public void setScaleX(double scaleX) {

    }

    @Override
    public double getScaleY() {
        return 0;
    }

    @Override
    public void setScaleY(double scaleY) {

    }

    @Override
    public boolean isVisible() {
        return flow.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        flow.setVisible(visible);
        for (HText txt : partRegular) txt.setVisible(visible);
        for (HText txt : partBold) txt.setVisible(visible);
    }

    @Override
    public double getWidth() {
        return flow.getWidth();
    }

    @Override
    public void setWidth(double value) {
        flow.setMaxWidth(value);
    }

    @Override
    public double getHeight() {
        return flow.getHeight();
    }

    @Override
    public void setHeight(double value) {
        flow.setMaxHeight(value);
    }
}
