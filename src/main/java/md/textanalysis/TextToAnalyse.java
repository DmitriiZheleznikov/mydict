package md.textanalysis;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.helper.TextAnalyserHelper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextToAnalyse {
    private File file;
    private String textRaw;
    private String textLowerNoDelimiters;

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

        List<String> rawLinesToAnalyse = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        progressFunction.step(1);

        textRaw = TextAnalyserHelper.convertToString(TextAnalyserHelper.getFileExt(file), rawLinesToAnalyse);
        progressFunction.step(2);

        textLowerNoDelimiters = TextAnalyserHelper.convertToLowerCase(textRaw);
        progressFunction.step(3);

        textLowerNoDelimiters = TextAnalyserHelper.convertToLettersOnly(textLowerNoDelimiters);
        progressFunction.step(4);
    }

    public String getTextRaw() {
        return textRaw;
    }

    public String getTextLowerNoDelimiters() {
        return textLowerNoDelimiters;
    }
}
