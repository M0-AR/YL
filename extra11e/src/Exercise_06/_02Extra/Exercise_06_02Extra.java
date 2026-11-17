package Exercise_06._02Extra;

import java.util.Scanner;

public class Exercise_06_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a binary number: ");
		String binary = input.nextLine();
		System.out.println("The hex value is " + binaryToHex(binary));
	}

	public static String binaryToHex(String binaryValue) {
		int decimal = 0;
		for (int i = 0; i < binaryValue.length(); i++) {
			decimal = decimal * 2 + (binaryValue.charAt(i) - '0');
		}

		if (decimal == 0) {
			return "0";
		}

		String hex = "";
		while (decimal > 0) {
			int digit = decimal % 16;
			hex = toHexChar(digit) + hex;
			decimal /= 16;
		}
		return hex;
	}

	private static char toHexChar(int digit) {
		if (digit < 10) {
			return (char)('0' + digit);
		}
		return (char)('A' + digit - 10);
	}
}
