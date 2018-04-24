package md.textanalysis.totextconverter.impl;

import md.textanalysis.totextconverter.AbstractLinesToTextConverter;

import java.util.List;

public class MyDictLinesToTextConverter extends AbstractLinesToTextConverter {
    public MyDictLinesToTextConverter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    @Override
    protected String processLine(String line) {
        if (line == null || line.trim().length() == 0) return "";

        return line.replaceAll("[^a-zA-Z' ]", ";");
    }
}
