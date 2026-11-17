package Exercise_03._01Extra;

import java.util.Scanner;

public class Exercise_03_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a numerator: ");
		int numerator = input.nextInt();
		System.out.print("Enter a denominator: ");
		int denominator = input.nextInt();

		if (numerator < denominator) {
			System.out.println(numerator + " / " + denominator
				+ " is a proper fraction");
		}
		else if (numerator % denominator == 0) {
			System.out.println(numerator + " / " + denominator
				+ " is an improper fraction and it can be reduced to "
				+ numerator / denominator);
		}
		else {
			System.out.println(numerator + " / " + denominator
				+ " is an improper fraction and its mixed fraction is "
				+ numerator / denominator + " + " + numerator % denominator
				+ " / " + denominator);
		}
	}
}
