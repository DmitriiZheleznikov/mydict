package md.textanalysis;

import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.htweener.HT;
import heli.htweener.tween.ICallable;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.textanalysis.helper.TextAnalyserHelper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/** This is the main class of "My Dictionary" file with list of english words you know.<br>
 * It's able to apply filtration to existing list, loading from file and saving to file by request or schedule.
 */
public class MyDict {
    public static final int SCHEDULE_TIME = 3*60000;
    public static final String SCHEDULE_NAME = "mydict_save_time";
    public static final String DEFAULT_FILE_NAME = "myDict.txt";

    private File fileMyDict;
    private Set<String> dict;

    public MyDict() {
        fileMyDict = null;
    }

    public MyDict(File fileMyDict) {
        this.fileMyDict = fileMyDict;
    }

    public void init() throws IOException {
        dict = new HashSet<>();
        if (fileMyDict == null) return;

        if (!fileMyDict.exists()) throw new IllegalArgumentException("File " + fileMyDict.getName() + " doesn't exist");
        List<String> rawLines = Files.readAllLines(Paths.get(fileMyDict.getAbsolutePath()), StandardCharsets.UTF_8);

        String text = TextAnalyserHelper.convertToString("mydict", rawLines);
        text = TextAnalyserHelper.convertToLowerCase(text);
        text = TextAnalyserHelper.convertToSingleSpaces(text);

        StringTokenizer st = new StringTokenizer(text, ";");
        while (st.hasMoreTokens()) {
            String nxt = st.nextToken().trim();
            if (nxt.length() > 0) {
                dict.add(TextAnalyserHelper.getRoot(nxt));
            }
        }
    }

    public void applyFilterTo(MDListLineModel line) {
        if (line == null) return;

        CListLineModel firstLine = line;
        while (firstLine.prev() != null) firstLine = firstLine.prev();

        do {
            if (dict.contains(TextAnalyserHelper.getRoot(firstLine.getText()))) firstLine.disable();
            firstLine = firstLine.next();
        } while (firstLine != null);
    }

    public void add(String word) {
        dict.add(word);
    }

    public void remove(String word) {
        dict.remove(word);
    }

    public void saveState() throws IOException {
        String filePath = fileMyDict == null ? DEFAULT_FILE_NAME : fileMyDict.getAbsolutePath();

        if (fileMyDict == null) {
            Files.write(Paths.get(filePath), dict, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            fileMyDict = new File(DEFAULT_FILE_NAME);
            init();
        } else {
            Files.write(Paths.get(filePath), dict, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        }
    }

    public void runScheduler(ICallable action) {
        HT.to(SCHEDULE_TIME, SCHEDULE_NAME).onComplete(() -> {
            action.call();
            HT.to(SCHEDULE_TIME, SCHEDULE_NAME).onComplete(() -> runScheduler(action));
        });
    }

    public void stopScheduler() {
        HT.kill(SCHEDULE_NAME);
    }

    public Set<String> getDict() {
        return dict;
    }
}
