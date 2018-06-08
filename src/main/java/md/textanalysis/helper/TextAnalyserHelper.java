package md.textanalysis.helper;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.converter.ITextConverter;
import md.textanalysis.converter.TextConverterFactory;
import md.textanalysis.text.analyse.AnalyserFacade;
import md.textanalysis.text.element.word.AbstractWord;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

    public static String getFileExt(File file) {
        String fileName = file.getName();
        int iDot = fileName.lastIndexOf(".");
        if (iDot == -1 || iDot == fileName.length()) {
            return "txt";
        }
        return fileName.substring(iDot+1);
    }

    public static List<AbstractWord> convertTextToWords(String ext, List<String> rawLinesToAnalyse) {
        if (ext == null || ext.length() == 0) {
            ext = "txt";
        }
        ITextConverter converter = TextConverterFactory.get(ext, rawLinesToAnalyse);
        converter.perform();
        return converter.getResult();
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
        return entity.matches("[\"A-Za-z'’-]+");
    }

    public static boolean isNumber(String entity) {
        return entity.matches("[0-9]+");
    }

    public static String convertToSingleSpaces(String textToConvert) {
        return textToConvert.replaceAll(" +", " ");
    }

    public static String getRoot(String word) {
        return AnalyserFacade.getWordRoot(word);
    }
}
