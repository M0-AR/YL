package atlas.collections;

import java.util.ArrayList;
import java.util.List;

public final class AvlTree {
    private static final class Node {
        int key;
        int height = 1;
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
        else return node;
        return rebalance(node);
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
        return rebalance(node);
    }

    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = balance(node);

        if (balance > 1 && balance(node.left) >= 0) {
            return rotateRight(node);
        }
        if (balance > 1) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && balance(node.right) <= 0) {
            return rotateLeft(node);
        }
        if (balance < -1) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node root) {
        Node pivot = root.left;
        Node middle = pivot.right;
        pivot.right = root;
        root.left = middle;
        updateHeight(root);
        updateHeight(pivot);
        return pivot;
    }

    private Node rotateLeft(Node root) {
        Node pivot = root.right;
        Node middle = pivot.left;
        pivot.left = root;
        root.right = middle;
        updateHeight(root);
        updateHeight(pivot);
        return pivot;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int balance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
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

    public boolean checkInvariants() {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE) && isBalanced(root);
    }

    private boolean check(Node node, long low, long high) {
        if (node == null) return true;
        if (node.key <= low || node.key >= high) return false;
        if (Math.abs(balance(node)) > 1) return false;
        if (node.height != 1 + Math.max(height(node.left), height(node.right))) return false;
        return check(node.left, low, node.key) && check(node.right, node.key, high);
    }

    private boolean isBalanced(Node node) {
        if (node == null) return true;
        return Math.abs(balance(node)) <= 1
            && isBalanced(node.left)
            && isBalanced(node.right);
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
        AvlTree tree = new AvlTree();
        for (int key = 1; key <= 10; key++) tree.insert(key);
        System.out.println(tree.inorder());
        System.out.println("height=" + tree.height() + ", size=" + tree.size());
    }
}
