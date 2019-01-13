package md.textanalysis.text.converter.state;

import md.textanalysis.utils.WordsTokenizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Fb2ConverterStateMachineTest {
    //I know it's not good to involve here WordsTokenizer, but it's useful here
    @Test
    void step() {
        Fb2ConverterStateMachine state = new Fb2ConverterStateMachine();
        StringBuilder sb = new StringBuilder();
        WordsTokenizer wt = new WordsTokenizer(getOriginalText());
        while (wt.hasMoreTokens()) {
            String word = wt.nextToken();
            if (state.step(word)) {
                sb.append(word).append(" ");
            }
        }
        assertEquals("test 1 . text- 2 text 0 text 6111 222 ", sb.toString());
    }

    private static String getOriginalText() {
        return "test1. < genre / >< br>text-2 &amp;<genre   >text-1</ genre"
                + "  >text0 <genre>text1<genre>te"
                + "xt2</genre  >text 3 <genre>text 4</ genre>"
                + "text 5</genre> text6"
                + "111<genre>text 7<genre>text 7.1</genre></genre>222</ genre><publish-info></publish-info>";
    }
}