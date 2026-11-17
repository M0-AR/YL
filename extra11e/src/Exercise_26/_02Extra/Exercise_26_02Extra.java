package Exercise_26._02Extra;

import Exercise_26._commonExtra.AVLTree;

public class Exercise_26_02Extra {
	public static void main(String[] args) {
		AVLTree<String> tree = new AVLTree<>();
		tree.insert("George");
		tree.insert("Michael");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");

		System.out.println(tree.isBalanced());
	}
}
