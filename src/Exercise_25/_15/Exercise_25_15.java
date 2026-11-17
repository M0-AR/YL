package Exercise_25._15;

import Exercise_25._common.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_25_15 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 10 integers: ");
        int[] values = new int[10];
        for (int i = 0; i < values.length; i++) {
            if (!input.hasNextInt()) {
                System.out.println("Please enter 10 integers.");
                return;
            }
            values[i] = input.nextInt();
        }

        BST<Integer> tree = new BST<>();
        for (int value : values) {
            tree.insert(value);
        }
        tree.delete(values[0]);

        List<Integer> leaves = new ArrayList<>();
        collectLeaves(tree.getRoot(), leaves);
        for (int leaf : leaves) {
            System.out.println(tree.getPath(leaf));
        }
    }

    private static void collectLeaves(BST.TreeNode<Integer> node, List<Integer> leaves) {
        if (node == null) {
            return;
        }
        collectLeaves(node.getLeft(), leaves);
        if (node.isLeaf()) {
            leaves.add(node.getElement());
        }
        collectLeaves(node.getRight(), leaves);
    }
}
