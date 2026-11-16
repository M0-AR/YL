// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_18._23;

import java.util.Scanner;

public class Exercise_18_23 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a binary string: ");
		String binaryString = input.next();


		System.out.println("The decimal equivalent of "
			+ binaryString + " is " + bin2Dec(binaryString));
	}



	public static int bin2Dec(String binaryString) {
		int index = 0;
		int n = binaryString.length() - 1;
		int result = 0;
		return bin2Dec(binaryString, index, n, result);
	}


	public static int bin2Dec(String binaryString, int index, int n, int result) {
		result += (Integer.parseInt(binaryString.charAt(index) + "") *
				(int)Math.pow(2, n));
		if (n == 0)
			return result;
		else
			return bin2Dec(binaryString, index + 1, n - 1, result);
	}
}
