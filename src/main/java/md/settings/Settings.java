package md.settings;

import heli.htweener.HT;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Settings of program which reads/stores: window size, paths to files and state for each file
 */
public class Settings {
    private static final Map<String, String> SETTINGS = new HashMap<>(5);

    private static final String SETTINGS_PROPERTY_HEIGHT = "height=";
    private static final String SETTINGS_PROPERTY_WIDTH = "width=";
    private static final String SETTINGS_PROPERTY_MAXIMIZED = "maximized=";
    private static final String SETTINGS_PROPERTY_MDF = "md_file_path=";
    private static final String SETTINGS_PROPERTY_F = "file_path=";
    private static final String SETTINGS_PROPERTY_STATE_SAVE = "state_save:";//state_save:/path/to/file.txt=word   all = will be replaced to _

    private static final String FILE_NAME = "mydict.ini";
    private static final int SAVE_DELAY = 2000;
    private static final String SAVE_DELAY_NAME = "settings_save_time";

    static {
        init();
        performRead();
    }

    public static void saveWindowSize(final double newHeight, final double newWidth, final boolean isMaximized) {
        if (!isMaximized) {
            setDouble(SETTINGS_PROPERTY_WIDTH, newWidth);
            setDouble(SETTINGS_PROPERTY_HEIGHT, newHeight);
        }
        setBoolean(SETTINGS_PROPERTY_MAXIMIZED, isMaximized);
        scheduleSave();
    }

    public static void saveMDPath(String path) {
        set(SETTINGS_PROPERTY_MDF, path);
        performSave();
    }

    public static void saveFPath(String path) {
        set(SETTINGS_PROPERTY_F, path);
        performSave();
    }

    public static void saveState(String filePath, String state) {
        set(getKeyForState(filePath), state);
        performSave();
    }

    public static double getHeight() {
        return getDouble(SETTINGS_PROPERTY_HEIGHT);
    }

    public static double getWidth() {
        return getDouble(SETTINGS_PROPERTY_WIDTH);
    }

    public static boolean isMaximized() {
        return getBoolean(SETTINGS_PROPERTY_MAXIMIZED);
    }

    public static String getMDFilePath() {
        return get(SETTINGS_PROPERTY_MDF);
    }

    public static String getFilePath() {
        return get(SETTINGS_PROPERTY_F);
    }

    public static String getState(String filePath) {
        return get(getKeyForState(filePath));
    }

    public static void removeState(String filePath) {
        SETTINGS.remove(getKeyForState(filePath));
    }

    private static String getKeyForState(String filePath) {
        return SETTINGS_PROPERTY_STATE_SAVE + filePath.replaceAll("=", "_") + "=";
    }

    private static void performSave() {
        HT.kill(SAVE_DELAY_NAME);
        try {
            Files.write(Paths.get(FILE_NAME), getData(),
                    StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Error writing settings");
            e.printStackTrace();
        }
    }

    private static List<String> getData() {
        List<String> data = new ArrayList<>(SETTINGS.size());
        for (String key : SETTINGS.keySet()) {
            data.add(getProperty(key));
        }

        return data;
    }

    private static void performRead() {
        List<String> data;
        try {
            if (Files.exists(Paths.get(FILE_NAME))) {
                data = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
            } else {
                data = Collections.emptyList();
            }
        } catch (IOException e) {
            System.out.println("Error reading settings");
            e.printStackTrace();
            return;
        }

        for (String line : data) {
            int i = line.indexOf("=");
            if (i > 0) {
                String key = line.substring(0, i + 1);
                String value = line.substring(i + 1);

                if (isAllowedKey(key)) {
                    SETTINGS.put(key, value);
                }
            }
        }
    }

    private static boolean isAllowedKey(String key) {
        return SETTINGS.containsKey(key) || key.contains(SETTINGS_PROPERTY_STATE_SAVE);
    }

    private static void init() {
        set(SETTINGS_PROPERTY_HEIGHT, "");
        set(SETTINGS_PROPERTY_WIDTH, "");
        set(SETTINGS_PROPERTY_MAXIMIZED, "");
        set(SETTINGS_PROPERTY_MDF, "");
        set(SETTINGS_PROPERTY_F, "");
    }

    private static void set(String key, String value) {
        SETTINGS.put(key, value);
    }

    private static String get(String key) {
        return SETTINGS.get(key);
    }

    private static String getProperty(String key) {
        return key + get(key);
    }

    private static double getDouble(String key) {
        String w = get(key);
        if (w == null || "".equals(w)) {
            return 0;
        }
        return Double.valueOf(w);
    }

    private static boolean getBoolean(String key) {
        String w = get(key);
        if (w == null || "".equals(w)) {
            return false;
        }
        return Boolean.valueOf(w);
    }

    private static void setDouble(String key, double value) {
        set(key, Double.valueOf(value).toString());
    }

    private static void setBoolean(String key, boolean value) {
        set(key, Boolean.valueOf(value).toString());
    }

    private static void scheduleSave() {
        HT.kill(SAVE_DELAY_NAME).to(SAVE_DELAY, SAVE_DELAY_NAME).onComplete(Settings::performSave);
    }
}
