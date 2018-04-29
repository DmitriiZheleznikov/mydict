package md.textanalysis.helper;

import md.textanalysis.helper.root.PhrasalVerbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhrasalVerbHelperTest {
    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        PhrasalVerbHelper.init();
    }

    @Test
    void get() throws IOException, URISyntaxException {
        String word = "broken";
        String text = "how did you find this broken thing down sir miss";
        String expected = "break down";
        String actual = PhrasalVerbHelper.get(text.indexOf(word), null, word, text);
        assertEquals(expected, actual);

        word = "save";
        text = "please save it up";
        expected = "save up";
        actual = PhrasalVerbHelper.get(text.indexOf(word), null, word, text);
        assertEquals(expected, actual);
    }

}