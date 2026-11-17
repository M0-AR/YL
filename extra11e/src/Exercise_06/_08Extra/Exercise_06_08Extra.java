package Exercise_06._08Extra;

import java.util.Scanner;

public class Exercise_06_08Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string s: ");
		String s = input.nextLine();
		System.out.print("Enter an integer b: ");
		int b = input.nextInt();

		System.out.println("The hashCode for " + s + " is " + hashCode(s, b));
	}

	public static int hashCode(String s, int b) {
		int code = 0;
		for (int i = 0; i < s.length(); i++) {
			code = code * b + s.charAt(i);
		}
		return code;
	}
}
