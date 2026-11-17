package Exercise_08._01Extra;

import java.util.Scanner;

public class Exercise_08_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a, b, c, d: ");
		double[][] a = new double[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				a[i][j] = input.nextDouble();
			}
		}

		double[][] inverse = inverse(a);
		if (inverse == null) {
			System.out.println("No inverse matrix");
		}
		else {
			printMatrix(inverse);
		}
	}

	public static double[][] inverse(double[][] a) {
		double determinant = a[0][0] * a[1][1] - a[0][1] * a[1][0];
		if (determinant == 0) {
			return null;
		}

		double[][] result = new double[2][2];
		result[0][0] = a[1][1] / determinant;
		result[0][1] = -a[0][1] / determinant;
		result[1][0] = -a[1][0] / determinant;
		result[1][1] = a[0][0] / determinant;
		return result;
	}

	private static void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
