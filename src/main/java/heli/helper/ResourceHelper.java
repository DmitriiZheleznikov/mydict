package heli.helper;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceHelper {
    public static Image image(Class<?> clazz, String fileName) {
        return new Image(clazz.getResourceAsStream(fileName));
    }

    public static Font font(Class<?> clazz, String fontUrl, double size) {
        return Font.loadFont(clazz.getResourceAsStream(fontUrl), size);
    }

    public static List<String> readTextFile(Class<?> clazz, String fileName) throws IOException, URISyntaxException {
        URI uri = clazz.getResource(fileName).toURI();
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        FileSystem zipfs = FileSystems.newFileSystem(uri, env);

        List<String> result = Files.readAllLines(Paths.get(uri), StandardCharsets.UTF_8);

        zipfs.close();

        return result;
    }
}
