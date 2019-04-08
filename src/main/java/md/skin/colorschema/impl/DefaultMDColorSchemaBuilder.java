package md.skin.colorschema.impl;

import javafx.scene.paint.Color;
import md.skin.colorschema.impl.builder.AbstractMDColorSchemaBuilder;
import md.skin.colorschema.impl.pojo.MDColorSchema;

public class DefaultMDColorSchemaBuilder extends AbstractMDColorSchemaBuilder {
    private static final Color BG_SHADOW = Color.color(0.5, 0.5, 0.5, 0.6);

    private static final Color TOP_BG = Color.color(0.149, 0.651, 0.6039);
    private static final Color TOP_BUTTON_NORMAL = Color.color(0.0941, 0.4118, 0.3647);
    private static final Color TOP_BUTTON_HOVERED = Color.color(0.051, 0.2667, 0.2392);
    private static final Color TOP_BUTTON_INACTIVE = Color.color(0.1176, 0.498, 0.451);

    private static final Color CENTER_BG = Color.ANTIQUEWHITE;
    private static final Color CENTER_HINT = Color.color(0.9804, 0.702, 0.3843);
    private static final Color CENTER_START_BUTTON_NORMAL = Color.color(0.9804, 0.702, 0.3843);
    private static final Color CENTER_START_BUTTON_HOVERED = Color.color(0.9804, 0.5922, 0.2902);
    private static final Color CENTER_START_BUTTON_INACTIVE = Color.color(0.9804, 0.7373, 0.5059);
    private static final Color CENTER_PROGRESS_MIN = Color.color(0.9804, 0.7373, 0.5059);
    private static final Color CENTER_PROGRESS_MAX = Color.color(0.9804, 0.702, 0.3843);
    private static final Color CENTER_PROGRESS_FINAL = Color.color(0.9804, 0.5922, 0.2902);

    private static final Color CENTER_LIST_LINE_BG = Color.color(1, 0.9765, 0.9569);
    private static final Color CENTER_LIST_LINE_BG_SHADOW = Color.color(0.5, 0.5, 0.5, 0.1);
    private static final Color CENTER_LIST_LINE_BG_STROKE = Color.gray(0.9, 0.2);
    private static final Color CENTER_LIST_LINE_BG_STROKE_INACTIVE = Color.gray(0.9, 0.1);
    private static final Color CENTER_LIST_LINE_BG_STROKE_INVISIBLE = Color.gray(0.9, 0);
    private static final Color CENTER_LIST_LINE_WORD = Color.color(0.451, 0.3176, 0.2);
    private static final Color CENTER_LIST_LINE_OLD_WORD = Color.color(0.8588, 0.7843, 0.6941);
    private static final Color CENTER_LIST_LINE_EXAMPLE1 = Color.color(0.7412, 0.6667, 0.5843);
    private static final Color CENTER_LIST_LINE_EXAMPLE2 = Color.color(0.8588, 0.7843, 0.6941);
    private static final Color CENTER_LIST_LINE_BUTTON_REMOVE_NORMAL = Color.color(0.8588, 0.7843, 0.6941);
    private static final Color CENTER_LIST_LINE_BUTTON_REMOVE_HOVERED = Color.color(0.451, 0.3176, 0.2);
    private static final Color CENTER_LIST_LINE_BUTTON_REMOVE_INACTIVE = Color.color(0.8588, 0.7843, 0.6941);
    private static final Color CENTER_LIST_LINE_BUTTON_EDIT_NORMAL = Color.color(0.8588, 0.7843, 0.6941);
    private static final Color CENTER_LIST_LINE_BUTTON_EDIT_HOVERED = Color.color(0.451, 0.3176, 0.2);
    private static final Color CENTER_LIST_LINE_BUTTON_EDIT_INACTIVE = Color.color(0.8588, 0.7843, 0.6941);

    private static final Color BOTTOM_BG = Color.color(0.698, 0.8745, 0.8588);
    private static final Color BOTTOM_BUTTON_NORMAL = Color.color(0.3255, 0.4196, 0.4353);
    private static final Color BOTTOM_BUTTON_HOVERED = Color.color(0.2196, 0.2863, 0.3059);
    private static final Color BOTTOM_BUTTON_INACTIVE = Color.color(0.4667, 0.6196, 0.6392);

