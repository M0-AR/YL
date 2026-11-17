package Exercise_04._04Extra;

import java.util.Scanner;

public class Exercise_04_04Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the coefficient of friction: ");
		double coefficient = input.nextDouble();
		System.out.print("Enter the angle: ");
		double angle = input.nextDouble();

		double acceleration = 9.8 * (Math.sin(Math.toRadians(angle))
			- coefficient * Math.cos(Math.toRadians(angle)));

		if (acceleration <= 0) {
			System.out.println("The brick does not move or move at a constant speed");
		}
		else {
			System.out.println("The brick accelerates at " + acceleration
				+ " meters per square seconds");
		}
	}
}
