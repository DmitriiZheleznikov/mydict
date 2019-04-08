package md.skin.colorschema.impl.pojo.part;

import heli.component.shape.list.centerlist.adj.ICListEditDialogColorSchema;
import javafx.scene.paint.Color;
import md.shape.mdcenterlist.adj.IMDListColorSchema;

public class MDListColorSchema implements IMDListColorSchema {
    Color countNumber;
    Color example1;
    Color example2;
    Color bg;
    Color bgShadow;
    Color bgStroke;
    Color bgStrokeInactive;
    Color bgStrokeInvisible;
    Color text;
    Color oldText;
    Color buttonDeleteNormal;
    Color buttonDeleteHovered;
    Color buttonDeleteInactive;
    Color buttonEditNormal;
    Color buttonEditHovered;
    Color buttonEditInactive;
    MDListEditDialogColorSchema editDialog;

    public MDListColorSchema(MDListEditDialogColorSchema editDialog) {
        this.editDialog = editDialog;
    }

    public MDListColorSchema countNumber(Color value) {
        this.countNumber = value;
        return this;
    }

    public MDListColorSchema example1(Color value) {
        this.example1 = value;
        return this;
    }

    public MDListColorSchema example2(Color value) {
        this.example2 = value;
        return this;
    }

    public MDListColorSchema bg(Color value) {
        this.bg = value;
        return this;
    }

    public MDListColorSchema bgShadow(Color value) {
        this.bgShadow = value;
        return this;
    }

    public MDListColorSchema bgStroke(Color value) {
        this.bgStroke = value;
        return this;
    }

    public MDListColorSchema bgStrokeInactive(Color value) {
        this.bgStrokeInactive = value;
        return this;
    }

    public MDListColorSchema bgStrokeInvisible(Color value) {
        this.bgStrokeInvisible = value;
        return this;
    }

    public MDListColorSchema text(Color value) {
        this.text = value;
        return this;
    }

    public MDListColorSchema oldText(Color value) {
        this.oldText = value;
        return this;
    }

    public MDListColorSchema buttonDeleteNormal(Color value) {
        this.buttonDeleteNormal = value;
        return this;
    }

    public MDListColorSchema buttonDeleteHovered(Color value) {
        this.buttonDeleteHovered = value;
        return this;
    }

    public MDListColorSchema buttonDeleteInactive(Color value) {
        this.buttonDeleteInactive = value;
        return this;
    }

    public MDListColorSchema buttonEditNormal(Color value) {
        this.buttonDeleteNormal = value;
        return this;
    }

    public MDListColorSchema buttonEditHovered(Color value) {
        this.buttonDeleteHovered = value;
        return this;
    }

    public MDListColorSchema buttonEditInactive(Color value) {
        this.buttonDeleteInactive = value;
        return this;
    }

    public MDListColorSchema editDialog(MDListEditDialogColorSchema editDialog) {
        this.editDialog = editDialog;
        return this;
    }

    @Override
    public Color countNumber() {
        return countNumber;
    }

    @Override
    public Color example1() {
        return example1;
    }

    @Override
    public Color example2() {
        return example2;
    }

    @Override
    public Color bg() {
        return bg;
    }

    @Override
    public Color bgShadow() {
        return bgShadow;
    }

    @Override
    public Color bgStroke() {
        return bgStroke;
    }

    @Override
    public Color bgStrokeInactive() {
        return bgStrokeInactive;
    }

    @Override
    public Color bgStrokeInvisible() {
        return bgStrokeInvisible;
    }

    @Override
    public Color text() {
        return text;
    }

    @Override
    public Color oldText() {
        return oldText;
    }

    @Override
    public Color buttonDeleteNormal() {
        return buttonDeleteNormal;
    }

    @Override
    public Color buttonDeleteHovered() {
        return buttonDeleteHovered;
    }

    @Override
    public Color buttonDeleteInactive() {
        return buttonDeleteInactive;
    }

    @Override
    public Color buttonEditNormal() {
        return buttonDeleteNormal;
    }

    @Override
    public Color buttonEditHovered() {
        return buttonDeleteHovered;
    }

    @Override
    public Color buttonEditInactive() {
        return buttonDeleteInactive;
    }

    @Override
    public MDListEditDialogColorSchema editDialog() {
        return editDialog;
    }
}
