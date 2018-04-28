package md.textanalysis.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamplesHelperTest {
    private static final String RAW_TEXT = "(CLOCKS TICKING) ANNOUNCER: October is inventory time, so right now, " +
            "Statler Toyota is making the best deals of the year on all 1985-model Toyotas. You won't find a better car " +
            "at a better price with better service anywhere in Hill Valley. That's Statler Toyota in downtown Hill Valley. " +
            "NEWSCASTER: The Senate is expected to vote on this today. In other news, officials at the Pacific Nuclear " +
            "research facility have denied the rumor that a case of missing plutonium was in fact stolen from their vault " +
            "two weeks ago. A Libyan terrorist group had claimed responsibility for the alleged theft. (ALARM RINGING) " +
            "However, officials now attribute the discrepancy to a simple clerical error. The FBI, which is investigating " +
            "the matter, had no comment. (BUZZER BLARES) MARTY: Hey, Doc? Doc? Hello! Anybody home? Einstein, come here, " +
            "boy. (WHISTLES) What's going on? Oh, God. Oh, Jesus! That is disgusting. Where the hell is he?";
    private static final String LOWER_TEXT = RAW_TEXT.toLowerCase();

    @Test
    void get() {
        int i = LOWER_TEXT.indexOf("officials");
        assertEquals("ley. That's Statler Toyota in downtown Hill Valley. NEWSCASTER: The Senate is expected to " +
                "vote on this today. In other news, officials at the Pacific Nuclear research facility have denied the " +
                "rumor that a case of missing plutonium was in fact stolen ",
                ExamplesHelper.get(i, RAW_TEXT));

        i = LOWER_TEXT.indexOf("officials", i+1);
        assertEquals("eir vault two weeks ago. A Libyan terrorist group had claimed responsibility for the alleged " +
                "theft. (ALARM RINGING) However, officials now attribute the discrepancy to a simple clerical error. " +
                "The FBI, which is investigating the matter, had no commen",
                ExamplesHelper.get(i, RAW_TEXT));
    }

}