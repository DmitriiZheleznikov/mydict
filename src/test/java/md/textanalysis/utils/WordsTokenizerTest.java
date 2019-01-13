package md.textanalysis.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsTokenizerTest {
    @Test
    void nextToken() {
        String testText = "This is father’s text, \"MyDictionary\" file (words you know)< name_1><name-2 ><name3>name_4&amp;&#8482;";//do  not do";
        String[] expected = new String[31];
        expected[0] = "This";
        expected[1] = "is";
        expected[2] = "father’s";
        expected[3] = "text";
        expected[4] = ",";
        expected[5] = "\"MyDictionary\"";
        expected[6] = "file";
        expected[7] = "(";
        expected[8] = "words";
        expected[9] = "you";
        expected[10] = "know";
        expected[11] = ")";
        expected[12] = "<";
        expected[13] = "name_1";
        expected[14] = ">";
        expected[15] = "<";
        expected[16] = "name-2";
        expected[17] = ">";
        expected[18] = "<";
        expected[19] = "name3";
        expected[20] = ">";
        expected[21] = "name";
        expected[22] = "_";
        expected[23] = "4";
        expected[24] = "&";
        expected[25] = "amp";
        expected[26] = ";";
        expected[27] = "&";
        expected[28] = "#";
        expected[29] = "8482";
        expected[30] = ";";
//        expected[31] = "don't";
//        expected[32] = "do";

        int i = 0;
        WordsTokenizer wt = new WordsTokenizer(testText);
        while (wt.hasMoreTokens()) {
            assertEquals(expected[i++], wt.nextToken());
        }
        assertEquals(expected.length, i);
    }

    @Test
    void nextTokenNoHTML() {
        String testText = "< name_1>";
        String[] expected = new String[5];
        expected[0] = "<";
        expected[1] = "name";
        expected[2] = "_";
        expected[3] = "1";
        expected[4] = ">";

        int i = 0;
        WordsTokenizer wt = new WordsTokenizer(testText, false);
        while (wt.hasMoreTokens()) {
            assertEquals(expected[i++], wt.nextToken());
        }
        assertEquals(expected.length, i);
    }
}