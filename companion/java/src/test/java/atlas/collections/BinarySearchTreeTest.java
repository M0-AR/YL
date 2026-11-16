package atlas.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {
    @Test
    void insertsAndFindsKeys() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        assertTrue(tree.contains(30));
        assertFalse(tree.contains(40));
        assertEquals(3, tree.size());
    }

    @Test
    void rejectsDuplicateKeys() {
        BinarySearchTree tree = new BinarySearchTree();
        assertTrue(tree.insert(10));
        assertFalse(tree.insert(10));
        assertEquals(1, tree.size());
    }

    @Test
    void inOrderTraversalIsSorted() {
        BinarySearchTree tree = new BinarySearchTree();
        for (int key : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.insert(key);
        assertEquals(List.of(20, 30, 40, 50, 60, 70, 80), tree.inorder());
    }

    @Test
    void deletesLeafOneChildAndTwoChildren() {
        BinarySearchTree tree = new BinarySearchTree();
        for (int key : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.insert(key);

        assertTrue(tree.delete(20));
        assertTrue(tree.delete(30));
        assertTrue(tree.delete(50));

        assertEquals(List.of(40, 60, 70, 80), tree.inorder());
        assertEquals(4, tree.size());
    }

    @Test
    void deletesRootAndMissingKey() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);

        assertFalse(tree.delete(99));
        assertTrue(tree.delete(10));
        assertEquals(0, tree.size());
        assertFalse(tree.contains(10));
    }

    @Test
    void reportsHeightAndBalance() {
        BinarySearchTree tree = new BinarySearchTree();
        for (int key : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.insert(key);
        assertTrue(tree.isBalanced());
        assertEquals(2, tree.height());
        assertEquals(20, tree.min());
        assertEquals(80, tree.max());
    }
}
