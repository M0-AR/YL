package atlas.advanced;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedBlackTreeTest {
    @Test
    void emptyTreeIsValid() {
        RedBlackTree tree = new RedBlackTree();

        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
        assertTrue(tree.checkInvariants());
        assertFalse(tree.contains(1));
        assertTrue(tree.inorder().isEmpty());
    }

    @Test
    void insertsSortedKeysWithoutLosingBalance() {
        RedBlackTree tree = new RedBlackTree();
        for (int key = 0; key < 2_000; key++) assertTrue(tree.insert(key));

        assertEquals(2_000, tree.size());
        assertEquals(range(0, 2_000), tree.inorder());
        assertTrue(tree.checkInvariants());
        assertTrue(tree.height() <= 24);
        assertFalse(tree.contains(2_000));
        assertFalse(tree.contains(-1));
    }

    @Test
    void rejectsDuplicateKeys() {
        RedBlackTree tree = new RedBlackTree();

        assertTrue(tree.insert(42));
        assertFalse(tree.insert(42));
        assertEquals(1, tree.size());
        assertEquals(List.of(42), tree.inorder());
        assertTrue(tree.checkInvariants());
    }

    @Test
    void staysValidThroughRandomInsertions() {
        RedBlackTree tree = new RedBlackTree();
        boolean[] present = new boolean[1_000];
        Random random = new Random(19);

        for (int i = 0; i < 5_000; i++) {
            int key = random.nextInt(present.length);
            assertEquals(!present[key], tree.insert(key));
            present[key] = true;
            assertTrue(tree.checkInvariants());
        }

        int expectedSize = 0;
        List<Integer> expected = new ArrayList<>();
        for (int key = 0; key < present.length; key++) {
            if (present[key]) {
                expectedSize++;
                expected.add(key);
                assertTrue(tree.contains(key));
            } else {
                assertFalse(tree.contains(key));
            }
        }

        assertEquals(expectedSize, tree.size());
        assertEquals(expected, tree.inorder());
        assertTrue(tree.height() <= 24);
    }

    private static List<Integer> range(int start, int end) {
        List<Integer> values = new ArrayList<>(end - start);
        for (int value = start; value < end; value++) values.add(value);
        return values;
    }
}
