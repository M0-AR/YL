package atlas.advanced;

import java.util.ArrayList;
import java.util.List;

public final class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final class Node {
        int key;
        Node left;
        Node right;
        boolean color;

        Node(int key, boolean color) {
            this.key = key;
            this.color = color;
        }
    }

    private Node root;
    private int size;

    public boolean insert(int key) {
        int before = size;
        root = insert(root, key);
        if (root != null) root.color = BLACK;
        return size > before;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            size++;
            return new Node(key, RED);
        }
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        return node;
    }

    public boolean contains(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.key) return true;
            current = key < current.key ? current.left : current.right;
        }
        return false;
    }

    private Node rotateLeft(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        pivot.color = node.color;
        node.color = RED;
        return pivot;
    }

    private Node rotateRight(Node node) {
        Node pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        pivot.color = node.color;
        node.color = RED;
        return pivot;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean checkInvariants() {
        return root == null || (root.color == BLACK && check(root) != -1);
    }

    private int check(Node node) {
        if (node == null) return 1;
        if (isRed(node.right)) return -1;
        if (isRed(node) && (isRed(node.left) || isRed(node.right))) return -1;

        int left = check(node.left);
        int right = check(node.right);
        if (left == -1 || right == -1 || left != right) return -1;
        return left + (isRed(node) ? 0 : 1);
    }

    public List<Integer> inorder() {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        return values;
    }

    private void inorder(Node node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.key);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        for (int key = 1; key <= 100; key++) tree.insert(key);
        System.out.println("size=" + tree.size() + ", height=" + tree.height());
        System.out.println("invariants=" + tree.checkInvariants());
    }
}
