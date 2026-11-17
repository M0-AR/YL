package Exercise_26._06;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import Exercise_26._common.AVLTree;
import Exercise_26._common.AVLTree.Node;

public class Exercise_26_06 {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        check(tree.isEmpty(), "new tree is empty");
        check(tree.getSize() == 0 && tree.getHeight() == 0, "new tree dimensions");
        check(!tree.search(1) && !tree.delete(1), "empty tree operations");
        check(tree.find(1) == null, "find on empty tree");

        check(tree.insert(25) && tree.insert(20) && tree.insert(34), "insert returns true");
        check(tree.insert(5) && tree.insert(30) && tree.insert(50), "insert leaves");
        check(!tree.insert(25), "duplicate insert returns false");
        check(tree.getSize() == 6, "size after inserts");
        check(tree.inorderList().equals(List.of(5, 20, 25, 30, 34, 50)), "inorder order");
        check(tree.getHeight() == 3, "height after inserts");
        verifyShape(tree.getRoot());

        check(tree.search(30) && !tree.search(99), "search present and absent");
        check(tree.find(1) == 5 && tree.find(5) == 34 && tree.find(6) == 50, "find by rank");
        check(tree.find(0) == null && tree.find(7) == null, "find out of range");
        check(tree.pathTo(30).equals(List.of(25, 34, 30)), "path to node");

        List<Integer> iterated = new ArrayList<>();
        for (int value : tree) {
            iterated.add(value);
        }
        check(iterated.equals(tree.inorderList()), "iterator order");

        check(tree.delete(5) && tree.delete(34) && tree.delete(25), "delete internal nodes");
        check(!tree.delete(34), "delete missing returns false");
        check(tree.inorderList().equals(List.of(20, 30, 50)), "order after deletes");
        verifyShape(tree.getRoot());

        tree.clear();
        check(tree.isEmpty() && tree.getSize() == 0 && tree.getRoot() == null, "clear");

        Random random = new Random(26);
        AVLTree<Integer> randomTree = new AVLTree<>();
        TreeSet<Integer> reference = new TreeSet<>();
        for (int step = 0; step < 20_000; step++) {
            int value = random.nextInt(2_000);
            if (random.nextBoolean()) {
                randomTree.insert(value);
                reference.add(value);
            } else {
                randomTree.delete(value);
                reference.remove(value);
            }
            if (step % 250 == 0) {
                check(randomTree.inorderList().equals(List.copyOf(reference)), "random order");
                check(randomTree.getSize() == reference.size(), "random size");
                verifyShape(randomTree.getRoot());
            }
        }
        for (int k = 1; k <= randomTree.getSize(); k += 53) {
            check(List.copyOf(reference).get(k - 1).equals(randomTree.find(k)), "random rank");
        }
        for (int value : reference) {
            check(randomTree.search(value), "random search");
        }
        int rootValue = randomTree.getRoot().element;
        check(randomTree.delete(rootValue), "delete root");
        reference.remove(rootValue);
        check(randomTree.getSize() == reference.size(), "size after root delete");
        verifyShape(randomTree.getRoot());

        System.out.println("All AVLTree tests passed");
    }

    private static void verifyShape(Node<Integer> node) {
        verifyNode(node, null);
    }

    private static void verifyNode(Node<Integer> node, Node<Integer> parent) {
        if (node == null) {
            return;
        }
        check(node.parent == parent, "parent reference");
        int balance = AVLTree.heightOf(node.left) - AVLTree.heightOf(node.right);
        check(Math.abs(balance) <= 1, "balance factor");
        check(node.size == 1 + AVLTree.sizeOf(node.left) + AVLTree.sizeOf(node.right),
                "subtree size");
        verifyNode(node.left, node);
        verifyNode(node.right, node);
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("FAILED: " + message);
        }
    }
}
