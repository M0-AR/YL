package atlas.collections;

import java.util.ArrayList;
import java.util.List;

public final class BinarySearchTree {
    private static final class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;
    private int size;

    public boolean insert(int key) {
        int before = size;
        root = insert(root, key);
        return size > before;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            size++;
            return new Node(key);
        }
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
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

    public boolean delete(int key) {
        int before = size;
        root = delete(root, key);
        return size < before;
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }
            Node successor = minNode(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }
        return node;
    }

    public int min() {
        if (root == null) throw new IllegalStateException("empty tree");
        return minNode(root).key;
    }

    public int max() {
        if (root == null) throw new IllegalStateException("empty tree");
        Node current = root;
        while (current.right != null) current = current.right;
        return current.key;
    }

    private Node minNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isBalanced() {
        return balanced(root) != -2;
    }

    private int balanced(Node node) {
        if (node == null) return -1;
        int left = balanced(node.left);
        if (left == -2) return -2;
        int right = balanced(node.right);
        if (right == -2) return -2;
        if (Math.abs(left - right) > 1) return -2;
        return 1 + Math.max(left, right);
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
        BinarySearchTree tree = new BinarySearchTree();
        for (int key : new int[]{50, 30, 70, 20, 40, 60, 80}) tree.insert(key);
        System.out.println(tree.inorder());
        System.out.println("height=" + tree.height() + ", balanced=" + tree.isBalanced());
    }
}
