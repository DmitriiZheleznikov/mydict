package md.textanalysis.totextconverter.impl.html.tag;

import java.util.List;

/**
 * Helps to find tags in particular line
 */
public class SearchTagTagHelperImpl {

    /** Find any first tag */
    public static SearchTagResult searchTag(String line, int startIndex) {
        int startPos = line.indexOf("<", startIndex);
        if (startPos == -1) return SearchTagResult.NULL;

        int endPos = line.indexOf(">", startPos)+1;

        return new SearchTagResult(startPos, endPos, line.substring(startPos, endPos));
    }

    /** Find any first tag, but with list of exceptions */
    public static SearchTagResult searchTagWithExceptions(String line, int startIndex, List<String> except) {
        SearchTagResult tag = searchTag(line, startIndex);
        if (tag.isFound()) {
            if (except.contains(tag.getTag().getNameLowerCase())) {
                return searchTagWithExceptions(line, tag.getTagEndPos(), except);
            }
        }
        return tag;
    }

    /** Find first tag by name */
    public static SearchTagResult searchTag(String line, int startIndex, String tagName) {
        if (tagName == null || tagName.length() == 0) return searchTag(line, startIndex);

        SearchTagResult tag = searchTag(line, startIndex);
        if (tag.isFound()) {
            if (!tagName.equals(tag.getTag().getNameLowerCase())) {
                return searchTag(line, tag.getTagEndPos(), tagName);
            }
        }
        return tag;
    }

}
