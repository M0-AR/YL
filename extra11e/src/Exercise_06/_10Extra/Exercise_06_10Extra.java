package Exercise_06._10Extra;

import java.util.Scanner;

public class Exercise_06_10Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of rows: ");
		int rows = input.nextInt();

		for (int i = 0; i <= rows; i++) {
			for (int space = 0; space < 2 * (rows - i + 1); space++) {
				System.out.print(' ');
			}
			for (int j = 0; j <= i; j++) {
				System.out.printf("%4d", c(i, j));
			}
			System.out.println();
		}
	}

	public static long c(int m, int n) {
		long result = 1;
		for (int i = 1; i <= n; i++) {
			result = result * (m - i + 1) / i;
		}
		return result;
	}
}
