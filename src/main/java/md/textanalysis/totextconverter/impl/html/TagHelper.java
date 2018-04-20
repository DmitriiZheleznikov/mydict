package md.textanalysis.totextconverter.impl.html;

import md.textanalysis.totextconverter.impl.html.tag.RemoveSpacesTagHelperImpl;
import md.textanalysis.totextconverter.impl.html.tag.SearchTagResult;
import md.textanalysis.totextconverter.impl.html.tag.SearchTagTagHelperImpl;

import java.util.List;

public class TagHelper {

    public static SearchTagResult searchTag(String line, int startIndex, String tagName) {
        return SearchTagTagHelperImpl.searchTag(line, startIndex, tagName);
    }

    /**
     * Searches first tag in the line
     * @param line String line by which search is performed
     * @param startIndex start index
     * @return start/end position of tag in line + Tag itself
     */
    public static SearchTagResult searchTag(String line, int startIndex) {
        return SearchTagTagHelperImpl.searchTag(line, startIndex);
    }

    /**
     * Removes all tags without their content<br>
     * Input:  &lt;b&gt;test1&lt;/b&gt; &lt;pre&gt;test2&lt;/pre&gt;
     * Output: test1 &lt;pre&gt;test2&lt;/pre&gt;
     * in case "pre" is in exceptions
     * @param line
     * @param except
     * @return
     */
    public static String deleteTags(String line, List<String> except) {
        return deleteTagsRecursive(line, except, 0);
    }

    private static String deleteTagsRecursive(String line, List<String> except, int startIndex) {
        String lineResult = line;
        SearchTagResult tag = SearchTagTagHelperImpl.searchTagWithExceptions(line, startIndex, except);
        if (tag.isFound()) {
            lineResult = lineResult.substring(0, tag.getTagStartPos())
                    + lineResult.substring(tag.getTagEndPos());
            lineResult = deleteTagsRecursive(lineResult, except, tag.getTagStartPos());
        }

        return lineResult;
    }

    /**
     * Checks if all tags closed in this line
     * Example 1, true:  &lt;pre&gt;test2&lt;/pre&gt;
     * Example 2, false: &lt;pre&gt;test2&lt;/pre
     * @param line
     * @return
     */
    public static boolean isAllTagsClosed(String line) {
        return countChar(line, '<') == countChar(line, '>');
    }

    public static int countChar(String line, char ch) {
        int cnt = 0;

        for (int i = 0; i < line.length(); i++) {
            char nxtChar = line.charAt(i);
            if (nxtChar == ch) cnt++;
        }

        return cnt;
    }

    public static String removeSpacesInsideTag(String line) {
        return RemoveSpacesTagHelperImpl.removeSpacesInsideTag(line);
    }
}
