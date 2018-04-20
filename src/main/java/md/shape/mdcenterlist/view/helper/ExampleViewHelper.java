package md.shape.mdcenterlist.view.helper;

import heli.component.shape.list.centerlist.ext.CText;
import heli.component.shape.list.centerlist.view.CListLineView;
import md.shape.mdcenterlist.view.MDListLineView;

import java.util.ArrayList;

public class ExampleViewHelper {
    private static int EXAMPLE_LINE_SIZE = -1;

    public static void resetCache() {
        EXAMPLE_LINE_SIZE = -1;
    }

    public static void setExampleText(CText tExample, String text, double size) {
        if (text.length() == 0) {
            tExample.setText("");
            return;
        }

        if (EXAMPLE_LINE_SIZE > 0) {
            tExample.setText(getExampleText(text, EXAMPLE_LINE_SIZE));
            return;
        }

        String textToCheck = "...Scientists have changed their estimation of when adolescence ends and adulthood starts. Adolescence is that difficult time when children develop into adults. It was traditionally thought to coincide with our teenage years. However, scientists from the University of Melbourne have redefined this. They say it can start at the age of 10 and continue to the age of 24";

        tExample.setText(textToCheck);
        if (tExample.getWidth() > 0) {

            int i;
            for (i = 100; i < 300; i++) {
                tExample.setText(textToCheck.substring(0, i));
                if (tExample.getWidth() > size) {
                    break;
                }
            }

            EXAMPLE_LINE_SIZE = i - 1;
            tExample.setText(getExampleText(text, EXAMPLE_LINE_SIZE));
        } else {
            tExample.setText(getExampleText(text, 135));
        }
    }

    public static String getExampleText(ArrayList<String> examples, int n) {
        if (examples == null) return "...";
        if (examples.size() < (n + 1)) {
            if (n == 0) return "..."; else return "";
        }
        return examples.get(n) == null ? "..." : examples.get(n);
    }

    private static String getExampleText(String example, int lineSize) {
        if (example == null) return "...";

        if (example.length() <= lineSize) {
            return "..." + example + "...";
        }

        String firstLine = getFirstLineOfExampleBrokenByWord(example, lineSize);
        return "..." + firstLine + "\n    " + example.substring(firstLine.length(), getRightBoundary(example)) + "...";
    }

    private static String getFirstLineOfExampleBrokenByWord(String example, int lineSize) {
        int i;
        for (i = lineSize; i > 0; i--) {
            char ch = example.charAt(i);
            if (!isLetter(ch)) break;
        }

        return example.substring(0, i);
    }

    //I decided it's faster than RegExp "([^A-Za-z'-])", but not tested it
    private static boolean isLetter(int code) {
        if (code >= 65 && code <= 90) return true; //A-Z
        if (code >= 97 && code <= 122) return true; //a-z
        if (code == 39 || code == 45) return true; //'-
        return false;
    }

    private static int getRightBoundary(String example) {
        int max = 300;
        if (EXAMPLE_LINE_SIZE > 0) {
            max = EXAMPLE_LINE_SIZE*2 - 20;
        }
        if (example.length() >= max) {
            return max;
        }
        return example.length();
    }
}
