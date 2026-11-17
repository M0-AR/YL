package Exercise_25._commonExtra;

import java.util.Objects;

public class BST<E extends Comparable<E>> {
	private TreeNode<E> root;
	private int size;

	public boolean search(E e) {
		return find(e) != null;
	}

	public boolean insert(E e) {
		Objects.requireNonNull(e);
		if (root == null) {
			root = new TreeNode<>(e);
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
					current.left = new TreeNode<>(e);
					size++;
					return true;
				}
				current = current.left;
			}
			else {
				if (current.right == null) {
					current.right = new TreeNode<>(e);
					size++;
					return true;
				}
				current = current.right;
			}
		}
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public int getSize() {
		return size;
	}

	private TreeNode<E> find(E e) {
		Objects.requireNonNull(e);
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

	public static final class TreeNode<E> {
		private E element;
		private TreeNode<E> left;
		private TreeNode<E> right;

		private TreeNode(E element) {
			this.element = element;
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
	}
}
