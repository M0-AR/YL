package Exercise_08._02Extra;

import java.util.Scanner;

public class Exercise_08_02Extra {
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

		System.out.print("Enter b1, b2, b3: ");
		double[] b = new double[N];
		for (int i = 0; i < N; i++) {
			b[i] = input.nextDouble();
		}

		double[] solution = solve(a, b);
		if (solution == null) {
			System.out.println("No solution");
		}
		else {
			System.out.println("The solution is " + solution[0] + " "
				+ solution[1] + " " + solution[2]);
		}
	}

	public static double[] solve(double[][] a, double[] b) {
		double determinant = determinant(a);
		if (determinant == 0) {
			return null;
		}

		double[] solution = new double[N];
		for (int column = 0; column < N; column++) {
			double[][] modified = copy(a);
			for (int row = 0; row < N; row++) {
				modified[row][column] = b[row];
			}
			solution[column] = determinant(modified) / determinant;
			if (solution[column] == 0) {
				solution[column] = 0.0;
			}
		}
		return solution;
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
}
