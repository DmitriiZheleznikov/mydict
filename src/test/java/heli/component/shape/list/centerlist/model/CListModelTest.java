package heli.component.shape.list.centerlist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CListModelTest {
    private CListModel list;
    private CListLineModel text1;
    private CListLineModel text2;
    private CListLineModel text3;
    private CListLineModel text9;
    private CListLineModel text10;

    @BeforeEach
    void setUp() {
        CListLineModel next = new CListLineModel("text1", null);
        text1 = next;
        next = new CListLineModel("text2", next);
        text2 = next;
        next = new CListLineModel("text3", next);
        text3 = next;
        next = new CListLineModel("text4", next);
        next = new CListLineModel("text5", next);
        next.disable();
        next = new CListLineModel("text6", next);
        next.disable();
        next = new CListLineModel("text7", next);
        next.disable();
        next = new CListLineModel("text8", next);
        next = new CListLineModel("text9", next);
        text9 = next;
        next = new CListLineModel("text10", next);
        text10 = next;
        next = new CListLineModel("text11", next);
        next = new CListLineModel("text12", next);
        next = new CListLineModel("text13", next);
        next.disable();
        next = new CListLineModel("text14", next);
        next = new CListLineModel("text15", next);
        list = new CListModel(next);
    }

    @Test
    void goTo() {
        assertEquals("LM{cur=\"LL0[text1]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.goTo("text11");
        assertEquals("LM{cur=\"LL10[text11]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.goTo("text1122324356456674747");
        assertEquals("LM{cur=\"LL10[text11]E\" 11/15}", list.getTextRepresentationOfActualState());
    }

    @Test
    void next() {
        assertEquals("LM{cur=\"LL0[text1]E\" 11/15}", list.getTextRepresentationOfActualState());

        CListModelOperation opActual = list.next();
        CListModelOperation opExpected = new CListModelOperation(0, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NEXT);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL1[text2]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.next();
        list.next();

        opActual = list.next();
        opExpected = new CListModelOperation(3, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NEXT);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL7[text8]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.next();
        list.next();
        list.next();
        list.next();
        list.next();

        opActual = list.next();
        opExpected = new CListModelOperation(13, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NEXT);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL14[text15]E\" 11/15}", list.getTextRepresentationOfActualState());

        opActual = list.next();
        opExpected = new CListModelOperation(14, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL14[text15]E\" 11/15}", list.getTextRepresentationOfActualState());
    }

    @Test
    void prev() {
        list.goTo("text15");
        assertEquals("LM{cur=\"LL14[text15]E\" 11/15}", list.getTextRepresentationOfActualState());

        CListModelOperation opActual = list.prev();
        CListModelOperation opExpected = new CListModelOperation(14, CListModelOperation.Action.NULL, CListModelOperation.Scroll.PREV);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL13[text14]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.prev(); //text12
        list.prev(); //text11
        list.prev(); //text10
        list.prev(); //text9
        list.prev(); //text8
        list.prev(); //text4

        opActual = list.prev();
        opExpected = new CListModelOperation(3, CListModelOperation.Action.NULL, CListModelOperation.Scroll.PREV);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL2[text3]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.prev(); //text2
        list.prev(); //text1
        assertEquals("LM{cur=\"LL0[text1]E\" 11/15}", list.getTextRepresentationOfActualState());

        opActual = list.prev();
        opExpected = new CListModelOperation(0, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL0[text1]E\" 11/15}", list.getTextRepresentationOfActualState());
    }

    @Test
    void disableCurrent() {
        assertEquals("LM{cur=\"LL0[text1]E\" 11/15}", list.getTextRepresentationOfActualState());

        list.next();
        CListModelOperation opActual = list.disableCurrent();
        CListModelOperation opExpected = new CListModelOperation(1, CListModelOperation.Action.REMOVE, CListModelOperation.Scroll.NEXT);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL2[text3]E\" 10/15}", list.getTextRepresentationOfActualState());

        list.disableCurrent();
        list.disableCurrent();
        list.disableCurrent();
        list.disableCurrent();
        list.disableCurrent();
        list.disableCurrent();
        list.disableCurrent();
        list.disableCurrent();
        assertEquals("LM{cur=\"LL14[text15]E\" 2/15}", list.getTextRepresentationOfActualState());

        opActual = list.disableCurrent();
        opExpected = new CListModelOperation(14, CListModelOperation.Action.REMOVE, CListModelOperation.Scroll.PREV);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL0[text1]E\" 1/15}", list.getTextRepresentationOfActualState());

        opActual = list.disableCurrent();
        opExpected = new CListModelOperation(0, CListModelOperation.Action.REMOVE, CListModelOperation.Scroll.NULL);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"null\" 0/15}", list.getTextRepresentationOfActualState());

        opActual = list.disableCurrent();
        opExpected = new CListModelOperation(0, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"null\" 0/15}", list.getTextRepresentationOfActualState());
    }

    @Test
    void restore() {
        assertEquals("LM{cur=\"LL0[text1]E\" 11/15}", list.getTextRepresentationOfActualState());

        CListModelOperation opActual = list.restore(6);
        CListModelOperation opExpected = new CListModelOperation(6, CListModelOperation.Action.RESTORE, CListModelOperation.Scroll.NEXT);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL6[text7]E\" 12/15}", list.getTextRepresentationOfActualState());

        opActual = list.restore(4);
        opExpected = new CListModelOperation(4, CListModelOperation.Action.RESTORE, CListModelOperation.Scroll.PREV);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL4[text5]E\" 13/15}", list.getTextRepresentationOfActualState());

        opActual = list.restore(4);
        opExpected = new CListModelOperation(4, CListModelOperation.Action.NULL, CListModelOperation.Scroll.NULL);
        assertEquals(opExpected, opActual);
        assertEquals("LM{cur=\"LL4[text5]E\" 13/15}", list.getTextRepresentationOfActualState());
    }

    @Test
    void getWindow() {
        assertEquals(generateWindow(null, text1, 1), list.getWindow());
        list.next();
        assertEquals(generateWindow(null, text1, 2), list.getWindow());
        list.next();
        assertEquals(generateWindow(null, text1, 3), list.getWindow());
        list.next();
        assertEquals(generateWindow(text1, text2, 3), list.getWindow());
        list.next();
        assertEquals(generateWindow(text2, text3, 3), list.getWindow());
        list.goTo("text14");
        assertEquals(generateWindow(text9, text10, 4), list.getWindow());
        list.next();
        assertEquals(generateWindow(text9, text10, 5), list.getWindow());

        CListLineModel next = new CListLineModel("text1", null);
        next.disable();
        next = new CListLineModel("text2", next);
        text2 = next;
        next = new CListLineModel("text3", next);
        next = new CListLineModel("text4", next);
        next = new CListLineModel("text5", next);
        next = new CListLineModel("text6", next);
        next = new CListLineModel("text7", next);
        list = new CListModel(next);

        assertEquals(generateWindow(null, text2, 1), list.getWindow());
    }

    private CListModelWindow generateWindow(CListLineModel m0, CListLineModel m1, int cur) {
        CListModelWindow window = new CListModelWindow();
        window.setCurrent(cur);
        window.getLines()[0] = m0;
        for (int i = 1; i < heli.component.shape.list.centerlist.model.CListModel.SIZE; i++) {
            window.getLines()[i] = m1;
            m1 = findNextModel(m1);
        }

        return window;
    }

    private CListLineModel findNextModel(CListLineModel c) {
        if (c == null) return null;

        c = c.next;
        if (c == null) return null;

        if (c.isDisabled()) return findNextModel(c);

        return c;
    }

}