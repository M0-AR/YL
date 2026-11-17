package Exercise_04._05Extra;

import java.util.Scanner;

public class Exercise_04_05Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String text = input.nextLine();

		if (text.length() == 0) {
			System.out.println("An empty string");
		}
		else {
			System.out.println("The last character is "
				+ text.charAt(text.length() - 1));
		}
	}
}
