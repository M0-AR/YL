package Exercise_02._02Extra;

import java.util.Scanner;

public class Exercise_02_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the ball travel time in seconds: ");
		double time = input.nextDouble();
		double height = 9.8 * time * time / 2;

		System.out.println("The height of the building is " + height + " meters");
	}
}
