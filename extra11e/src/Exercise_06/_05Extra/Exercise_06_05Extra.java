package Exercise_06._05Extra;

import java.util.Scanner;

public class Exercise_06_05Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String text = input.nextLine();
		System.out.println("The new string is " + title(text));
	}

	public static String title(String s) {
		StringBuilder result = new StringBuilder(s);
		boolean startOfWord = true;

		for (int i = 0; i < result.length(); i++) {
			char ch = result.charAt(i);
			if (startOfWord && Character.isLowerCase(ch)) {
				result.setCharAt(i, Character.toUpperCase(ch));
			}
			startOfWord = ch == ' ';
		}

		return result.toString();
	}
}
