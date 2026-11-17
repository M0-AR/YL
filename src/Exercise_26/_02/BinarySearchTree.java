package Exercise_26._02;

public class BinarySearchTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean search(int value) {
        Node current = root;
        while (current != null) {
            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean insert(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        Node current = root;
        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    size++;
                    return true;
                }
                current = current.left;
            } else if (value > current.value) {
                if (current.right == null) {
                    current.right = new Node(value);
                    size++;
                    return true;
                }
                current = current.right;
            } else {
                return false;
            }
        }
    }

    public boolean delete(int value) {
        int before = size;
        root = delete(root, value);
        return size < before;
    }

    private Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }
            Node successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.value = successor.value;
            node.right = delete(node.right, successor.value);
        }
        return node;
    }
}
