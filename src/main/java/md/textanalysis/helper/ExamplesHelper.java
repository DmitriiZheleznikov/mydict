package md.textanalysis.helper;

/**
 * Helps to find examples that contains 'word' in text
 */
public class ExamplesHelper {
    private static final int COUNT_SYMBOLS_SIDE = 125;

    public static String get(int indx, String text) {
        return getExample(indx, text);
    }

    public static String getExample(int indx, String text) {
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
