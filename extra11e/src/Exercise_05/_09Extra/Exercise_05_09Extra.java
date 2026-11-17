package Exercise_05._09Extra;

import java.util.Scanner;

public class Exercise_05_09Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String text = input.nextLine();

		int count = 0;
		for (int i = 0; i < text.length() - 1; i++) {
			if (text.charAt(i) == text.charAt(i + 1)) {
				count++;
			}
		}

		System.out.println("The number of consecutive repeating characters is "
			+ count);
	}
}
