// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._09;

import Exercise_12._07.Exercise_12_07;

import java.util.Scanner;

public class TestBin2Dec {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a binary: ");
		try {
			System.out.println(Exercise_12_07.bin2Dec(input.nextLine()));
		}
		catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