    //EDIT DIALOG
    private static final Color CENTER_LIST_EDIT_HOVER = Color.color(0.5, 0.5, 0.5, 0.5);
    private static final Color CENTER_LIST_EDIT_NEW_VAL_TEXT = Color.color(0.451, 0.3176, 0.2);
    private static final Color CENTER_LIST_EDIT_NEW_VAL_BG = Color.color(1, 0.9765, 0.9569);
    private static final Color CENTER_LIST_EDIT_SAVE_NORMAL = Color.color(0.5098, 0.4470, 0.39608);
    private static final Color CENTER_LIST_EDIT_SAVE_HOVERED = Color.color(0.372513, 0.3490, 0.3137);
    private static final Color CENTER_LIST_EDIT_SAVE_INACTIVE = Color.color(0.6863, 0.6235, 0.5686);
    private static final Color CENTER_LIST_EDIT_CANCEL_NORMAL = Color.color(0.5098, 0.4470, 0.39608);
    private static final Color CENTER_LIST_EDIT_CANCEL_HOVERED = Color.color(0.372513, 0.3490, 0.3137);
    private static final Color CENTER_LIST_EDIT_CANCEL_INACTIVE = Color.color(0.6863, 0.6235, 0.5686);

    @Override
    public MDColorSchema build() {
        MDColorSchema schema = super.build();

        schema.top().bg()
                .background(TOP_BG)
                .shadow(BG_SHADOW);
        schema.top().button()
                .normal(TOP_BUTTON_NORMAL)
                .hovered(TOP_BUTTON_HOVERED)
                .inactive(TOP_BUTTON_INACTIVE);

        schema.center().bg()
                .background(CENTER_BG)
                .shadow(BG_SHADOW);
        schema.center()
                .hint(CENTER_HINT);
        schema.center().startButton()
                .normal(CENTER_START_BUTTON_NORMAL)
                .hovered(CENTER_START_BUTTON_HOVERED)
                .inactive(CENTER_START_BUTTON_INACTIVE);
        schema.center().progress()
                .min(CENTER_PROGRESS_MIN)
                .max(CENTER_PROGRESS_MAX)
                .fin(CENTER_PROGRESS_FINAL);
        schema.center().list()
                .bg(CENTER_LIST_LINE_BG)
                .bgShadow(CENTER_LIST_LINE_BG_SHADOW)
                .bgStroke(CENTER_LIST_LINE_BG_STROKE)
                .bgStrokeInactive(CENTER_LIST_LINE_BG_STROKE_INACTIVE)
                .bgStrokeInvisible(CENTER_LIST_LINE_BG_STROKE_INVISIBLE)
                .text(CENTER_LIST_LINE_WORD)
                .oldText(CENTER_LIST_LINE_OLD_WORD)
                .example1(CENTER_LIST_LINE_EXAMPLE1)
                .example2(CENTER_LIST_LINE_EXAMPLE2)
                .buttonDeleteNormal(CENTER_LIST_LINE_BUTTON_REMOVE_NORMAL)
                .buttonDeleteHovered(CENTER_LIST_LINE_BUTTON_REMOVE_HOVERED)
                .buttonDeleteInactive(CENTER_LIST_LINE_BUTTON_REMOVE_INACTIVE)
                .buttonEditNormal(CENTER_LIST_LINE_BUTTON_EDIT_NORMAL)
                .buttonEditHovered(CENTER_LIST_LINE_BUTTON_EDIT_HOVERED)
                .buttonEditInactive(CENTER_LIST_LINE_BUTTON_EDIT_INACTIVE);
        schema.center().list().editDialog()
                .listHover(CENTER_LIST_EDIT_HOVER)
                .inputNewValueText(CENTER_LIST_EDIT_NEW_VAL_TEXT)
                .inputNewValueBg(CENTER_LIST_EDIT_NEW_VAL_BG)
                .buttonSaveNormal(CENTER_LIST_EDIT_SAVE_NORMAL)
                .buttonSaveHovered(CENTER_LIST_EDIT_SAVE_HOVERED)
                .buttonSaveInactive(CENTER_LIST_EDIT_SAVE_INACTIVE)
                .buttonCancelNormal(CENTER_LIST_EDIT_CANCEL_NORMAL)
                .buttonCancelHovered(CENTER_LIST_EDIT_CANCEL_HOVERED)
                .buttonCancelInactive(CENTER_LIST_EDIT_CANCEL_INACTIVE);

        schema.bottom().bg()
                .background(BOTTOM_BG)
                .shadow(BG_SHADOW);
        schema.bottom().button()
                .normal(BOTTOM_BUTTON_NORMAL)
                .hovered(BOTTOM_BUTTON_HOVERED)
                .inactive(BOTTOM_BUTTON_INACTIVE);

        return schema;
    }
}
