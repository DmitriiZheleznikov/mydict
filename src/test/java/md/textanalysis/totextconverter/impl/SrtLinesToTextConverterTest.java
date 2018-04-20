package md.textanalysis.totextconverter.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SrtLinesToTextConverterTest {
    private static final List<String> INPUT_LIST = new ArrayList<>(35);
    private static final String EXPECTED =
            " (CLOCKS TICKING)" +
            " ANNOUNCER: October is" +
            " inventory time," +
            " so right now," +
            " Statler Toyota" +
            " is making the best" +
            " deals of the year" +
            " on all" +
            " 1985-model Toyotas." +
            " You won't find a better car" +
            " at a better price" +
            " with better service" +
            " anywhere in Hill Valley.";

    static {
        INPUT_LIST.add("1");
        INPUT_LIST.add("00:00:23,565 --> 00:00:25,233");
        INPUT_LIST.add("(CLOCKS TICKING)");
        INPUT_LIST.add("  ");
        INPUT_LIST.add("2");
        INPUT_LIST.add("00:01:31,550 --> 00:01:33,259");
        INPUT_LIST.add("ANNOUNCER: October is");
        INPUT_LIST.add("inventory time,");
        INPUT_LIST.add("");
        INPUT_LIST.add("3");
        INPUT_LIST.add("00:01:33,343 --> 00:01:34,969");
        INPUT_LIST.add("so right now,");
        INPUT_LIST.add("Statler Toyota");
        INPUT_LIST.add("");
        INPUT_LIST.add("4");
        INPUT_LIST.add("00:01:35,053 --> 00:01:36,929");
        INPUT_LIST.add("is making the best");
        INPUT_LIST.add("deals of the year");
        INPUT_LIST.add("");
        INPUT_LIST.add("5");
        INPUT_LIST.add("00:01:37,014 --> 00:01:39,182");
        INPUT_LIST.add("on all");
        INPUT_LIST.add("1985-model Toyotas.");
        INPUT_LIST.add("");
        INPUT_LIST.add("6");
        INPUT_LIST.add("00:01:39,349 --> 00:01:40,808");
        INPUT_LIST.add("You won't find a better car");
        INPUT_LIST.add("");
        INPUT_LIST.add("7");
        INPUT_LIST.add("00:01:40,893 --> 00:01:44,187");
        INPUT_LIST.add("at a better price");
        INPUT_LIST.add("with better service");
        INPUT_LIST.add("anywhere in Hill Valley.");
        INPUT_LIST.add("");
        INPUT_LIST.add("");
    }

    @Test
    void perform() {
        SrtLinesToTextConverter converter = new SrtLinesToTextConverter(INPUT_LIST);
        assertEquals(EXPECTED, converter.perform());
    }
}