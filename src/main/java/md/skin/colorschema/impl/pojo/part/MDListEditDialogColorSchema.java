package md.skin.colorschema.impl.pojo.part;

import heli.component.shape.list.centerlist.adj.ICListEditDialogColorSchema;
import javafx.scene.paint.Color;

public class MDListEditDialogColorSchema implements ICListEditDialogColorSchema {
    Color listHover;
    Color inputNewValueText;
    Color inputNewValueBg;
    Color buttonSaveNormal;
    Color buttonSaveHovered;
    Color buttonSaveInactive;
    Color buttonCancelNormal;
    Color buttonCancelHovered;
    Color buttonCancelInactive;

    public MDListEditDialogColorSchema listHover(Color listHover) {
        this.listHover = listHover;
        return this;
    }

    public MDListEditDialogColorSchema inputNewValueText(Color inputNewValueText) {
        this.inputNewValueText = inputNewValueText;
        return this;
    }

    public MDListEditDialogColorSchema inputNewValueBg(Color inputNewValueBg) {
        this.inputNewValueBg = inputNewValueBg;
        return this;
    }

    public MDListEditDialogColorSchema buttonSaveNormal(Color buttonSaveNormal) {
        this.buttonSaveNormal = buttonSaveNormal;
        return this;
    }

    public MDListEditDialogColorSchema buttonSaveHovered(Color buttonSaveHovered) {
        this.buttonSaveHovered = buttonSaveHovered;
        return this;
    }

    public MDListEditDialogColorSchema buttonSaveInactive(Color buttonSaveInactive) {
        this.buttonSaveInactive = buttonSaveInactive;
        return this;
    }

    public MDListEditDialogColorSchema buttonCancelNormal(Color buttonCancelNormal) {
        this.buttonCancelNormal = buttonCancelNormal;
        return this;
    }

    public MDListEditDialogColorSchema buttonCancelHovered(Color buttonCancelHovered) {
        this.buttonCancelHovered = buttonCancelHovered;
        return this;
    }

    public MDListEditDialogColorSchema buttonCancelInactive(Color buttonCancelInactive) {
        this.buttonCancelInactive = buttonCancelInactive;
        return this;
    }

    @Override
    public Color buttonSaveNormal() {
        return buttonSaveNormal;
    }

    @Override
    public Color buttonSaveHovered() {
        return buttonSaveHovered;
    }

    @Override
    public Color buttonSaveInactive() {
        return buttonSaveInactive;
    }

    @Override
    public Color buttonCancelNormal() {
        return buttonCancelNormal;
    }

    @Override
    public Color buttonCancelHovered() {
        return buttonCancelHovered;
    }

    @Override
    public Color buttonCancelInactive() {
        return buttonCancelInactive;
    }

    @Override
    public Color inputNewValueText() {
        return inputNewValueText;
    }

    @Override
    public Color inputNewValueBg() {
        return inputNewValueBg;
    }

    @Override
    public Color listHover() {
        return listHover;
    }
}
