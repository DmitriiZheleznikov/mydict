package md.textanalysis.totextconverter.impl;

import java.util.List;
import java.util.regex.Pattern;

import static md.textanalysis.totextconverter.impl.srt.SRTHelper.isLineTime;
import static md.textanalysis.totextconverter.impl.srt.SRTHelper.isLineValid;

/**
 * Converts list of lines to 1 big String for SRT file. Removes all time and tech lines, takes into account word breaks and so on
 */
public class SrtLinesToTextConverter extends TxtLinesToTextConverter {
    public SrtLinesToTextConverter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    @Override
    protected String processLine(String line) {
        if (isLineTime(line)) return null;
        if (!isLineValid(line)) return null;

        return super.processLine(line);
    }


}
