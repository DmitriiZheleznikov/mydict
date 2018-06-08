package md.textanalysis.text.element.ctrl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HTMLTagTest {
    @Test
    void getNameLowerCase() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name-1");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add(">");
        assertEquals("name-1", HTMLTag.getNameLowerCase());

        HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("/");
        HTMLTag.add("Name_2");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add(">");
        assertEquals("name_2", HTMLTag.getNameLowerCase());

        HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name3");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add("/");
        HTMLTag.add(">");
        assertEquals("name3", HTMLTag.getNameLowerCase());
    }

    @Test
    void isOpen1() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name-1");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add(">");
        assertEquals(true, HTMLTag.isOpen());
    }

    @Test
    void isOpen2() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("/");
        HTMLTag.add("Name_2");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add(">");
        assertEquals(false, HTMLTag.isOpen());
    }

    @Test
    void isOpen3() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name3");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add("/");
        HTMLTag.add(">");
        assertEquals(true, HTMLTag.isOpen());
    }

    @Test
    void isClose1() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name-1");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add(">");
        assertEquals(false, HTMLTag.isClose());
    }

    @Test
    void isClose2() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("/");
        HTMLTag.add("Name_2");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add(">");
        assertEquals(true, HTMLTag.isClose());
    }

    @Test
    void isClose3() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name3");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add("/");
        HTMLTag.add(">");
        assertEquals(true, HTMLTag.isClose());
    }

    @Test
    void isComplete1() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name3");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add("/");
        HTMLTag.add(">");
        assertEquals(true, HTMLTag.isComplete());
    }

    @Test
    void isComplete2() {
        HTMLTag HTMLTag = new HTMLTag();
        HTMLTag.add("<");
        HTMLTag.add("Name3");
        HTMLTag.add("i");
        HTMLTag.add("=");
        HTMLTag.add("/");
        HTMLTag.add("<");
        assertEquals(false, HTMLTag.isComplete());
    }

}