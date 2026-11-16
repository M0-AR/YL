package Exercise_40;

import java.util.ArrayList;
import java.util.List;

public class Chapter40 {
    public static final class Node {
        private final List<Integer> keys = new ArrayList<>();
        private final List<Node> children = new ArrayList<>();

        public List<Integer> keys() {
            return List.copyOf(keys);
        }

        public List<Node> children() {
            return List.copyOf(children);
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }
    }

    private static final int MAX_KEYS = 3;

    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        int index = 0;
        while (index < root.keys.size() && key > root.keys.get(index)) {
            index++;
        }
        if (index < root.keys.size() && root.keys.get(index) == key) {
            return true;
        }
        if (root.isLeaf()) {
            return false;
        }
        return search(root.children.get(index), key);
    }

    public static Node insert(Node root, int key) {
        if (root == null) {
            Node node = new Node();
            node.keys.add(key);
            return node;
        }
        if (search(root, key)) {
            return root;
        }
        if (root.keys.size() == MAX_KEYS) {
            Node newRoot = new Node();
            newRoot.children.add(root);
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key);
        return root;
    }

    public static boolean validate(Node root) {
        if (root == null) {
            return true;
        }
        if (root.keys.isEmpty()) {
            return false;
        }
        return validateNode(root, null, null, 0, new int[] {-1});
    }

    private static void insertNonFull(Node node, int key) {
        if (node.isLeaf()) {
            int index = 0;
            while (index < node.keys.size() && node.keys.get(index) < key) {
                index++;
            }
            node.keys.add(index, key);
            return;
        }
        int index = 0;
        while (index < node.keys.size() && key > node.keys.get(index)) {
            index++;
        }
        if (index < node.keys.size() && node.keys.get(index) == key) {
            return;
        }
        Node child = node.children.get(index);
        if (child.keys.size() == MAX_KEYS) {
            splitChild(node, index);
            int median = node.keys.get(index);
            if (key == median) {
                return;
            }
            if (key > median) {
                index++;
            }
        }
        insertNonFull(node.children.get(index), key);
    }

    private static void splitChild(Node parent, int index) {
        Node left = parent.children.get(index);
        Node right = new Node();
        int median = left.keys.remove(1);
        right.keys.add(left.keys.remove(1));
        if (!left.isLeaf()) {
            right.children.add(left.children.remove(2));
            right.children.add(left.children.remove(2));
        }
        parent.keys.add(index, median);
        parent.children.add(index + 1, right);
    }

    private static boolean validateNode(Node node, Integer min, Integer max, int depth, int[] leafDepth) {
        if (node.keys.isEmpty() || node.keys.size() > MAX_KEYS) {
            return false;
        }
        for (int index = 0; index < node.keys.size(); index++) {
            int key = node.keys.get(index);
            if (min != null && key <= min) {
                return false;
            }
            if (max != null && key >= max) {
                return false;
            }
            if (index > 0 && node.keys.get(index - 1) >= key) {
                return false;
            }
        }
        if (node.isLeaf()) {
            if (leafDepth[0] == -1) {
                leafDepth[0] = depth;
            }
            return leafDepth[0] == depth;
        }
        if (node.children.size() != node.keys.size() + 1) {
            return false;
        }
        for (int index = 0; index < node.children.size(); index++) {
            Integer childMin = index == 0 ? min : node.keys.get(index - 1);
            Integer childMax = index == node.keys.size() ? max : node.keys.get(index);
            if (!validateNode(node.children.get(index), childMin, childMax, depth + 1, leafDepth)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int key : new int[] {10, 20, 5, 6, 12, 30, 7, 17, 3, 4, 25, 40}) {
            root = insert(root, key);
        }
        System.out.println(root.keys());
        System.out.println(search(root, 17));
        System.out.println(search(root, 99));
        System.out.println(validate(root));
    }
}
