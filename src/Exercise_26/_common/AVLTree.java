package Exercise_26._common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AVLTree<E extends Comparable<E>> implements Iterable<E> {
    public static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;
        public int height = 1;
        public int size = 1;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    private Node<E> root;

    public Node<E> getRoot() {
        return root;
    }

    public int getSize() {
        return sizeOf(root);
    }

    public int getHeight() {
        return heightOf(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean search(E element) {
        return findNode(element) != null;
    }

    public boolean insert(E element) {
        Objects.requireNonNull(element, "element");
        int before = getSize();
        root = insert(root, element, null);
        root.parent = null;
        return getSize() > before;
    }

    public boolean delete(E element) {
        if (element == null || root == null) {
            return false;
        }
        int before = getSize();
        root = delete(root, element);
        if (root != null) {
            root.parent = null;
        }
        return getSize() < before;
    }

    public E find(int k) {
        if (k < 1 || k > getSize()) {
            return null;
        }
        return find(k, root);
    }

    public List<E> pathTo(E element) {
        List<E> path = new ArrayList<>();
        Node<E> current = root;
        while (current != null) {
            path.add(current.element);
            int comparison = element.compareTo(current.element);
            if (comparison == 0) {
                break;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return path;
    }

    public List<E> inorderList() {
        List<E> values = new ArrayList<>();
        inorder(root, values);
        return values;
    }

    public void clear() {
        root = null;
    }

    public static int heightOf(Node<?> node) {
        return node == null ? 0 : node.height;
    }

    public static int sizeOf(Node<?> node) {
        return node == null ? 0 : node.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final Iterator<E> values = inorderList().iterator();

            @Override
            public boolean hasNext() {
                return values.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return values.next();
            }
        };
    }

    private Node<E> findNode(E element) {
        Node<E> current = root;
        while (current != null) {
            int comparison = element.compareTo(current.element);
            if (comparison == 0) {
                return current;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return null;
    }

    private Node<E> insert(Node<E> node, E element, Node<E> parent) {
        if (node == null) {
            return new Node<>(element, parent);
        }
        int comparison = element.compareTo(node.element);
        if (comparison < 0) {
            node.left = insert(node.left, element, node);
        } else if (comparison > 0) {
            node.right = insert(node.right, element, node);
        } else {
            return node;
        }
        return rebalance(node);
    }

    private Node<E> delete(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int comparison = element.compareTo(node.element);
        if (comparison < 0) {
            node.left = delete(node.left, element);
        } else if (comparison > 0) {
            node.right = delete(node.right, element);
        } else {
            if (node.left == null || node.right == null) {
                Node<E> child = node.left != null ? node.left : node.right;
                if (child != null) {
                    child.parent = node.parent;
                }
                return child;
            }
            Node<E> successor = minimum(node.right);
            node.element = successor.element;
            node.right = delete(node.right, successor.element);
        }
        return rebalance(node);
    }

    private Node<E> minimum(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private E find(int k, Node<E> node) {
        int leftSize = sizeOf(node.left);
        if (k <= leftSize) {
            return find(k, node.left);
        }
        if (k == leftSize + 1) {
            return node.element;
        }
        return find(k - leftSize - 1, node.right);
    }

    private void inorder(Node<E> node, List<E> values) {
        if (node == null) {
            return;
        }
        inorder(node.left, values);
        values.add(node.element);
        inorder(node.right, values);
    }

    private Node<E> rebalance(Node<E> node) {
        update(node);
        int balance = heightOf(node.left) - heightOf(node.right);
        if (balance > 1) {
            if (heightOf(node.left.left) < heightOf(node.left.right)) {
                node.left = rotateLeft(node.left);
                node.left.parent = node;
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (heightOf(node.right.right) < heightOf(node.right.left)) {
                node.right = rotateRight(node.right);
                node.right.parent = node;
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<E> rotateRight(Node<E> top) {
        Node<E> pivot = top.left;
        Node<E> middle = pivot.right;
        Node<E> parent = top.parent;

        pivot.parent = parent;
        pivot.right = top;
        top.parent = pivot;
        top.left = middle;
        if (middle != null) {
            middle.parent = top;
        }

        update(top);
        update(pivot);
        return pivot;
    }

    private Node<E> rotateLeft(Node<E> top) {
        Node<E> pivot = top.right;
        Node<E> middle = pivot.left;
        Node<E> parent = top.parent;

        pivot.parent = parent;
        pivot.left = top;
        top.parent = pivot;
        top.right = middle;
        if (middle != null) {
            middle.parent = top;
        }

        update(top);
        update(pivot);
        return pivot;
    }

    private void update(Node<E> node) {
        node.height = 1 + Math.max(heightOf(node.left), heightOf(node.right));
        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
    }
}
