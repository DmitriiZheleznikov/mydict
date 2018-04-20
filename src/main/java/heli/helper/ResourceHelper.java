package heli.helper;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class ResourceHelper {
    public static Image image(Class<?> clazz, String fileName) {
        return new Image(clazz.getResourceAsStream(fileName));
    }

    public static Font font(Class<?> clazz, String fontUrl, double size) {
        return Font.loadFont(clazz.getResourceAsStream(fontUrl), size);
    }
}
