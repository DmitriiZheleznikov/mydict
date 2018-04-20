package md.textanalysis.helper;

/**
 * Helps to find examples that contains 'word' in text
 */
public class ExamplesHelper {
    private static final int COUNT_SYMBOLS_SIDE = 125;

    /**
     * Helps to find examples that contains 'word' in text
     * @param word - worrd in lower case
     * @param num - number of occurrence
     * @param lowerText - text in lower case to search
     * @param rawText - text
     * @return part of "rawText" containing "word"
     */
    public static String get(String word, int num, String lowerText, String rawText) {
        return getExample(findIndexWord(word, num, lowerText), rawText);
    }

    private static int findIndexWord(String word, int num, String lowerText) {
        int indx = -1*COUNT_SYMBOLS_SIDE;
        for (int i = 0; i < num; i++) {
            indx = lowerText.indexOf(word, indx+COUNT_SYMBOLS_SIDE);
            if (indx == -1) break;
        }

        return indx;
    }

    private static String getExample(int indx, String text) {
        if (indx == -1) return null;

        return text.substring(getLeftBoundary(indx), getRightBoundary(indx, text.length())).replaceAll("([\r\n])", " ");
    }

    private static int getLeftBoundary(int indx) {
        if (indx > COUNT_SYMBOLS_SIDE) {
            return indx - COUNT_SYMBOLS_SIDE;
        }
        return 0;
    }

    private static int getRightBoundary(int indx, int textLen) {
        if ((indx + COUNT_SYMBOLS_SIDE) > textLen) {
            return textLen;
        }
        return indx + COUNT_SYMBOLS_SIDE;
    }
}
