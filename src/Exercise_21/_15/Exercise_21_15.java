// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._15;

import java.util.*;

public class Exercise_21_15 {
	public static void main(String[] args) {
		int number1 = (int)(Math.random() * 10);
		int number2 = (int)(Math.random() * 10);


		Scanner input = new Scanner(System.in);


		Set<Integer> answers = new HashSet<>();

		System.out.print(
			"What is " + number1 + " + " + number2 + "? ");
		int answer = input.nextInt();

		while (number1 + number2 != answer) {
			if (answers.contains(answer))
				System.out.println("You already entered " + answer);
			else {
				System.out.print("Wrong answer. Try again. What is "
					+ number1 + " + " + number2 + "? ");
				answers.add(answer);
			}
			answer = input.nextInt();
		}

		System.out.println("You got it!");
	}
}
