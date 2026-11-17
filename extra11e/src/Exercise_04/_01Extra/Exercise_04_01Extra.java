package Exercise_04._01Extra;

import java.util.Scanner;

public class Exercise_04_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the mass of the cart: ");
		double mass = input.nextDouble();
		System.out.print("Enter the force to push the cart: ");
		double force = input.nextDouble();

		double angle = Math.toDegrees(Math.asin(force / (mass * 9.8)));
		System.out.println("The angle of the ramp is " + angle + " degrees");
	}
}
