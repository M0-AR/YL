// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_18._22;

import java.util.Scanner;

public class Exercise_18_22 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a decimal number: ");
		int value = input.nextInt();


		System.out.println("The hex equivalent of "
			+ value + " is " + dec2Hex(value));
	}



	public static String dec2Hex(int value) {
		String result = "";
		return dec2Hex(value, result);
	}


	public static String dec2Hex(int value, String result) {
		int r = value % 16;
		String remainder = r >= 10 ?
			String.valueOf((char)('A' + r % 10)) : String.valueOf(r);

		if (value / 16 == 0)
			return remainder + result;
		else
			return dec2Hex(value / 16, remainder + result);
	}
}
