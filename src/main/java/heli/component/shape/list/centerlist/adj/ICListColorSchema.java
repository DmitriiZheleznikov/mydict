package heli.component.shape.list.centerlist.adj;

import javafx.scene.paint.Color;

public interface ICListColorSchema {
    Color bg();
    Color bgShadow();
    Color bgStroke();
    Color bgStrokeInactive();
    Color bgStrokeInvisible();
    Color text();
    Color oldText();
    Color buttonDeleteNormal();
    Color buttonDeleteHovered();
    Color buttonDeleteInactive();
    Color buttonEditNormal();
    Color buttonEditHovered();
    Color buttonEditInactive();
    ICListEditDialogColorSchema editDialog();
}
