package md.textanalysis.totextconverter.impl.html;

import md.textanalysis.totextconverter.impl.html.tag.SearchTagResult;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TagHelperTest {
    @Test
    void searchTagAny() {
        String example = "aaa <aaa i='2'>33</aaa> bbb <bbb> ccc";
        SearchTagResult expected = new SearchTagResult(4, 15, "<aaa i='2'>");

        assertEquals(expected, TagHelper.searchTag(example, 0));
    }

    @Test
    void searchTagParticular() {
        String example = "aaa <aaa i='2'>33</aaa> bbb <bbb> ccc";
        SearchTagResult expected = new SearchTagResult(28, 33, "<bbb>");

        assertEquals(expected, TagHelper.searchTag(example, 0, "bbb"));
    }

    @Test
    void deleteTags() {
        String expected = "test1 <pre>test2</pre>";
        String example  = "<b>test1</b> <pre>test2</pre>";

        assertEquals(expected, TagHelper.deleteTags(example, Collections.singletonList("pre")));
    }

    @Test
    void isAllTagsClosed() {
        assertEquals(true,  TagHelper.isAllTagsClosed("<b>test1</b> <pre>test2</pre>"));
        assertEquals(false, TagHelper.isAllTagsClosed("<b>test1</b> <pre>test2</pre"));
    }

    @Test
    void removeSpacesInsideTag() {
        String expected = " <test> bb b </test> <test  />";
        String example  = " <  test  > bb b <  /  test  > <  test  /  >";

        assertEquals(expected, TagHelper.removeSpacesInsideTag(example));
    }

}