package Exercise_05._15Extra;

import java.util.Scanner;

public class Exercise_05_15Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int userStreak = 0;
		int computerStreak = 0;

		while (userStreak < 2 && computerStreak < 2) {
			int computer = (int)(Math.random() * 3);
			System.out.print("scissor (0), rock (1), paper (2): ");
			int user = input.nextInt();

			if (user == computer) {
				System.out.println("It is a draw");
				userStreak = 0;
				computerStreak = 0;
			}
			else if ((user == 0 && computer == 2)
				|| (user == 1 && computer == 0)
				|| (user == 2 && computer == 1)) {
				System.out.println("You won");
				userStreak++;
				computerStreak = 0;
			}
			else {
				System.out.println("You lost");
				computerStreak++;
				userStreak = 0;
			}
		}

		if (userStreak == 2) {
			System.out.println("You won more two consectutive times");
		}
		else {
			System.out.println("The computer won two consecutive times");
		}
	}
}
