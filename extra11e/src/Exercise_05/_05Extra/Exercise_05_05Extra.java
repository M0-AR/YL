package Exercise_05._05Extra;

import java.util.Scanner;

public class Exercise_05_05Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of the sides: ");
		int sides = input.nextInt();
		System.out.print("Enter the radius of the bounding circle: ");
		double radius = input.nextDouble();

		System.out.print("The coordinates of the points on the polygon are ");
		for (int i = 0; i < sides; i++) {
			double angle = 2 * Math.PI * i / sides;
			double x = radius * Math.cos(angle);
			double y = radius * Math.sin(angle);
			System.out.println("(" + x + ", " + y + ")");
		}
	}
}
