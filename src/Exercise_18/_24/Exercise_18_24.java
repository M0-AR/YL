// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_18._24;

import java.util.Scanner;

public class Exercise_18_24 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a hex string: ");
		String hexString = input.next();


		System.out.println("The decimal equivalent of "
			+ hexString + " is " + hex2Dec(hexString));
	}



	public static int hex2Dec(String hexString) {
		int result = 0;
		int index = 0;
		int n = hexString.length() - 1;
		return hex2Dec(hexString, index, n, result);
	}


	public static int hex2Dec(String hexString, int index, int n, int result) {
		int dec = 0;
		if (hexString.charAt(index) >= 'A' &&
			 hexString.charAt(index) <= 'F') {
			dec = (hexString.charAt(index) - 'A') + 10;
		}
		else {
			dec = Integer.parseInt(String.valueOf(hexString.charAt(index)));
		}
		dec *= (int)Math.pow(16, n);

		if (n == 0)
			return result + dec;
		else
			return hex2Dec(hexString, index + 1, n - 1, dec + result);
	}
}
