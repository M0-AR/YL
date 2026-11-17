package Exercise_06._03Extra;

import java.util.Scanner;

public class Exercise_06_03Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an integer: ");
		int value = input.nextInt();
		System.out.println("The binary value is " + decimalToBinary(value));
	}

	public static String decimalToBinary(int value) {
		if (value == 0) {
			return "0";
		}

		String binary = "";
		while (value > 0) {
			binary = (value % 2) + binary;
			value /= 2;
		}
		return binary;
	}
}
