package Exercise_02._03Extra;

import java.util.Scanner;

public class Exercise_02_03Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the friction force in Newtons: ");
		double force = input.nextDouble();
		System.out.print("Enter the object's mass in kg: ");
		double mass = input.nextDouble();
		System.out.print("Enter the object's acceleration in m/s^2: ");
		double acceleration = input.nextDouble();

		double coefficient = (force - mass * acceleration) / (mass * 9.8);
		System.out.println("The coefficient for friction is " + coefficient);
	}
}
