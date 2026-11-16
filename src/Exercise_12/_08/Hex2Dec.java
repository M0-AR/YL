// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._08;

import java.util.Scanner;

public class Hex2Dec {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a hex number: ");
		String hex = input.nextLine();

		try {
			System.out.println("The decimal value for hex number "
				+ hex + " is " + hexToDecimal(hex.toUpperCase()));
		}
		catch (HexFormatException ex) {
			System.out.println(ex.getMessage());
		}
	}



	public static int hexToDecimal(String hex) throws HexFormatException {
		int decimalValue = 0;
		for (int i = 0; i < hex.length(); i++) {
			if (!((hex.charAt(i) >= '0' && hex.charAt(i) <= '9') ||
				 (hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F')))
				throw new HexFormatException(hex);
			char hexChar = hex.charAt(i);
			decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
		}

		return decimalValue;
	}

	public static int hexCharToDecimal(char ch) {
		if (ch >= 'A' && ch <= 'F')
			return 10 + ch - 'A';
		else
			return ch - '0';
	}
}
