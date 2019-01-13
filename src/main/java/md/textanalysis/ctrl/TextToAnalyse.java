package md.textanalysis.ctrl;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.analyser.text.TextOrderNumberSetter;
import md.textanalysis.text.analyser.text.TextPhrasesCreator;
import md.textanalysis.text.element.word.AbstractWord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextToAnalyse {
    private File file;
    private List<String> rawLinesToAnalyse;
    List<AbstractWord> entities;

    public TextToAnalyse(File file) {
        this.file = file;
    }

    public void init() throws IOException {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("File " + file.getName() + " doesn't exist");
        }

        rawLinesToAnalyse = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        progressFunction.step();
    }

    public void prepare(IProgressFunction progressFunction) throws IOException {
        entities = TextAnalyserHelper.convertToWords(TextAnalyserHelper.getFileExt(file), rawLinesToAnalyse);
        progressFunction.step();

        initAllWords(progressFunction);

        AnalyserFacade.modifyTextSpecialCases(entities);
        progressFunction.step();

        new TextPhrasesCreator().perform(entities);
        progressFunction.step();

        new TextOrderNumberSetter().perform(entities);
        progressFunction.step();
    }

    private void initAllWords(IProgressFunction progressFunction) {
        int wordsCount = getEntities().size();
        int progressStepEach = TextAnalyserHelper.calcProgressStep(wordsCount, 25);
        int i = 0;
        for (AbstractWord entity : getEntities()) {
            entity.init();
            TextAnalyserHelper.increaseProgress(i++, progressStepEach, progressFunction);
        }
    }

    public File getFile() {
        return file;
    }

    public List<String> getRawLinesToAnalyse() {
        return rawLinesToAnalyse;
    }

    public List<AbstractWord> getEntities() {
        return entities;
    }
}
