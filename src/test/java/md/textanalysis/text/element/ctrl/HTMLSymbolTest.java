package md.textanalysis.text.element.ctrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HTMLSymbolTest {
    HTMLSymbol symbol;

    @BeforeEach
    void init() {
        symbol = new HTMLSymbol();
    }

    @Test
    void isComplete() {
        symbol.add("&");
        assertEquals(false, symbol.isComplete());

        symbol.add("test");
        assertEquals(false, symbol.isComplete());

        symbol.add(";");
        assertEquals(true, symbol.isComplete());
    }

    @Test
    void isValid() {
        symbol.add("&");
        assertEquals(true, symbol.isValid());
        symbol.add("test");
        assertEquals(false, symbol.isValid());
        symbol.add(";");
        assertEquals(false, symbol.isValid());

        symbol = new HTMLSymbol();
        symbol.add("&");
        assertEquals(true, symbol.isValid());
        symbol.add("amp");
        assertEquals(true, symbol.isValid());
        symbol.add(";");
        assertEquals(true, symbol.isValid());

        symbol = new HTMLSymbol();
        symbol.add("&");
        assertEquals(true, symbol.isValid());
        symbol.add("#");
        assertEquals(true, symbol.isValid());
        symbol.add("777");
        assertEquals(false, symbol.isValid());

        symbol = new HTMLSymbol();
        symbol.add("&");
        assertEquals(true, symbol.isValid());
        symbol.add("#");
        assertEquals(true, symbol.isValid());
        symbol.add("8482");
        assertEquals(true, symbol.isValid());
        symbol.add(";");
        assertEquals(true, symbol.isValid());
    }

    @Test
    void get() {
        symbol.add("&");
        symbol.add("#");
        symbol.add("8482");
        symbol.add(";");
        assertEquals("™", symbol.get());

        symbol = new HTMLSymbol();
        symbol.add("&");
        symbol.add("amp");
        symbol.add(";");
        String tst = symbol.get();
        assertEquals("&", symbol.get());
    }
}