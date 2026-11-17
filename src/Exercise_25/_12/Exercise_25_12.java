package Exercise_25._12;

import Exercise_25._common.BST;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Exercise_25_12 {
    private static int checks;

    public static void main(String[] args) {
        testEmptyTree();
        testInsertionAndSearch();
        testTraversals();
        testMetrics();
        testDeletion();
        testIterator();
        testCloneAndEquals();
        testClear();
        System.out.println("All " + checks + " BST checks passed.");
    }

    private static void testEmptyTree() {
        section("Empty tree");
        BST<Integer> tree = new BST<>();
        check(tree.isEmpty(), "new tree is empty");
        check(tree.getSize() == 0, "new tree size is zero");
        check(!tree.search(1), "empty tree does not contain 1");
        check(tree.height() == 0, "empty tree height is zero");
        check(tree.inorderList().isEmpty(), "empty inorder list");
        check(tree.preorderList().isEmpty(), "empty preorder list");
        check(tree.postorderList().isEmpty(), "empty postorder list");
        check(!tree.delete(1), "delete from empty tree returns false");
    }

    private static void testInsertionAndSearch() {
        section("Insertion and search");
        BST<Integer> tree = new BST<>();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int value : values) {
            check(tree.insert(value), "insert " + value);
        }
        check(tree.getSize() == values.length, "size after inserts");
        check(!tree.insert(50), "duplicate insert returns false");
        check(tree.search(40), "search existing value");
        check(!tree.search(45), "search missing value");
        check(tree.getPath(20).equals(List.of(20, 30, 50)), "path from 20 to root");
        check(tree.getPath(45).isEmpty(), "path for missing value is empty");
        check(tree.isLeaf(20), "20 is a leaf");
        check(!tree.isLeaf(30), "30 is not a leaf");
        check(!tree.isLeaf(45), "missing value is not a leaf");
    }

    private static void testTraversals() {
        section("Traversals");
        BST<Integer> tree = sampleTree();
        check(tree.inorderList().equals(List.of(20, 30, 40, 50, 60, 70, 80)), "inorder list");
        check(tree.preorderList().equals(List.of(50, 30, 20, 40, 70, 60, 80)), "preorder list");
        check(tree.postorderList().equals(List.of(20, 40, 30, 60, 80, 70, 50)), "postorder list");
        check(capture(tree::breadthFirstTraversal).equals("50 30 70 20 40 60 80"), "breadth-first order");
        check(capture(tree::inorder).equals("20 30 40 50 60 70 80"), "inorder output");
        check(capture(tree::preorder).equals("50 30 20 40 70 60 80"), "preorder output");
        check(capture(tree::postorder).equals("20 40 30 60 80 70 50"), "postorder output");
    }

    private static void testMetrics() {
        section("Tree metrics");
        BST<Integer> tree = sampleTree();
        check(tree.height() == 3, "height");
        check(tree.getNumberOfLeaves() == 4, "leaf count");
        check(tree.getNumberOfNonLeaves() == 3, "nonleaf count");
        check(tree.isFullBST(), "sample tree is full");
        tree.insert(10);
        check(!tree.isFullBST(), "tree with an extra leaf is not full");
    }

    private static void testDeletion() {
        section("Deletion");
        BST<Integer> tree = sampleTree();
        check(tree.delete(20), "delete leaf");
        check(!tree.search(20), "leaf removed");
        check(tree.delete(30), "delete node with one child");
        check(!tree.search(30), "one-child node removed");
        check(tree.delete(50), "delete root with two children");
        check(!tree.search(50), "root removed");
        check(tree.getSize() == 4, "size after deletions");
        check(tree.inorderList().equals(List.of(40, 60, 70, 80)), "inorder after deletions");
        check(!tree.delete(50), "delete missing value returns false");
    }

    private static void testIterator() {
        section("Iterators");
        BST<Integer> tree = sampleTree();
        List<Integer> values = new ArrayList<>();
        for (int value : tree) {
            values.add(value);
        }
        check(values.equals(List.of(20, 30, 40, 50, 60, 70, 80)), "for-each inorder");

        Iterator<Integer> preorder = tree.preorderIterator();
        List<Integer> preorderValues = new ArrayList<>();
        while (preorder.hasNext()) {
            preorderValues.add(preorder.next());
        }
        check(preorderValues.equals(List.of(50, 30, 20, 40, 70, 60, 80)), "preorder iterator");

        Iterator<Integer> postorder = tree.postorderIterator();
        List<Integer> postorderValues = new ArrayList<>();
        while (postorder.hasNext()) {
            postorderValues.add(postorder.next());
        }
        check(postorderValues.equals(List.of(20, 40, 30, 60, 80, 70, 50)), "postorder iterator");

        ListIterator<Integer> iterator = tree.listIterator();
        check(iterator.next() == 20, "list iterator forward");
        check(iterator.next() == 30, "list iterator forward 2");
        check(iterator.previous() == 30, "list iterator backward");
        check(iterator.previous() == 20, "list iterator backward 2");
        check(!iterator.hasPrevious(), "list iterator at start");

        ListIterator<Integer> indexed = tree.listIterator(3);
        check(indexed.next() == 50, "list iterator starting index");

        iterator.add(35);
        check(tree.inorderList().equals(List.of(20, 30, 35, 40, 50, 60, 70, 80)), "list iterator add");
        check(iterator.previous() == 35, "list iterator previous after add");
        iterator.set(36);
        check(tree.inorderList().equals(List.of(20, 30, 36, 40, 50, 60, 70, 80)), "list iterator set");
        iterator.remove();
        check(tree.inorderList().equals(List.of(20, 30, 40, 50, 60, 70, 80)), "list iterator remove");
    }

    private static void testCloneAndEquals() {
        section("Clone and equals");
        BST<Integer> tree = sampleTree();
        BST<Integer> copy = tree.clone();
        check(copy.equals(tree), "clone equals original");
        check(copy != tree, "clone is a different object");
        check(copy.getRoot() != tree.getRoot(), "clone has a different root");
        copy.insert(90);
        check(!copy.equals(tree), "clone is independent");
        check(tree.getSize() == 7, "original size unchanged");
        BST<Integer> sameElements = new BST<>(new Integer[] {80, 70, 60, 50, 40, 30, 20});
        check(tree.equals(sameElements), "equals ignores shape");
        check(tree.hashCode() == sameElements.hashCode(), "equal trees share a hash code");
    }

    private static void testClear() {
        section("Clear");
        BST<Integer> tree = sampleTree();
        tree.clear();
        check(tree.isEmpty(), "tree is empty after clear");
        check(tree.getSize() == 0, "size is zero after clear");
        check(tree.getRoot() == null, "root is null after clear");
    }

    private static BST<Integer> sampleTree() {
        return new BST<>(new Integer[] {50, 30, 70, 20, 40, 60, 80});
    }

    private static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(output));
            action.run();
        } finally {
            System.setOut(original);
        }
        return output.toString().trim();
    }

    private static void section(String name) {
        System.out.println("Testing " + name + "...");
    }

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
