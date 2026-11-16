package atlas.collections;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AvlTreeTest {
    @Test
    void staysBalancedAfterSortedInsertions() {
        AvlTree tree = new AvlTree();
        for (int key = 1; key <= 1_000; key++) assertTrue(tree.insert(key));

        assertEquals(1_000, tree.size());
        assertTrue(tree.checkInvariants());
        assertTrue(tree.height() <= 14);
    }

    @Test
    void matchesTreeSetAfterRandomOperations() {
        AvlTree tree = new AvlTree();
        TreeSet<Integer> expected = new TreeSet<>();
        Random random = new Random(7);

        for (int i = 0; i < 2_000; i++) {
            int key = random.nextInt(500);
            if (random.nextBoolean()) {
                assertEquals(expected.add(key), tree.insert(key));
            } else {
                assertEquals(expected.remove(key), tree.delete(key));
            }
            assertTrue(tree.checkInvariants());
        }

        assertEquals(expected.size(), tree.size());
        assertEquals(List.copyOf(expected), tree.inorder());
    }

    @Test
    void rejectsDuplicateKeys() {
        AvlTree tree = new AvlTree();
        assertTrue(tree.insert(42));
        assertFalse(tree.insert(42));
        assertEquals(1, tree.size());
    }

    @Test
    void deletesAllKeys() {
        AvlTree tree = new AvlTree();
        Set<Integer> keys = new HashSet<>();
        for (int key = 0; key < 200; key++) {
            keys.add(key);
            tree.insert(key);
        }
        for (int key : keys) assertTrue(tree.delete(key));

        assertEquals(0, tree.size());
        assertTrue(tree.checkInvariants());
        assertTrue(tree.inorder().isEmpty());
    }
}
