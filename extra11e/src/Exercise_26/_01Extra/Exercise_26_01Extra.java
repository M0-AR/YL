package Exercise_26._01Extra;

import Exercise_26._commonExtra.AVLTree;

import java.util.ArrayList;
import java.util.Comparator;

public class Exercise_26_01Extra {
	public static void main(String[] args) {
		AVLTree<String> tree = new AVLTree<>();
		tree.insert("George");
		tree.insert("Michael");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");

		System.out.print("Inorder (sorted): ");
		tree.inorder();
		System.out.print("\nPostorder: ");
		tree.postorder();
		System.out.print("\nPreorder: ");
		tree.preorder();
		System.out.print("\nThe number of nodes is " + tree.getSize());

		System.out.print("\nIs Peter in the tree? "
			+ tree.search("Peter"));

		System.out.print("\nA path from the root to Peter is: ");
		ArrayList<AVLTree.TreeNode<String>> path = tree.path("Peter");
		for (AVLTree.TreeNode<String> node : path) {
			System.out.print(node.element + " ");
		}

		Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
		AVLTree<Integer> intTree = new AVLTree<>(numbers,
			Comparator.reverseOrder());
		System.out.print("\nInorder (sorted): ");
		intTree.inorder();
		System.out.println();
	}
}
