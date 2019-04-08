package heli.helper;

import javafx.scene.paint.Color;

public class NodeStyleHelper {
    public static String addProperty(String style, String prop, Color color) {
        return addProperty(style, prop,
                "#"
                + Integer.toHexString((int)Math.round(color.getRed() * 255.0))
                + Integer.toHexString((int)Math.round(color.getGreen() * 255.0))
                + Integer.toHexString((int)Math.round(color.getBlue() * 255.0)));
    }

    public static String addProperty(String style, String prop, String value) {
        if (style == null || style.length() == 0) return style;
        if (prop == null || prop.length() == 0) return style;

        return removeProperty(style, prop) + prop + ":" + value + ";";
    }

    public static String removeProperty(String style, String prop) {
        if (style == null || style.length() == 0) return style;
        if (prop == null || prop.length() == 0) return style;

        int startIndx = style.indexOf(prop);
        if (startIndx >= 0) {
            int endIndx = style.indexOf(";", startIndx);

            if (endIndx < 0) {
                return style.substring(0, startIndx);
            }
            else if ((endIndx+1) == style.length()) {
                return style.substring(0, startIndx);
            }
            else {
                return style.substring(0, startIndx) + style.substring(endIndx+1);
            }
        }

        return style;
    }
}
