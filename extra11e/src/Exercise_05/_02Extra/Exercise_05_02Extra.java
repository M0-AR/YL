package Exercise_05._02Extra;

public class Exercise_05_02Extra {
	public static void main(String[] args) {
		final int TRIALS = 1_000_000;
		int hits = 0;

		for (int i = 0; i < TRIALS; i++) {
			double x = Math.random() * 2 - 1;
			double y = Math.random() * 2 - 1;
			if (x < 0 || x + y <= 1) {
				hits++;
			}
		}

		System.out.println("The probability in Region 1 and 3 is "
			+ (1.0 * hits / TRIALS));
	}
}
