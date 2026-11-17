package Exercise_02._06Extra;

import java.util.Scanner;

public class Exercise_02_06Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an integer: ");
		int number = input.nextInt();

		int digit1 = number % 10;
		number /= 10;
		int digit2 = number % 10;
		number /= 10;
		int digit3 = number % 10;
		number /= 10;
		int digit4 = number % 10;

		System.out.println("" + digit1 + digit2 + digit3 + digit4);
	}
}
