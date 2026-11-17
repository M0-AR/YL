package Exercise_04._03Extra;

import java.util.Scanner;

public class Exercise_04_03Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the coefficient of friction: ");
		double coefficient = input.nextDouble();

		double angle = Math.toDegrees(Math.atan(coefficient));
		System.out.println("The minimal angle for the brick to slide is "
			+ angle + " degrees");
	}
}
