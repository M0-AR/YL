package Exercise_25._05Extra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Exercise_25_05Extra {
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
		List<Integer> levelOrder = levelOrder(root);
		System.out.println("Level order: " + levelOrder);
		if (!levelOrder.equals(List.of(50, 25, 75, 12, 30, 70, 80, 10, 110))) {
			throw new IllegalStateException("Unexpected level order");
		}
		System.out.println("Exercise_25_05Extra OK");
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

	static List<Integer> levelOrder(Node root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			result.add(node.value);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return result;
	}
}
