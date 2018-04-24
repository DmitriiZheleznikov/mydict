package md.textanalysis.helper;

import md.textanalysis.totextconverter.LinesToTextConverterFactory;

import java.util.List;

/**
 * Useful functions for text analysis
 */
public class TextAnalyserHelper {
    public static String convertToString(String ext, List<String> rawLinesToAnalyse) {
        if (ext == null || ext.length() == 0) {
            ext = "txt";
        }
        return LinesToTextConverterFactory.get(ext, rawLinesToAnalyse).perform();
    }

    public static String convertToLowerCase(String textToConvert) {
        return textToConvert.toLowerCase();
    }

    public static String convertToLettersOnly(String textToConvert) {
        return textToConvert.replaceAll("([^a-z'-])", " ");
    }

    public static String convertToSingleSpaces(String textToConvert) {
        return textToConvert.replaceAll(" +", " ");
    }
}
