package Exercise_03._02Extra;

import java.util.Scanner;

public class Exercise_03_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the coordinates for two points: ");
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();

		double slope = (y2 - y1) / (x2 - x1);
		double intercept = y1 - slope * x1;

		System.out.print("The line equation for two points (" + x1 + ", " + y1
			+ ") and (" + x2 + ", " + y2 + ") is y = ");

		if (slope == 1) {
			System.out.print("x");
		}
		else if (slope == -1) {
			System.out.print("-x");
		}
		else {
			System.out.print(slope + "x");
		}

		if (intercept > 0) {
			System.out.println(" + " + intercept);
		}
		else if (intercept < 0) {
			System.out.println(" - " + (-intercept));
		}
		else {
			System.out.println();
		}
	}
}
