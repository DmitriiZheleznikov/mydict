package md.textanalysis.utils;

import java.util.Comparator;

public class PhrasesComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int cntWords1 = countWords(o1);
        int cntWords2 = countWords(o2);

        if (cntWords1 == cntWords2) {
            return o1.compareTo(o2);
        } else {
            return cntWords1 > cntWords2 ? 1 : -1;
        }
    }

    int countWords(String word) {
        if (word == null) return 0;

        int cnt = 0;
        boolean wordFound = false;
        for (int i = 0; i < word.length(); i++) {
            if (!(' ' == word.charAt(i))) {
                if (!wordFound) {
                    cnt++;
                    wordFound = true;
                }
            } else {
                wordFound = false;
            }
        }

        return cnt;
    }
}
