package Exercise_25._common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BST<E extends Comparable<E>> implements Iterable<E>, Cloneable {
    private TreeNode<E> root;
    private int size;

    public BST() {
    }

    public BST(E[] objects) {
        if (objects != null) {
            for (E object : objects) {
                insert(object);
            }
        }
    }

    public boolean search(E e) {
        return getNode(e) != null;
    }

    public boolean insert(E e) {
        Objects.requireNonNull(e, "element");
        if (root == null) {
            root = new TreeNode<>(e, null);
            size++;
            return true;
        }
        TreeNode<E> current = root;
        while (true) {
            int comparison = e.compareTo(current.element);
            if (comparison == 0) {
                return false;
            }
            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new TreeNode<>(e, current);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TreeNode<>(e, current);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    public boolean delete(E e) {
        TreeNode<E> node = getNode(e);
        if (node == null) {
            return false;
        }
        if (node.left != null && node.right != null) {
            TreeNode<E> predecessor = node.left;
            while (predecessor.right != null) {
                predecessor = predecessor.right;
            }
            node.element = predecessor.element;
            node = predecessor;
        }
        TreeNode<E> child = node.left != null ? node.left : node.right;
        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        if (child != null) {
            child.parent = node.parent;
        }
        size--;
        return true;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public boolean isLeaf(E e) {
        TreeNode<E> node = getNode(e);
        return node != null && node.left == null && node.right == null;
    }

    public ArrayList<E> getPath(E e) {
        TreeNode<E> node = getNode(e);
        ArrayList<E> path = new ArrayList<>();
        while (node != null) {
            path.add(node.element);
            node = node.parent;
        }
        return path;
    }

    public void inorder() {
        for (E e : inorderList()) {
            System.out.print(e + " ");
        }
    }

    public void preorder() {
        for (E e : preorderList()) {
            System.out.print(e + " ");
        }
    }

    public void postorder() {
        for (E e : postorderList()) {
            System.out.print(e + " ");
        }
    }

    public void breadthFirstTraversal() {
        if (root == null) {
            return;
        }
        Deque<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.remove();
            System.out.print(node.element + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isFullBST() {
        return size == (int) Math.pow(2, height()) - 1;
    }

    public int getNumberOfLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaves(node.left) + countLeaves(node.right);
    }

    public int getNumberOfNonLeaves() {
        return size - getNumberOfLeaves();
    }

    public ArrayList<E> inorderList() {
        ArrayList<E> list = new ArrayList<>(size);
        collectInorder(root, list);
        return list;
    }

    private void collectInorder(TreeNode<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        collectInorder(node.left, list);
        list.add(node.element);
        collectInorder(node.right, list);
    }

    public ArrayList<E> preorderList() {
        ArrayList<E> list = new ArrayList<>(size);
        collectPreorder(root, list);
        return list;
    }

    private void collectPreorder(TreeNode<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        list.add(node.element);
        collectPreorder(node.left, list);
        collectPreorder(node.right, list);
    }

    public ArrayList<E> postorderList() {
        ArrayList<E> list = new ArrayList<>(size);
        collectPostorder(root, list);
        return list;
    }

    private void collectPostorder(TreeNode<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        collectPostorder(node.left, list);
        collectPostorder(node.right, list);
        list.add(node.element);
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    public Iterator<E> preorderIterator() {
        return Collections.unmodifiableList(preorderList()).iterator();
    }

    public Iterator<E> postorderIterator() {
        return Collections.unmodifiableList(postorderList()).iterator();
    }

    public ListIterator<E> listIterator() {
        return new BSTListIterator(0);
    }

    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return new BSTListIterator(index);
    }

    private TreeNode<E> getNode(E e) {
        Objects.requireNonNull(e, "element");
        TreeNode<E> current = root;
        while (current != null) {
            int comparison = e.compareTo(current.element);
            if (comparison == 0) {
                return current;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return null;
    }

    private void rebuildFromSorted(ArrayList<E> elements) {
        root = null;
        size = 0;
        root = buildBalanced(elements, 0, elements.size() - 1, null);
    }

    private TreeNode<E> buildBalanced(ArrayList<E> elements, int low, int high, TreeNode<E> parent) {
        if (low > high) {
            return null;
        }
        int middle = (low + high) >>> 1;
        TreeNode<E> node = new TreeNode<>(elements.get(middle), parent);
        size++;
        node.left = buildBalanced(elements, low, middle - 1, node);
        node.right = buildBalanced(elements, middle + 1, high, node);
        return node;
    }

    private final class BSTListIterator implements ListIterator<E> {
        private final ArrayList<E> elements;
        private int cursor;
        private int lastReturned = -1;

        private BSTListIterator(int index) {
            elements = inorderList();
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor < elements.size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = cursor;
            return elements.get(cursor++);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturned = --cursor;
            return elements.get(cursor);
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            elements.remove(lastReturned);
            if (lastReturned < cursor) {
                cursor--;
            }
            lastReturned = -1;
            rebuildFromSorted(elements);
        }

        @Override
        public void set(E e) {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            Objects.requireNonNull(e, "element");
            E previous = elements.get(lastReturned);
            if (previous.equals(e)) {
                return;
            }
            if (elements.contains(e)) {
                throw new IllegalArgumentException("duplicate element");
            }
            elements.remove(lastReturned);
            int insertion = -Collections.binarySearch(elements, e) - 1;
            elements.add(insertion, e);
            cursor = insertion + 1;
            lastReturned = insertion;
            rebuildFromSorted(elements);
        }

        @Override
        public void add(E e) {
            Objects.requireNonNull(e, "element");
            int position = Collections.binarySearch(elements, e);
            if (position >= 0) {
                throw new IllegalArgumentException("duplicate element");
            }
            int insertion = -position - 1;
            elements.add(insertion, e);
            cursor = insertion + 1;
            lastReturned = -1;
            rebuildFromSorted(elements);
        }
    }

    @Override
    public BST<E> clone() {
        BST<E> copy = new BST<>();
        copy.root = copyNode(root, null);
        copy.size = size;
        return copy;
    }

    private TreeNode<E> copyNode(TreeNode<E> node, TreeNode<E> parent) {
        if (node == null) {
            return null;
        }
        TreeNode<E> copy = new TreeNode<>(node.element, parent);
        copy.left = copyNode(node.left, copy);
        copy.right = copyNode(node.right, copy);
        return copy;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BST<?> other)) {
            return false;
        }
        return size == other.size && inorderList().equals(other.inorderList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inorderList());
    }

    public static final class TreeNode<E> {
        private E element;
        private TreeNode<E> left;
        private TreeNode<E> right;
        private TreeNode<E> parent;

        private TreeNode(E element, TreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public E getElement() {
            return element;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public TreeNode<E> getRight() {
            return right;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
