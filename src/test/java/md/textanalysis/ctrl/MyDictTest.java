package md.textanalysis.ctrl;

import md.textanalysis.text.analyse.AnalyserFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyDictTest {
    MyDict md;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        AnalyserFacade.init();
        md = new MyDict(new File("src/test/java/resources/MyDictTest.file.txt"));
        md.init();
    }

    @Test
    void add() {
        md.add("added");
        assertEquals(true, md.containsRoot("add"));
    }

    @Test
    void remove() {
        md.remove("break");
        assertEquals(false, md.containsRoot("break"));
    }

    @Test
    void containsRoot() {
        assertEquals(true, md.containsRoot("break"));
        assertEquals(true, md.containsRoot("get a load of"));
        assertEquals(false, md.containsRoot("get load of"));
    }

}