package Exercise_08._03Extra;

import java.util.Scanner;

public class Exercise_08_03Extra {
	private static final int N = 3;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a11, a12, a13, a21, a22, a23, a31, a32, a33: ");
		double[][] a = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
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
		double determinant = determinant(a);
		if (determinant == 0) {
			return null;
		}

		double[][] inverse = new double[N][N];
		for (int column = 0; column < N; column++) {
			for (int row = 0; row < N; row++) {
				double[][] modified = copy(a);
				for (int i = 0; i < N; i++) {
					modified[i][row] = i == column ? 1 : 0;
				}
				inverse[row][column] = determinant(modified) / determinant;
			}
		}
		return inverse;
	}

	public static double determinant(double[][] a) {
		return a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1])
			- a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0])
			+ a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);
	}

	private static double[][] copy(double[][] a) {
		double[][] result = new double[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(a[i], 0, result[i], 0, N);
		}
		return result;
	}

	private static void printMatrix(double[][] matrix) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
