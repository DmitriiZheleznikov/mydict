package md.layout.content.control.helper;

import heli.component.shape.list.centerlist.model.CListLineModel;
import md.settings.Settings;
import md.shape.mdcenterlist.MDList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentHelper {
    public static final String POSTFIX_SAVED_STATE_FILE_NAME = ".mydict.save";
    public static final String FILE_NAME_POSTFIX_FINISH = ".mydict.txt";

    public static void saveState(File fileToAnalyse, MDList list) throws IOException {
        Settings.saveState(fileToAnalyse.getAbsolutePath(), list.getModel().getCurrentLine().getText());
    }

    public static void finish(File fileToAnalyse, MDList list) throws IOException {
        List<String> content = new ArrayList<>(list.getModel().getLength());
        CListLineModel line = list.getModel().getFirstLine();
        do {
            if (line.isEnabled()) content.add(line.getText());
            line = line.next();
        } while (line != null);

        Files.write(Paths.get(fileToAnalyse.getAbsolutePath() + FILE_NAME_POSTFIX_FINISH), content,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }

    public static String checkSaveSate(File fileToAnalyse) {
        return Settings.getState(fileToAnalyse.getAbsolutePath());
    }

    public static void removeState(File fileToAnalyse) {
        Settings.removeState(fileToAnalyse.getAbsolutePath());
    }
}
