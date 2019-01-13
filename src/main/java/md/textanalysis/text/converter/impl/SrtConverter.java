package md.textanalysis.text.converter.impl;

import java.util.List;

import static md.textanalysis.helper.SRTHelper.isLineTime;
import static md.textanalysis.helper.SRTHelper.isLineValid;

public class SrtConverter extends TxtConverter {

    public SrtConverter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    @Override
    protected boolean beforeLineProcess(String line) {
        return isLineTime(line) || !isLineValid(line);
    }
}
