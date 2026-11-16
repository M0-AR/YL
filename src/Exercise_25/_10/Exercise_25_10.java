// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._10;

import java.util.Iterator;

public class Exercise_25_10 {
	public static void main(String[] args) {
		BST<String> tree = new BST<>();
		tree.insert("George");
		tree.insert("Michael");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");



		Iterator<String> iterator = tree.preorderIterator();



		while (iterator.hasNext())
			System.out.print(iterator.next().toUpperCase() + " ");
		System.out.println();
	}
}
