// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._10;

public class Exercise_12_10 {

	public static void main(String[] args) {

		int length = 1200000000;
		try {
			byte[] array = new byte[length];
		}
		catch (OutOfMemoryError ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println("Error handled program continues... ");
	}
}
