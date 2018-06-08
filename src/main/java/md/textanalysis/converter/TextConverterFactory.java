package md.textanalysis.converter;

import md.textanalysis.converter.impl.Fb2Converter;
import md.textanalysis.converter.impl.MyDictConverter;
import md.textanalysis.converter.impl.SrtConverter;
import md.textanalysis.converter.impl.TxtConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextConverterFactory {
    private static final Map<String, IFactoryAction> MAP = new HashMap<>(4);
    static {
        MAP.put("txt", TxtConverter::new);
        MAP.put("srt", SrtConverter::new);
        MAP.put("fb2", Fb2Converter::new);
        MAP.put("mydict", MyDictConverter::new);
    }

    public static ITextConverter get(String fileExtension, List<String> rawLinesToAnalyse) {
        IFactoryAction converter = MAP.get(fileExtension.trim().toLowerCase());
        if (converter == null) converter = MAP.get("txt");

        return converter.call(rawLinesToAnalyse);
    }

    private interface IFactoryAction {
        ITextConverter call(List<String> rawLinesToAnalyse);
    }
}
