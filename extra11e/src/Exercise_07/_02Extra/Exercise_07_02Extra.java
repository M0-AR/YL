package Exercise_07._02Extra;

import java.util.Scanner;

public class Exercise_07_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the coordinates of six points: ");
		double[] x = new double[6];
		double[] y = new double[6];
		for (int i = 0; i < 6; i++) {
			x[i] = input.nextDouble();
			y[i] = input.nextDouble();
		}

		double area = 0;
		for (int i = 0; i < 6; i++) {
			int next = (i + 1) % 6;
			area += x[i] * y[next] - x[next] * y[i];
		}
		area = Math.abs(area) / 2;

		System.out.println("The total area is " + area);
	}
}
