// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._05;

import java.util.Scanner;
public class Exercise_25_05 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Integer[] numbers = new Integer[10];


		System.out.print("Enter 10 integers: ");
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = input.nextInt();


		BST<Integer> intTree = new BST<>(numbers);


		System.out.print("Tree postorder: ");
		intTree.postorder();
		System.out.println();
	}
}
