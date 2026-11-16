// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._04;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise_25_04 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Integer[] numbers = new Integer[10];


		System.out.print("Enter 10 integers: ");
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = input.nextInt();


		BST<Integer> intTree = new BST<>(numbers);


		System.out.print("Tree preorder: ");
		intTree.preorder();
		System.out.println();
	}
}
