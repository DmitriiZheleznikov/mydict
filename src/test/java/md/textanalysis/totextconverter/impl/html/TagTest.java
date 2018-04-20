package md.textanalysis.totextconverter.impl.html;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {
    @Test
    void getName() {
        assertEquals("name", new Tag("<name i=\"eee\">").getName());
        assertNotEquals("name", new Tag("<   name i=\"eee\">").getName());
        assertNotEquals("name", new Tag("</   name i=\"eee\"/>").getName());
        assertEquals("name", new Tag("</name i=\"eee\">").getName());
    }

    @Test
    void isOpen() {
        assertEquals(true, new Tag("<name i=\"eee\">").isOpen());
        assertEquals(true, new Tag("<   name i=\"eee\"/>").isOpen());
        assertNotEquals(true, new Tag("</   name i=\"eee\"/>").isOpen());
    }

    @Test
    void isClose() {
        assertNotEquals(true, new Tag("<name i=\"eee\">").isClose());
        assertEquals(true, new Tag("<   name i=\"eee\"/>").isClose());
        assertEquals(true, new Tag("</   name i=\"eee\"/>").isClose());
    }

}