package Exercise_25._04Extra;

import java.util.ArrayList;
import java.util.List;

public class Exercise_25_04Extra {
	static class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		int[] values = {50, 25, 75, 12, 30, 70, 80, 110, 10};
		Node root = null;
		for (int value : values) {
			root = insert(root, value);
		}
		List<Integer> inorder = new ArrayList<>();
		inorder(root, inorder);
		int height = height(root);
		System.out.println("Inorder: " + inorder);
		System.out.println("Height: " + height);
		if (!inorder.equals(List.of(10, 12, 25, 30, 50, 70, 75, 80, 110))) {
			throw new IllegalStateException("Unexpected inorder");
		}
		if (height != 4) {
			throw new IllegalStateException("Unexpected height: " + height);
		}
		System.out.println("Exercise_25_04Extra OK");
	}

	static Node insert(Node node, int value) {
		if (node == null) {
			return new Node(value);
		}
		if (value < node.value) {
			node.left = insert(node.left, value);
		}
		else if (value > node.value) {
			node.right = insert(node.right, value);
		}
		return node;
	}

	static void inorder(Node node, List<Integer> values) {
		if (node == null) {
			return;
		}
		inorder(node.left, values);
		values.add(node.value);
		inorder(node.right, values);
	}

	static int height(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}
}
