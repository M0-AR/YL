package Exercise_26._commonExtra;

import java.util.ArrayList;
import java.util.Comparator;

public class AVLTree<E> {
	private Comparator<E> comparator;
	private TreeNode<E> root;
	private int size;

	@SuppressWarnings("unchecked")
	public AVLTree() {
		comparator = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
	}

	public AVLTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public AVLTree(E[] objects) {
		this();
		for (E object : objects) {
			insert(object);
		}
	}

	public AVLTree(E[] objects, Comparator<E> comparator) {
		this(comparator);
		for (E object : objects) {
			insert(object);
		}
	}

	public boolean insert(E e) {
		int oldSize = size;
		root = insert(root, e);
		return size > oldSize;
	}

	private TreeNode<E> insert(TreeNode<E> node, E e) {
		if (node == null) {
			size++;
			return new TreeNode<>(e);
		}

		int comparison = comparator.compare(e, node.element);
		if (comparison < 0) {
			node.left = insert(node.left, e);
		}
		else if (comparison > 0) {
			node.right = insert(node.right, e);
		}
		else {
			return node;
		}

		updateHeight(node);
		return balance(node);
	}

	private TreeNode<E> balance(TreeNode<E> node) {
		int balanceFactor = height(node.left) - height(node.right);
		if (balanceFactor > 1) {
			if (height(node.left.left) < height(node.left.right)) {
				node.left = rotateLeft(node.left);
			}
			return rotateRight(node);
		}
		if (balanceFactor < -1) {
			if (height(node.right.right) < height(node.right.left)) {
				node.right = rotateRight(node.right);
			}
			return rotateLeft(node);
		}
		return node;
	}

	private TreeNode<E> rotateRight(TreeNode<E> y) {
		TreeNode<E> x = y.left;
		TreeNode<E> middle = x.right;
		x.right = y;
		y.left = middle;
		updateHeight(y);
		updateHeight(x);
		return x;
	}

	private TreeNode<E> rotateLeft(TreeNode<E> x) {
		TreeNode<E> y = x.right;
		TreeNode<E> middle = y.left;
		y.left = x;
		x.right = middle;
		updateHeight(x);
		updateHeight(y);
		return y;
	}

	private void updateHeight(TreeNode<E> node) {
		node.height = 1 + Math.max(height(node.left), height(node.right));
	}

	private int height(TreeNode<E> node) {
		return node == null ? -1 : node.height;
	}

	public boolean search(E e) {
		TreeNode<E> current = root;
		while (current != null) {
			int comparison = comparator.compare(e, current.element);
			if (comparison == 0) {
				return true;
			}
			current = comparison < 0 ? current.left : current.right;
		}
		return false;
	}

	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> path = new ArrayList<>();
		TreeNode<E> current = root;
		while (current != null) {
			path.add(current);
			int comparison = comparator.compare(e, current.element);
			if (comparison == 0) {
				break;
			}
			current = comparison < 0 ? current.left : current.right;
		}
		return path;
	}

	public boolean isBalanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(TreeNode<E> node) {
		if (node == null) {
			return true;
		}
		return Math.abs(height(node.left) - height(node.right)) <= 1
			&& isBalanced(node.left) && isBalanced(node.right);
	}

	public void inorder() {
		inorder(root);
	}

	private void inorder(TreeNode<E> node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.element + " ");
			inorder(node.right);
		}
	}

	public void preorder() {
		preorder(root);
	}

	private void preorder(TreeNode<E> node) {
		if (node != null) {
			System.out.print(node.element + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}

	public void postorder() {
		postorder(root);
	}

	private void postorder(TreeNode<E> node) {
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.element + " ");
		}
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public int getSize() {
		return size;
	}

	public static final class TreeNode<E> {
		public E element;
		private TreeNode<E> left;
		private TreeNode<E> right;
		private int height;

		private TreeNode(E element) {
			this.element = element;
		}

		public TreeNode<E> getLeft() {
			return left;
		}

		public TreeNode<E> getRight() {
			return right;
		}
	}
}
