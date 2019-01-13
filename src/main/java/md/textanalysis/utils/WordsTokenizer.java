package md.textanalysis.utils;

public class WordsTokenizer {
    private static final String WORD_PATTERN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-’'\"";
    private static final String TAG_NAME_PATTERN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_1234567890";
    private static final String NUMBER_PATTERN = "1234567890";

    private String rawText;
    private int i;
    private boolean nxtTokenSearchPerformed;
    private String nxtToken;
    private int numOpenTag = 0;
    private int numCloseTag = 0;
    private boolean htmlSupportEnabled;

    public WordsTokenizer(String rawText) {
        this.rawText = rawText;
        this.i = 0;
        this.nxtTokenSearchPerformed = false;
        this.nxtToken = null;
        this.htmlSupportEnabled = true;
    }

    public WordsTokenizer(String rawText, boolean htmlSupportEnabled) {
        this.rawText = rawText;
        this.i = 0;
        this.nxtTokenSearchPerformed = false;
        this.nxtToken = null;
        this.htmlSupportEnabled = htmlSupportEnabled;
    }

    public boolean hasMoreTokens() {
        findNextToken();
        return nxtToken != null;
    }

    public String nextToken() {
        findNextToken();

        nxtTokenSearchPerformed = false;
        String nextToken = nxtToken;
        nxtToken = null;

        return nextToken;
    }

    private void findNextToken() {
        if (nxtTokenSearchPerformed) return;

        nxtToken = null;
        nxtTokenSearchPerformed = true;

        skipSpaces();
        if (i == rawText.length()) return;

        if ('<' == rawText.charAt(i)) numOpenTag++;
        if ('>' == rawText.charAt(i)) numCloseTag++;

        boolean isNum = isNumber(rawText.charAt(i));

        if (getPattern(isNum).indexOf(rawText.charAt(i)) < 0) {
            nxtToken = String.valueOf(rawText.charAt(i++));
        } else {
            StringBuilder sb = new StringBuilder();
            for (; i < rawText.length(); i++) {
                if (getPattern(isNum).indexOf(rawText.charAt(i)) < 0) break;
                sb.append(rawText.charAt(i));
            }
            if (sb.length() > 0) nxtToken = sb.toString();
        }

//        if (CASE_WITH_NOT.containsKey(nxtToken)) {
//            int fNOT = checkFollowingNOT();
//            if (fNOT > -1) {
//                i = fNOT;
//                nxtToken = CASE_WITH_NOT.get(nxtToken);
//            }
//        }
    }

//    private int checkFollowingNOT() {
//        int ii = i;
//        for (; ii < rawText.length(); ii++) {
//            if (rawText.charAt(ii) != ' ') break;
//        }
//
//        if (ii >= rawText.length()) return -1;
//        if (rawText.charAt(ii) == 'N' || rawText.charAt(ii) == 'n') {
//            if (++ii >= rawText.length()) return -1;
//            if (rawText.charAt(ii) == 'O' || rawText.charAt(ii) == 'o') {
//                if (++ii >= rawText.length()) return -1;
//                if (rawText.charAt(ii) == 'T' || rawText.charAt(ii) == 't') {
//                    return ++ii;
//                }
//            }
//        }
//
//        return -1;
//    }

    private boolean isNumber(char ch) {
        return NUMBER_PATTERN.indexOf(ch) >= 0;
    }

//    private String getPatternO(boolean isNumber) {
//        if (isNumber) {
//            return numOpenTag == numCloseTag ? NUMBER_PATTERN : TAG_NAME_PATTERN;
//        }
//        return numOpenTag == numCloseTag ? WORD_PATTERN : TAG_NAME_PATTERN;
//    }

    private String getPattern(boolean isNumber) {
        if (htmlSupportEnabled && numOpenTag != numCloseTag) return TAG_NAME_PATTERN;

        return isNumber ? NUMBER_PATTERN : WORD_PATTERN;
    }

    private void skipSpaces() {
        for (; i < rawText.length(); i++) {
            if (rawText.charAt(i) != ' ') return;
        }
    }
}
