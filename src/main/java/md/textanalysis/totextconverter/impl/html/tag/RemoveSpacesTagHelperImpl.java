package md.textanalysis.totextconverter.impl.html.tag;

/**
 * This class contains only 1 function for removing almost all spaces inside tags
 * Input:  "  > <   tag  > < / tag > </  tag> <tag / > <   \n"
 * Step 1: "  > <tag  > </ tag > </  tag> <tag / > <\n"
 * Step 2: "  > <tag  > </tag > </tag> <tag /> <\n"
 * Step 3: "> <tag> </tag> </tag> <tag /> <\n"
 */
public class RemoveSpacesTagHelperImpl {
    public static String removeSpacesInsideTag(String line) {
        String lineResult = removeSpacesInsideTagStep1(line);
        lineResult = removeSpacesInsideTagStep2(lineResult);
        lineResult = removeSpacesInsideTagStep3(lineResult);

        return lineResult;
    }

    private static String removeSpacesInsideTagStep3(String line) {
        StringBuilder lineResult = new StringBuilder();
        boolean tagStarted = false;

        for (int i = line.length()-1; i >= 0; i--) {
            char nxtChar = line.charAt(i);
            if (nxtChar == '>') {
                tagStarted = true;
            } else {
                if (tagStarted && nxtChar == ' ') continue;
                tagStarted = false;
            }
            lineResult.append(nxtChar);
        }

        return lineResult.reverse().toString();
    }

    private static String removeSpacesInsideTagStep2(String line) {
        StringBuilder lineResult = new StringBuilder();
        boolean tagStarted = false;
        boolean slash = false;

        for (int i = 0; i < line.length(); i++) {
            char nxtChar = line.charAt(i);
            if (nxtChar == '<') {
                tagStarted = true;
            } else if (tagStarted && nxtChar == '/') {
                slash = true;
            } else {
                if (slash && nxtChar == ' ') continue;
                tagStarted = false;
                slash = false;
            }
            lineResult.append(nxtChar);
        }

        return lineResult.toString();
    }

    private static String removeSpacesInsideTagStep1(String line) {
        StringBuilder lineResult = new StringBuilder();
        boolean tagStarted = false;

        for (int i = 0; i < line.length(); i++) {
            char nxtChar = line.charAt(i);
            if (nxtChar == '<') {
                tagStarted = true;
            } else {
                if (tagStarted && nxtChar == ' ') continue;
                tagStarted = false;
            }
            lineResult.append(nxtChar);
        }

        return lineResult.toString();
    }
}
