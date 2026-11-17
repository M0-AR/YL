package Exercise_05._10Extra;

import java.util.Scanner;

public class Exercise_05_10Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String text = input.nextLine();

		int bestStart = 0;
		int bestLength = 1;
		int i = 0;
		while (i < text.length()) {
			int j = i;
			while (j + 1 < text.length() && text.charAt(j + 1) == text.charAt(i)) {
				j++;
			}
			int length = j - i + 1;
			if (length > bestLength) {
				bestLength = length;
				bestStart = i;
			}
			i = j + 1;
		}

		System.out.print("The first longest consecutive repeating character substring is ");
		for (int k = 0; k < bestLength; k++) {
			System.out.print(text.charAt(bestStart));
		}
		System.out.println();
	}
}
