package md.textanalysis.totextconverter.impl;

import md.textanalysis.totextconverter.AbstractLinesToTextConverter;

import java.util.List;

/**
 * Converts list of lines to 1 big String for TXT file. Takes into account word breaks and so on
 */
public class TxtLinesToTextConverter extends AbstractLinesToTextConverter {
    private boolean wordWrap = false;

    public TxtLinesToTextConverter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    @Override
    protected String processLine(String line) {
        if (line == null || line.trim().length() == 0) {
            wordWrap = false;
            return null;
        }

        if (!wordWrap) line = " " + line;
        wordWrap = line.charAt(line.length()-1) == 45;

        return convertToStringLine(line);
    }

    private String convertToStringLine(String lineToConvert) {
        if (lineToConvert.charAt(lineToConvert.length()-1) == 45) {
            return lineToConvert.substring(0, lineToConvert.length()-1);
        }
        return lineToConvert;
    }
}
