package Exercise_18._05Extra;

import java.util.Scanner;

public class Exercise_18_05Extra {
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

		Location largest = new Location(0, 0, -1);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Location current = m(i, j);
				if (current.size > largest.size) {
					largest = current;
				}
			}
		}

		System.out.println("The size of a maximum square submatrix is "
			+ largest.size);
		System.out.println("The upper-left corner of the first largest block is at ("
			+ largest.x + ", " + largest.y + ")");
	}

	public static Location m(int i, int j) {
		int size = matrix.length;
		if (i == size - 1 || j == size - 1) {
			return new Location(j, i, matrix[i][j]);
		}
		if (matrix[i][j] == 0) {
			return new Location(j, i, 0);
		}

		int blockSize = 1 + Math.min(m(i, j + 1).size,
			Math.min(m(i + 1, j).size, m(i + 1, j + 1).size));
		return new Location(j, i, blockSize);
	}

	public static class Location {
		public int x;
		public int y;
		public int size;

		public Location(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
}
