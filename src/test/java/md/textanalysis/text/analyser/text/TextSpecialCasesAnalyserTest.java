package md.textanalysis.text.analyser.text;

import md.textanalysis.text.modifier.TextSpecialCasesModifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

class TextSpecialCasesAnalyserTest {

    private TextSpecialCasesModifier modifier;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        modifier = new TextSpecialCasesModifier();
        modifier.init();
        System.out.println(modifier.toString());
    }

    @Test
    void process() {
        //System.out.println(analyser.toString());
    }
}