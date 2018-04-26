package md.textanalysis.totextconverter;

import md.textanalysis.totextconverter.impl.Fb2LinesToTextConverter;
import md.textanalysis.totextconverter.impl.MyDictLinesToTextConverter;
import md.textanalysis.totextconverter.impl.SrtLinesToTextConverter;
import md.textanalysis.totextconverter.impl.TxtLinesToTextConverter;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinesToTextConverterFactoryTest {
    private static final List<String> INPUT_LINES = Collections.emptyList();

    @Test
    void get() {
        assertEquals(TxtLinesToTextConverter.class, LinesToTextConverterFactory.get("txt", INPUT_LINES).getClass());
        assertEquals(SrtLinesToTextConverter.class, LinesToTextConverterFactory.get("srt", INPUT_LINES).getClass());
        assertEquals(Fb2LinesToTextConverter.class, LinesToTextConverterFactory.get("fb2", INPUT_LINES).getClass());
        assertEquals(MyDictLinesToTextConverter.class, LinesToTextConverterFactory.get("mydict", INPUT_LINES).getClass());
        assertEquals(TxtLinesToTextConverter.class, LinesToTextConverterFactory.get("zzz", INPUT_LINES).getClass());
        assertEquals(TxtLinesToTextConverter.class, LinesToTextConverterFactory.get("", INPUT_LINES).getClass());
    }

}