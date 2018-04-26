package md.textanalysis.totextconverter;

import md.textanalysis.totextconverter.impl.Fb2LinesToTextConverter;
import md.textanalysis.totextconverter.impl.MyDictLinesToTextConverter;
import md.textanalysis.totextconverter.impl.SrtLinesToTextConverter;
import md.textanalysis.totextconverter.impl.TxtLinesToTextConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates converter by file extension
 */
public class LinesToTextConverterFactory {
    private static final Map<String, ILinesToTextFactoryAction> MAP = new HashMap<>(3);
    static {
        MAP.put("txt", TxtLinesToTextConverter::new);
        MAP.put("srt", SrtLinesToTextConverter::new);
        MAP.put("fb2", Fb2LinesToTextConverter::new);
        MAP.put("mydict", MyDictLinesToTextConverter::new);
    }

    public static ILinesToTextConverter get(String fileExtension, List<String> rawLinesToAnalyse) {
        ILinesToTextFactoryAction converter = MAP.get(fileExtension.trim().toLowerCase());
        if (converter == null) converter = MAP.get("txt");

        return converter.call(rawLinesToAnalyse);
    }

    private interface ILinesToTextFactoryAction {
        ILinesToTextConverter call(List<String> rawLinesToAnalyse);
    }
}
