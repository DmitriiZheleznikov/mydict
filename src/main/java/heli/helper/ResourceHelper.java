package heli.helper;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ResourceHelper {
    public static Image image(Class<?> clazz, String fileName) {
        return new Image(clazz.getResourceAsStream(fileName));
    }

    public static Font font(Class<?> clazz, String fontUrl, double size) {
        return Font.loadFont(clazz.getResourceAsStream(fontUrl), size);
    }

    public static List<String> readTextFile(Class<?> clazz, String fileName) throws IOException, URISyntaxException {
        return Files.readAllLines(Paths.get(clazz.getResource(fileName).toURI()), StandardCharsets.UTF_8);
    }
}
