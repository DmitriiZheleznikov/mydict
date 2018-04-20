package md.shape;

import heli.component.shape.button.textbutton.HTextButton;
import javafx.scene.paint.Color;

import static md.FontConstant.FONT_ROBOTO_B_SIZE_DEFAULT;
import static md.FontConstant.FONT_ROBOTO_B_URL;

public class MDButton extends HTextButton {
    public MDButton(String text, Color cRegular, Color cInactive, Color cHovered, double size) {
        super(text, cRegular, cInactive, cHovered, FONT_ROBOTO_B_URL, size);
    }

    public MDButton(String text, Color cRegular, Color cInactive, Color cHovered) {
        super(text, cRegular, cInactive, cHovered, FONT_ROBOTO_B_URL, FONT_ROBOTO_B_SIZE_DEFAULT);
    }
}
