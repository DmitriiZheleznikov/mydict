package md.textanalysis.totextconverter.impl.html;

/**
 * Simple representation of XML-tag<br>
 * Requires pre-process (remove of extra spaces)<br>
 * Following string is incorrect: "&lt;   tag_name ...&gt;" (extra spaces between "&lt;" and "tag_name")<br>
 * Following string is correct: "&lt;tag_name ...&gt;"
 */
public class Tag {
    private static final String TAG_NAME_PATTERN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_1234567890";

    public static Tag NULL = new Tag("");

    private String name;
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
        this.name = calcName(tag);
    }

    private String calcName(String tag) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < tag.length(); i++) {
            if (tag.charAt(i) == '<' || tag.charAt(i) == '/') continue;
            if (TAG_NAME_PATTERN.indexOf(tag.charAt(i)) == -1) break;
            name.append(tag.charAt(i));
        }
        return name.toString();
    }

    public String getName() {
        return name;
    }

    public String getNameLowerCase() {
        return name != null ? name.toLowerCase() : null;
    }

    public String getTag() {
        return tag;
    }

    public boolean isOpen() {
        return tag.charAt(1) != '/';
    }

    public boolean isClose() {
        return tag.startsWith("</") || tag.endsWith("/>");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag1 = (Tag) o;

        return tag != null ? tag.equals(tag1.tag) : tag1.tag == null;
    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }

    @Override
    public String toString() {
        return tag;
    }
}