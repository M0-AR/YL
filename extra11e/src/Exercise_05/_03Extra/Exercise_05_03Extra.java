package Exercise_05._03Extra;

import java.util.Scanner;

public class Exercise_05_03Extra {
	public static void main(String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of seconds: ");
		int seconds = input.nextInt();

		for (int remaining = seconds; remaining > 0; remaining--) {
			if (remaining == 1) {
				System.out.println("1 second remaining");
			}
			else {
				System.out.println(remaining + " seconds remaining");
			}
			Thread.sleep(1000);
		}

		System.out.println("Stopped");
	}
}
