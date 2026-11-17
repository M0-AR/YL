package Exercise_07._04Extra;

import java.util.Scanner;

public class Exercise_07_04Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String text = input.nextLine();
		int[] counts = count(text);

		for (int digit = 0; digit < counts.length; digit++) {
			if (counts[digit] > 0) {
				System.out.println("Digit " + digit + " occurs " + counts[digit]
					+ (counts[digit] > 1 ? " times" : " time"));
			}
		}
	}

	public static int[] count(String s) {
		int[] counts = new int[10];
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				counts[ch - '0']++;
			}
		}
		return counts;
	}
}
