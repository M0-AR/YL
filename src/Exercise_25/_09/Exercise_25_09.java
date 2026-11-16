// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._09;

public class Exercise_25_09 {
	public static void main(String[] args) throws Exception {
		Integer[] numbers = {60, 55, 45, 47, 59, 100, 76, 107, 101};
		Integer[] numbers2 = {60, 55, 45, 48, 59, 100, 76, 107, 101};


		BST<Integer> intTree = new BST<>(numbers);
		BST<Integer> intTree2 = new BST<>(numbers2);


		System.out.print("intTree: ");
		intTree.preorder();
		System.out.print("\nintTree2: ");
		intTree2.preorder();


		BST<Integer> intTreeCopy = intTree.clone();


		System.out.println("\nIs intTree equal to intTree2? " +
			intTree.equals(intTree2));
		System.out.println("Is intTree equal to intTreeCopy? " +
			intTree.equals(intTreeCopy));


		System.out.print("intTreeCopy: ");
		intTreeCopy.preorder();
		System.out.println();
	}
}
