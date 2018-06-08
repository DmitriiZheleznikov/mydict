package md.textanalysis.converter.impl;

import md.textanalysis.text.analyse.AnalyserFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyDictConverterTest {
    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        AnalyserFacade.init();
    }

    @Test
    void getResult() {
        MyDictConverter converter = new MyDictConverter(getRaw());
        converter.perform();

        assertEquals(getExpected(), converter.getResult());
    }

    List<String> getExpected() {
        List<String> list = new ArrayList<>(4);
        list.add("hide");
        list.add("broken");
        list.add("save up");
        list.add("get a load of");
        return list;
    }

    List<String> getRaw() {
        List<String> list = new ArrayList<>(4);
        list.add("  hide");
        list.add("broken  ");
        list.add("Save   Up");
        list.add("Get a   load of");
        return list;
    }
}