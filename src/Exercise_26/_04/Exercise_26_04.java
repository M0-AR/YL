package Exercise_26._04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Exercise_26._common.AVLTree;
import Exercise_26._common.AVLTree.Node;

public class Exercise_26_04 {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int value = 1; value <= 100; value++) {
            tree.insert(value);
        }

        List<Node<Integer>> leaves = new ArrayList<>();
        collectLeaves(tree.getRoot(), leaves);
        System.out.println("Leaf nodes: " + leaves.size());
        for (Node<Integer> leaf : leaves) {
            List<Integer> path = new ArrayList<>();
            for (Node<Integer> node = leaf; node != null; node = node.parent) {
                path.add(node.element);
            }
            Collections.reverse(path);
            System.out.println("Leaf " + leaf.element + ": " + path);
        }
    }

    private static void collectLeaves(Node<Integer> node, List<Node<Integer>> leaves) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leaves.add(node);
            return;
        }
        collectLeaves(node.left, leaves);
        collectLeaves(node.right, leaves);
    }
}
