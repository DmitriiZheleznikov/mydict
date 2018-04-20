package md.textanalysis.totextconverter.impl.html.tag;

import md.textanalysis.totextconverter.impl.html.Tag;

/**
 * Result of search tag in line: tag itself, start and end pos in line where search was performed
 */
public class SearchTagResult {
    public static SearchTagResult NULL = new SearchTagResult(-1, -1, Tag.NULL);

    private int tagStartPos;
    private int tagEndPos;
    private Tag tag;

    private SearchTagResult(int tagStartPos, int tagEndPos, Tag tag) {
        this.tagStartPos = tagStartPos;
        this.tagEndPos = tagEndPos;
        this.tag = tag;
    }

    public SearchTagResult(int tagStartPos, int tagEndPos, String tag) {
        this.tagStartPos = tagStartPos;
        this.tagEndPos = tagEndPos;
        this.tag = new Tag(tag);
    }

    public int getTagStartPos() {
        return tagStartPos;
    }

    public int getTagEndPos() {
        return tagEndPos;
    }

    public Tag getTag() {
        return tag;
    }

    public boolean isFound() {
        return tagStartPos != tagEndPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchTagResult)) return false;

        SearchTagResult that = (SearchTagResult) o;

        if (tagStartPos != that.tagStartPos) return false;
        if (tagEndPos != that.tagEndPos) return false;
        return tag != null ? tag.equals(that.tag) : that.tag == null;
    }

    @Override
    public int hashCode() {
        int result = tagStartPos;
        result = 31 * result + tagEndPos;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchTagResult{" +
                "tagStartPos=" + tagStartPos +
                ", tagEndPos=" + tagEndPos +
                ", tag=" + tag +
                '}';
    }
}
