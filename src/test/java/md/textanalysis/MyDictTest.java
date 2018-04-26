package md.textanalysis;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyDictTest {
    private static final Set<String> EXPECTED = new HashSet<>();
    static {
        EXPECTED.add("test");
        EXPECTED.add("ssf");
        EXPECTED.add("sfsd-fsd");
        EXPECTED.add("snsf'kk sflkjsf");
    }

    @Test
    void init() throws IOException {
        MyDict md = new MyDict(new File("src/test/java/resources/MyDictTest.file.txt"));
        md.init();
        assertEquals(EXPECTED, md.getDict());
    }

}