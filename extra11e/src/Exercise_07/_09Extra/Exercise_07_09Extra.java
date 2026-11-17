package Exercise_07._09Extra;

import java.util.Scanner;

public class Exercise_07_09Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean[] covered = new boolean[99];

		int number = input.nextInt();
		while (number != 0) {
			if (number >= 1 && number <= 99) {
				covered[number - 1] = true;
			}
			number = input.nextInt();
		}

		StringBuilder missing = new StringBuilder();
		for (int i = 0; i < covered.length; i++) {
			if (!covered[i]) {
				if (missing.length() > 0) {
					missing.append(' ');
				}
				missing.append(i + 1);
			}
		}

		if (missing.length() == 0) {
			System.out.println("The tickets cover all numbers");
		}
		else {
			System.out.println(missing + " are the missing numbers.");
		}
	}
}
