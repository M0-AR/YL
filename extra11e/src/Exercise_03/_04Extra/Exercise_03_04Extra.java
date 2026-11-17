package Exercise_03._04Extra;

import java.util.Scanner;

public class Exercise_03_04Extra {
	public static void main(String[] args) {
		int number1 = (int)(Math.random() * 10);
		int number2 = (int)(Math.random() * 10);
		if (number1 < number2) {
			int temp = number1;
			number1 = number2;
			number2 = temp;
		}

		System.out.print("What is " + number1 + " - " + number2 + "? ");
		Scanner input = new Scanner(System.in);
		int answer = input.nextInt();

		int choice;
		if (number1 - number2 == answer) {
			choice = (int)(Math.random() * 3);
			switch (choice) {
				case 0: System.out.println("excellent"); break;
				case 1: System.out.println("correct"); break;
				default: System.out.println("way to go");
			}
		}
		else {
			choice = (int)(Math.random() * 3);
			switch (choice) {
				case 0: System.out.println("incorrect"); break;
				case 1: System.out.println("wrong"); break;
				default: System.out.println("not right");
			}
			System.out.println(number1 + " - " + number2 + " should be "
				+ (number1 - number2));
		}
	}
}
