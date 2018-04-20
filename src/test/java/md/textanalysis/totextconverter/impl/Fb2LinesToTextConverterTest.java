package md.textanalysis.totextconverter.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Fb2LinesToTextConverterTest {
    private static final List<String> INPUT_LIST = new ArrayList<>(5);
    private static final List<String> PRE_LIST_EXPECTED = new ArrayList<>(4);
    private static final String EXPECTED = " text-2 text0   text6 111222";

    static {
        INPUT_LIST.add("< genre / >< br>text-2 <genre   >text-1</ genre");
        INPUT_LIST.add("  >text0 <genre>text1<genre>te");
        INPUT_LIST.add("xt2</genre  >text 3 <genre>text 4</ genre>");
        INPUT_LIST.add("text 5</genre> text6");
        INPUT_LIST.add("111<genre>text 7<genre>text 7.1</genre></genre>222</ genre>");

        PRE_LIST_EXPECTED.add("<genre /><br>text-2 <genre>text-1</genre>text0 <genre>text1<genre>te");
        PRE_LIST_EXPECTED.add("xt2</genre>text 3 <genre>text 4</genre>");
        PRE_LIST_EXPECTED.add("text 5</genre> text6");
        PRE_LIST_EXPECTED.add("111<genre>text 7<genre>text 7.1</genre></genre>222</genre>");
    }

    @Test
    void preProcessList() {
        Fb2LinesToTextConverter converter = new Fb2LinesToTextConverter(INPUT_LIST);
        assertEquals(PRE_LIST_EXPECTED, converter.preProcessList(INPUT_LIST));
    }

    @Test
    void perform() {
        Fb2LinesToTextConverter converter = new Fb2LinesToTextConverter(INPUT_LIST);
        assertEquals(EXPECTED, converter.perform());
    }


}