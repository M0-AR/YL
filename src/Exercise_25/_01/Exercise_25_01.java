// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._01;

public class Exercise_25_01 {
	public static void main(String[] args) {

		BST<String> tree = new BST<>();
		tree.insert("George");
		tree.insert("Micheal");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");


		System.out.print("\nBreadth-first: ");
		tree.breadthFirstTraversal();


		System.out.print("\nHeight of tree: ");
		System.out.println(tree.height());


		Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
		BST<Integer> intTree = new BST<>(numbers);


		intTree.breadthFirstTraversal();


		System.out.print("\nHeight of tree: ");
		System.out.println(intTree.height());
	}
}
