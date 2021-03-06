package heli.component.shape.list.centerlist.ext;

import heli.component.shape.button.textbutton.HTextButton;
import javafx.scene.paint.Color;

import static heli.component.shape.list.centerlist.adj.CListFontConstants.FONT_ROBOTO_B_SIZE_DEFAULT;
import static heli.component.shape.list.centerlist.adj.CListFontConstants.FONT_ROBOTO_B_URL;

public class CButton extends HTextButton {

    public CButton(String text, Color cRegular, Color cInactive, Color cHovered, double size) {
        super(text, cRegular, cInactive, cHovered, FONT_ROBOTO_B_URL, size);
    }

    public CButton(String text, Color cRegular, Color cInactive, Color cHovered) {
        super(text, cRegular, cInactive, cHovered, FONT_ROBOTO_B_URL, FONT_ROBOTO_B_SIZE_DEFAULT);
    }


}
