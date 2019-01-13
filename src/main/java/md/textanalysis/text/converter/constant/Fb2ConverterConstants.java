package md.textanalysis.text.converter.constant;

import java.util.ArrayList;
import java.util.List;

public class Fb2ConverterConstants {
    /** Tags in FB2 file that should be removed with content inside them */
    public static final List<String> TAGS_TO_BE_ERASED_WITH_CONTENT = new ArrayList<>();

    static {
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("myheader");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("description");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("publish-info");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("history");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("program-used");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("date");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("author");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("binary");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("id");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("genre");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("program-used");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("lang");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("image");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("coverpage");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("src-lang");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("src-url");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("src-ocr");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("version");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("history");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("publisher");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("isbn");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("document-info");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("publish-info");
        TAGS_TO_BE_ERASED_WITH_CONTENT.add("a");
    }


    private Fb2ConverterConstants() {
    }
}
