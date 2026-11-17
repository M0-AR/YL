package Exercise_02._01Extra;

import java.util.Scanner;

public class Exercise_02_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the width and height of a rectangle: ");
		double width = input.nextDouble();
		double height = input.nextDouble();

		double perimeter = 2 * (width + height);
		double area = width * height;
		double diagonal = Math.sqrt(width * width + height * height);

		System.out.println("The perimeter is " + perimeter);
		System.out.println("The area is " + area);
		System.out.println("The length of the diagonal is " + diagonal);
	}
}
