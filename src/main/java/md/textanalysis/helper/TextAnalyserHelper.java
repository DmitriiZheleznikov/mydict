package md.textanalysis.helper;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.converter.ITextConverter;
import md.textanalysis.text.converter.TextConverterFactory;
import md.textanalysis.text.element.word.AbstractWord;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Useful functions for text analysis
 */
public class TextAnalyserHelper {

    public static void init() throws IOException, URISyntaxException {
        init(IProgressFunction.NULL);
    }

    public static void init(IProgressFunction progressFunction) throws IOException, URISyntaxException {
        AnalyserFacade.init(progressFunction);
    }

    public static int calcProgressStep(int count, int workLeftInPercent) {
        return workLeftInPercent == 0 ? 0 : (count / workLeftInPercent);
    }

    public static void increaseProgress(int i, int progressStepEach, IProgressFunction progressFunction) {
        if (progressFunction == null || progressStepEach <= 0) return;
        if (i % progressStepEach == 0) progressFunction.step();
    }

    public static String getFileExt(File file) {
        String fileName = file.getName();
        int iDot = fileName.lastIndexOf(".");
        if (iDot == -1 || iDot == fileName.length()) {
            return "txt";
        }
        return fileName.substring(iDot+1);
    }

    public static List<AbstractWord> convertToWords(String ext, List<String> rawLinesToAnalyse) {
        ITextConverter converter = TextConverterFactory.get(ext, rawLinesToAnalyse);
        converter.perform();
        return converter.getResult();
    }

    public static List<AbstractWord> convertTxtLineToWordsArray(String rawLineToAnalyse) {
        ITextConverter converter = TextConverterFactory.get("txt", Collections.singletonList(rawLineToAnalyse));
        converter.perform(new ArrayList());
        return converter.getResult();
    }

    public static void initWords(List<AbstractWord> list) {
        for (AbstractWord entity : list) {
            entity.init();
        }
    }

    public static String convertToLowerCase(String textToConvert) {
        return textToConvert.toLowerCase();
    }

    public static String convertToLettersOnly(String textToConvert) {
        return textToConvert.replaceAll("([^a-z'-])", " ");
    }

    public static String trimIfNeeded(String line) {
        if (line == null || line.isEmpty()) return line;
        if (line.charAt(0) == ' ' || line.charAt(line.length()-1) == ' ') {
            return line.trim();
        }
        return line;
    }

    public static String lowerCaseIfNeeded(String line) {
        if (line == null || line.isEmpty()) return line;
        if (containsUpper(line)) {
            return line.toLowerCase();
        }
        return line;
    }

    public static String singleSpacesIfNeeded(String line) {
        if (line == null || line.isEmpty()) return line;
        if (containsExtraSpaces(line)) {
            return convertToSingleSpaces(line);
        }
        return line;
    }

    public static boolean containsUpper(String entity) {
        return entity.matches(".*[A-Z].*");
    }

    public static boolean containsExtraSpaces(String entity) {
        return entity.matches(".*  .*");
    }

    public static boolean isWord(String entity) {
        return entity.matches("[-_\"A-Za-z'’]+");
    }

    public static boolean isNumber(String entity) {
        return entity.matches("[_0-9]+");
    }

    public static String convertToSingleSpaces(String textToConvert) {
        return textToConvert.replaceAll(" +", " ");
    }

    public static String getRoot(String word) {
        return AnalyserFacade.getWordRoot(word);
    }
}
