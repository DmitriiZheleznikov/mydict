package md.textanalysis.ctrl;

import heli.htweener.HT;
import heli.htweener.tween.ICallable;
import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.converter.ITextConverter;
import md.textanalysis.text.converter.TextConverterFactory;
import md.textanalysis.utils.PhrasesTreeSet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class MyDict {
    public static final int SCHEDULE_TIME = 3*60000;
    public static final String SCHEDULE_NAME = "mydict_save_time";
    public static final String DEFAULT_FILE_NAME = "MyDictionary.txt";

    private File fileMyDict;
    private Set<String> lines;
    private Map<String, List<String>> roots;

    public MyDict(File fileMyDict) {
        this.fileMyDict = fileMyDict;
    }

    public void init() throws IOException {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) throws IOException {
        if (fileMyDict == null) return;

        if (!fileMyDict.exists()) throw new IllegalArgumentException("File " + fileMyDict.getName() + " doesn't exist");
        List<String> rawLines = Files.readAllLines(Paths.get(fileMyDict.getAbsolutePath()), StandardCharsets.UTF_8);

        ITextConverter converter = TextConverterFactory.get("mydict", rawLines);
        converter.perform();
        initFromOriginalsSet(converter.getResult());

        progressFunction.step();
    }

    private void initFromOriginalsSet(List<String> rawLines) {
        lines = new PhrasesTreeSet();
        roots = new HashMap<>(rawLines.size());

        for (String line : rawLines) {
            add(line);
        }
    }

    public void add(String line) {
        lines.add(line);

        String root = AnalyserFacade.getPhraseRoot(line);
        List<String> originals = roots.computeIfAbsent(root, k -> new ArrayList<>(5));
        originals.add(line);
    }

    public void remove(String line) {
        removeByRoot(AnalyserFacade.getPhraseRoot(line));
    }

    private void removeByRoot(String root) {
        List<String> originals = roots.get(root);

        for (String original : originals) {
            lines.remove(original);
        }

        roots.remove(root);
    }

    public boolean containsOriginal(String original) {
        return containsRoot(AnalyserFacade.getPhraseRoot(original));
    }

    public boolean containsRoot(String root) {
        return roots.containsKey(root);
    }

    public void saveState() throws IOException {
        String filePath = fileMyDict == null ? DEFAULT_FILE_NAME : fileMyDict.getAbsolutePath();

        if (fileMyDict == null) {
            Files.write(Paths.get(filePath), lines, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            fileMyDict = new File(DEFAULT_FILE_NAME);
            init();
        } else {
            Files.write(Paths.get(filePath), lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
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

    @Override
    public String toString() {
        return "MyDict{" +
                "lines=" + lines +
                ", roots=" + roots +
                '}';
    }
}
