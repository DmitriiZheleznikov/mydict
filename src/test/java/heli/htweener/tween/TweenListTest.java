package heli.htweener.tween;

import heli.htweener.exception.NotEnoughSpaceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TweenListTest {
    private TweenList list;

    @BeforeEach
    void setUp() {
        list = new TweenList(10, 5);
        add("tween1", 60);
        add("tween2", 10);
        add("tween3", 20);
        add("tween4", 20);
        add("tween5", 50);
        add("tween6", 50);
    }

    @Test
    void add() {
        //                    612255
        assertEquals("AAAAAANNNN", list.getTextRepresentationOfActualState());
        list.step(10);
        //                    501144
        assertEquals("AIAAAANNNN", list.getTextRepresentationOfActualState());
        list.step(10);
        //                    400033
        assertEquals("AIIIAANNNN", list.getTextRepresentationOfActualState());
        add("tween1", 50);
        //                    400533
        assertEquals("AIIAAANNNN", list.getTextRepresentationOfActualState());
        list.step(30);
        //                    100200
        assertEquals("AIIAIINNNN", list.getTextRepresentationOfActualState());
        add("tween1", 20);
        //                    102200
        assertEquals("AIAAIINNNN", list.getTextRepresentationOfActualState());
        add("tween1", 20);
        //                    122200
        assertEquals("AAAAIINNNN", list.getTextRepresentationOfActualState());

        add("tween1", 20);
        add("tween2", 20);
        add("tween3", 20);
        add("tween4", 20);
        add("tween5", 20);
        add("tween6", 20);

        assertThrows(NotEnoughSpaceException.class, () -> add("tween4", 20));
    }

    @Test
    void interrupt() {
        //                    612255
        assertEquals("AAAAAANNNN", list.getTextRepresentationOfActualState());

        list.interrupt(null, "tween5");
        //                    612205
        assertEquals("AAAAIANNNN", list.getTextRepresentationOfActualState());
    }

    @Test
    void step() {
        //                    612255
        assertEquals("AAAAAANNNN", list.getTextRepresentationOfActualState());

        list.step(10);
        //                    501144
        assertEquals("AIAAAANNNN", list.getTextRepresentationOfActualState());

        list.step(10);
        //                    400033
        assertEquals("AIIIAANNNN", list.getTextRepresentationOfActualState());

        list.step(40);
        //                    000000
        assertEquals("IIIIIINNNN", list.getTextRepresentationOfActualState());

        list.step(40);
        //                    000000
        assertEquals("IIIIIINNNN", list.getTextRepresentationOfActualState());
    }

    private void add(String name, int duration) {
        list.add(null, duration, null, name);
    }
}