package Exercise_06._04Extra;

import java.util.Scanner;

public class Exercise_06_04Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a binary number string: ");
		String binary = input.nextLine();
		System.out.println("The decimal value is " + binaryToDecimal(binary));
	}

	public static int binaryToDecimal(String binaryString) {
		int value = 0;
		for (int i = 0; i < binaryString.length(); i++) {
			value = value * 2 + (binaryString.charAt(i) - '0');
		}
		return value;
	}
}
