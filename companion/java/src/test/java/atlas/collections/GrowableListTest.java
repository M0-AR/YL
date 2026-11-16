package atlas.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrowableListTest {
    @Test
    void growsAndKeepsInsertionOrder() {
        GrowableList<Integer> list = new GrowableList<>();
        for (int i = 0; i < 100; i++) list.add(i);

        assertEquals(100, list.size());
        assertEquals(42, list.get(42));
    }

    @Test
    void removesAndShiftsElements() {
        GrowableList<String> list = new GrowableList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        assertEquals("b", list.remove(1));
        assertEquals("c", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void rejectsInvalidIndexes() {
        GrowableList<String> list = new GrowableList<>();
        list.add("only");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void iteratorVisitsEveryElement() {
        GrowableList<Integer> list = new GrowableList<>();
        for (int i = 0; i < 10; i++) list.add(i);

        List<Integer> seen = new ArrayList<>();
        for (int value : list) seen.add(value);

        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), seen);
    }
}
