package md.textanalysis.totextconverter;

import md.textanalysis.totextconverter.impl.Fb2LinesToTextConverter;
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
    }

    public static ILinesToTextConverter get(String fileExtension, List<String> rawLinesToAnalyse) {
        return MAP.get(fileExtension.trim().toLowerCase()).call(rawLinesToAnalyse);
    }

    private interface ILinesToTextFactoryAction {
        ILinesToTextConverter call(List<String> rawLinesToAnalyse);
    }
}
