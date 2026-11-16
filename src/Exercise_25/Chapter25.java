package Exercise_25;

import java.util.ArrayList;
import java.util.List;

public class Chapter25 {
    public static String exerciseOne(int[] values) {
        Node root = build(values);
        List<Integer> keys = new ArrayList<>();
        inOrder(root, keys);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            if (i > 0) {
                builder.append(' ');
            }
            builder.append(keys.get(i));
        }
        return builder.toString();
    }

    public static boolean exerciseTwo(int[] values, int target) {
        Node node = build(values);
        while (node != null) {
            if (target == node.key) {
                return true;
            }
            node = target < node.key ? node.left : node.right;
        }
        return false;
    }

    public static int[] exerciseThree(int[] values, int low, int high) {
        List<Integer> keys = new ArrayList<>();
        collectRange(build(values), low, high, keys);
        int[] result = new int[keys.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = keys.get(i);
        }
        return result;
    }

    private static Node build(int[] values) {
        Node root = null;
        if (values != null) {
            for (int value : values) {
                root = insert(root, value);
            }
        }
        return root;
    }

    private static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    private static void inOrder(Node node, List<Integer> keys) {
        while (node != null) {
            inOrder(node.left, keys);
            keys.add(node.key);
            node = node.right;
        }
    }

    private static void collectRange(Node node, int low, int high, List<Integer> keys) {
        if (node == null) {
            return;
        }
        if (node.key > low) {
            collectRange(node.left, low, high, keys);
        }
        if (node.key >= low && node.key <= high) {
            keys.add(node.key);
        }
        if (node.key < high) {
            collectRange(node.right, low, high, keys);
        }
    }

    private static final class Node {
        final int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        System.out.println(exerciseOne(values));
        System.out.println(exerciseTwo(values, 60));
        System.out.println(java.util.Arrays.toString(exerciseThree(values, 25, 65)));
    }
}
