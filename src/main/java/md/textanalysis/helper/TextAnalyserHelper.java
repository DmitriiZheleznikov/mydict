package md.textanalysis.helper;

import md.textanalysis.helper.root.IrregularVerbHelper;
import md.textanalysis.helper.root.RootFinderHelper;
import md.textanalysis.helper.root.SpecialCasesHelper;
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

    public static String getRoot(String word) {
        String key = null;

        String specialCase = SpecialCasesHelper.get(word);
        if (specialCase != null) {
            key = specialCase;
        }

        if (key == null) {
            String irrVerbForm1 = IrregularVerbHelper.get(word);
            if (irrVerbForm1 != null) {
                key = irrVerbForm1;
            }
        }

        if (key == null) {
            key = RootFinderHelper.get(word);
        }

        return key;
    }
}
