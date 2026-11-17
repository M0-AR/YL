package Exercise_05._11Extra;

public class Exercise_05_11Extra {
	public static void main(String[] args) {
		System.out.printf("%-15s%s%n", "Number", "SquareRoot");
		System.out.println("-------------------------------");

		for (int number = 0; number <= 20; number += 2) {
			System.out.printf("%-15d%.4f%n", number, Math.sqrt(number));
		}
	}
}
