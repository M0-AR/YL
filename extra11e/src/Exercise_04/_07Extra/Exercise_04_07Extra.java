package Exercise_04._07Extra;

import java.util.Scanner;

public class Exercise_04_07Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a decimal number between 0 and 15: ");
		int value = input.nextInt();

		System.out.println("The binary number for " + value + " is "
			+ Integer.toBinaryString(value));
	}
}
