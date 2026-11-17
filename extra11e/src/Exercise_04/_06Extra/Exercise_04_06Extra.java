package Exercise_04._06Extra;

import java.util.Scanner;

public class Exercise_04_06Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a four-digit binary string: ");
		String binary = input.nextLine();

		if (binary.length() != 4) {
			System.out.println("Please enter exactly four binary digits");
		}
		else {
			int value = Integer.parseInt(binary, 2);
			System.out.println("The decimal number for " + binary + " is " + value);
		}
	}
}
