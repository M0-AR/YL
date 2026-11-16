// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._06;

public class Exercise_25_06 {
	public static void main(String[] args) {
		Integer[] numbers = {60, 55, 45, 47, 59, 100, 76, 107, 101};


		BST<Integer> intTree = new BST<>(numbers);


		System.out.println("Number of leaf nodes: " +
			intTree.getNumberOfLeaves());
	}
}
