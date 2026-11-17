package Exercise_18._04Extra;

import java.util.Scanner;

public class Exercise_18_04Extra {
	private static int[][] matrix;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of rows in the square matrix: ");
		int size = input.nextInt();
		matrix = new int[size][size];

		System.out.println("Enter the matrix row by row:");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = input.nextInt();
			}
		}

		int largest = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				largest = Math.max(largest, m(i, j));
			}
		}

		System.out.println("The size of a maximum square submatrix is "
			+ largest);
	}

	public static int m(int i, int j) {
		int size = matrix.length;
		if (i == size - 1 || j == size - 1) {
			return matrix[i][j];
		}
		if (matrix[i][j] == 0) {
			return 0;
		}
		return 1 + Math.min(m(i, j + 1),
			Math.min(m(i + 1, j), m(i + 1, j + 1)));
	}
}
