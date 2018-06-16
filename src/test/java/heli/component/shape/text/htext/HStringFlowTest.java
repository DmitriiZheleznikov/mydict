package heli.component.shape.text.htext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HStringFlowTest {
    private HStringFlow flow;

    @BeforeEach
    void setUp() {
        flow = new HStringFlow();
        flow.add(new HString("12345", HString.FontWeight.REGULAR));
        flow.add(new HString("67890", HString.FontWeight.BOLD));
        flow.add(new HString("qwertyuiopasdfghjklzxcvbnm", HString.FontWeight.REGULAR));
    }

    @Test
    void cutLeft() {
        flow.cutLeft(11);
        assertEquals("wertyuiopasdfghjklzxcvbnm", flow.toString());
    }

    @Test
    void cutRight() {
        flow.cutRight(11);
        assertEquals("1234567890qwertyuiopasdfg", flow.toString());
    }
}