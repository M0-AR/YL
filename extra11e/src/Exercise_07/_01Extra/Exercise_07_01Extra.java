package Exercise_07._01Extra;

import java.util.Scanner;

public class Exercise_07_01Extra {
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

		boolean allCovered = true;
		for (int i = 0; i < covered.length; i++) {
			if (!covered[i]) {
				allCovered = false;
				break;
			}
		}

		if (allCovered) {
			System.out.println("The tickets cover all numbers");
		}
		else {
			System.out.println("The tickets don't cover all numbers");
		}
	}
}
