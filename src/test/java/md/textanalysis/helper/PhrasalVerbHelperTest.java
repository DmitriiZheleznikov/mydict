package md.textanalysis.helper;

import md.textanalysis.helper.root.PhrasalVerbHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhrasalVerbHelperTest {
    @Test
    void get() throws IOException, URISyntaxException {
        String expected = "break down";
        String actual = PhrasalVerbHelper.get("broken", "how did you find this broken thing down sir miss");
        assertEquals(expected, actual);
    }

}