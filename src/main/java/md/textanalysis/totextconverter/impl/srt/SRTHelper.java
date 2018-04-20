package md.textanalysis.totextconverter.impl.srt;

import java.util.regex.Pattern;

/**
 * Useful functions for SRT processing
 */
public class SRTHelper {
    /** identify of line is 'time' in SRT file */
    public static boolean isLineTime(String line) {
        if (line == null || "".equals(line)) return false;

        return Pattern.matches("^\\d+:.+-->.+$", line);
    }

    /** identify if line is 'text' or 'time' and not tech line */
    public static boolean isLineValid(String line) {
        if (line == null || "".equals(line)) return false;

        if (((int)line.charAt(0)) == 65279) line = line.substring(1);
        if (Pattern.matches("^\\d+$", line)) {
            return false;
        }

        return true;
    }
}
