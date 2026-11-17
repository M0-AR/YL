package Exercise_05._16Extra;

public class Exercise_05_16Extra {
	public static void main(String[] args) {
		int player1Wins = 0;
		int player2Wins = 0;

		for (int game = 0; game < 1_000_000; game++) {
			int streak = 0;
			while (streak > -2 && streak < 2) {
				int computer = (int)(Math.random() * 3);
				int user = (int)(Math.random() * 3);

				if (user == computer) {
					continue;
				}
				if ((user == 0 && computer == 2)
					|| (user == 1 && computer == 0)
					|| (user == 2 && computer == 1)) {
					streak++;
				}
				else {
					streak--;
				}
			}

			if (streak == 2) {
				player1Wins++;
			}
			else {
				player2Wins++;
			}
		}

		System.out.println("Player1 won " + player1Wins + " times");
		System.out.println("Player2 won " + player2Wins + " times");
	}
}
