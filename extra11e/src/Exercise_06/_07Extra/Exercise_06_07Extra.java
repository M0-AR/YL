package Exercise_06._07Extra;

import java.util.Scanner;

public class Exercise_06_07Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String text = input.nextLine();
		System.out.println("The new string is " + swapCase(text));
	}

	public static String swapCase(String s) {
		StringBuilder result = new StringBuilder(s);
		for (int i = 0; i < result.length(); i++) {
			char ch = result.charAt(i);
			if (Character.isUpperCase(ch)) {
				result.setCharAt(i, Character.toLowerCase(ch));
			}
			else if (Character.isLowerCase(ch)) {
				result.setCharAt(i, Character.toUpperCase(ch));
			}
		}
		return result.toString();
	}
}
