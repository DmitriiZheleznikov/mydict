package md.textanalysis.totextconverter;

import java.util.List;

abstract public class AbstractLinesToTextConverter implements ILinesToTextConverter {
    protected List<String> rawLinesToAnalyse;

    public AbstractLinesToTextConverter(List<String> rawLinesToAnalyse) {
        this.rawLinesToAnalyse = rawLinesToAnalyse;
    }

    // -------- PRE process --------
    protected List<String> preProcessList(List<String> rawLinesToAnalyse) {
        return rawLinesToAnalyse;
    }

    // -------- process --------
    protected String processList(List<String> rawLinesToAnalyse) {
        StringBuilder sb = new StringBuilder();

        for (String line : rawLinesToAnalyse) {
            line = line.trim();
            String converterLine = processLine(line);
            if(converterLine != null && converterLine.length() > 0) sb.append(converterLine);
        }

        return sb.toString();
    }
    abstract protected String processLine(String line);

    // -------- POST process --------
    protected String postProcessResult(String result) {
        return result;
    }

// -------------------------------------------------------

    public String perform() {
        rawLinesToAnalyse = preProcessList(rawLinesToAnalyse);
        String result = processList(rawLinesToAnalyse);
        return postProcessResult(result);
    }

}
