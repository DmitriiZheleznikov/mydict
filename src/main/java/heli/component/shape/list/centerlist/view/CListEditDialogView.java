package heli.component.shape.list.centerlist.view;

import heli.component.shape.list.centerlist.ext.CButton;
import heli.component.shape.list.centerlist.ext.CTextField;
import heli.component.shape.list.centerlist.view.effect.appear.IListEditDialogAppearEffect;
import heli.component.shape.list.centerlist.view.effect.appear.editDialogImpl.DefaultListEditDialogAppearEffect;
import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tween.ICallable;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.util.Random;

import static heli.component.shape.list.centerlist.adj.CListFontConstants.FONT_UBUTNU_R_SIZE_DEFAULT;
import static heli.component.shape.list.centerlist.adj.CListFontConstants.FONT_UBUTNU_R_URL;

public class CListEditDialogView {
    protected static final Random random = new Random();
    protected static IListEditDialogAppearEffect[] LIST_OF_EFFECTS = new IListEditDialogAppearEffect[] {
        new DefaultListEditDialogAppearEffect()
    };

    protected CListView listView;
    protected CTextField tfNewValue;
    protected CButton bSave;
    protected CButton bCancel;

    protected HRectangle bg;

    public CListEditDialogView(CListView listView) {
        this.listView = listView;
        this.bg = new HRectangle();
        this.tfNewValue = new CTextField("", listView.getColorSchema().editDialog().inputNewValueText(), FONT_UBUTNU_R_URL, FONT_UBUTNU_R_SIZE_DEFAULT);
        this.bSave = new CButton("SAVE",
                listView.getColorSchema().editDialog().buttonSaveNormal(),
                listView.getColorSchema().editDialog().buttonSaveInactive(),
                listView.getColorSchema().editDialog().buttonSaveHovered(),
                FONT_UBUTNU_R_SIZE_DEFAULT);//*1.5);
        this.bCancel = new CButton("CANCEL",
                listView.getColorSchema().editDialog().buttonCancelNormal(),
                listView.getColorSchema().editDialog().buttonCancelInactive(),
                listView.getColorSchema().editDialog().buttonCancelHovered(),
                FONT_UBUTNU_R_SIZE_DEFAULT);//*1.5);
        tfNewValue.setAlignment(Pos.CENTER);
        tfNewValue.setBgColor(listView.getColorSchema().editDialog().inputNewValueBg());
        bg.setColor(listView.getColorSchema().editDialog().listHover());

//        HDropShadow dropShadow = new HDropShadow();
//        dropShadow.setRadius(3);
//        dropShadow.setColor(listView.getColorSchema().editDialog().listHover().darker());
//        dropShadow.setOffsetX(2);
//        dropShadow.setOffsetY(2);
//        tfNewValue.setEffect(dropShadow);
//        bSave.setEffect(dropShadow);
//        bCancel.setEffect(dropShadow);

        init();
        hide();
    }

    protected void init() {
        tfNewValue.clear();
        bSave.setRegular();
        bCancel.setRegular();
        //locate();
    }

    public void addToScene(Scene scene) {
        listView.getGroup().getChildren().addAll(bg, tfNewValue, bSave, bCancel);
    }

    public void locate() {
        bg.setX(listView.getBgRect().getX());
        bg.setY(listView.getBgRect().getY());
        bg.setHeight(listView.getBgRect().getHeight());
        bg.setWidth(listView.getBgRect().getWidth());

        tfNewValue.setLayoutX(listView.getCurrentLine().getX() + listView.getSizeBetween()*5);
        tfNewValue.setLayoutY(listView.getCurrentLine().gettText().getY() + listView.getCurrentLine().gettText().getHeight());
        tfNewValue.setPrefWidth(listView.getCurrentLine().getWidth() - listView.getSizeBetween()*10);

        //   text_input
        //--||||||||||||--
        //----SS----CC----
        double offsetX = tfNewValue.getLayoutX();
        double quoterX = tfNewValue.getWidth()/4;
        bSave.setX(offsetX + quoterX - bSave.getWidth()/2);
        bCancel.setX(offsetX + 3*quoterX - bCancel.getWidth()/2);
        bSave.setY(tfNewValue.getLayoutY() + tfNewValue.getHeight() + bSave.getHeight());
        bCancel.setY(tfNewValue.getLayoutY() + tfNewValue.getHeight() + bCancel.getHeight());
    }

    public void resize(double sf) {
        //boolean isVisible = bg.isVisible();
        //hide();
        tfNewValue.resize(sf);
        bSave.resize(sf);
        bCancel.resize(sf);
        locate();
        //if (isVisible) show();
    }

    public void show() {
        //if (1==1) throw new RuntimeException("stop");
        locate();
        bg.setVisible(true);
        tfNewValue.setVisible(true);
        bSave.setVisible(true);
        bCancel.setVisible(true);
    }

    public void hide() {
        bg.setVisible(false);
        tfNewValue.setVisible(false);
        bSave.setVisible(false);
        bCancel.setVisible(false);
    }

    public void appear() {
        init();
        getRandomAppearEffect().appear(listView, null);
    }

    public void disappear() {
        getRandomAppearEffect().disappear(listView, null);
    }

    public void setOnCancel(ICallable onCancel) {
        bCancel.setOnMouseClicked(event -> {
            if (onCancel != null) onCancel.call();
        });
    }

    public void setOnSave(ICallable onSave) {
        bSave.setOnMouseClicked(event -> {
            if (onSave != null) onSave.call();
        });
    }

    public CTextField getTfNewValue() {
        return tfNewValue;
    }

    public CButton getbSave() {
        return bSave;
    }

    public CButton getbCancel() {
        return bCancel;
    }

    public HRectangle getBg() {
        return bg;
    }

    protected IListEditDialogAppearEffect getRandomAppearEffect() {
        return LIST_OF_EFFECTS[random.nextInt(LIST_OF_EFFECTS.length)];
    }
}
