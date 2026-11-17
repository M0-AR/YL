package Exercise_04._02Extra;

import java.util.Scanner;

public class Exercise_04_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the mass of the cart: ");
		double mass = input.nextDouble();
		System.out.print("Enter the angle of the ramp: ");
		double angle = input.nextDouble();

		double force = mass * 9.8 * Math.sin(Math.toRadians(angle));
		System.out.println("The force needed to push the cart is " + force
			+ " Newtons");
	}
}
