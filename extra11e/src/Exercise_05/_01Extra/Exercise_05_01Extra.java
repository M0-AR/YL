package Exercise_05._01Extra;

public class Exercise_05_01Extra {
	public static void main(String[] args) {
		final int TRIALS = 1_000_000;
		int hits = 0;

		for (int i = 0; i < TRIALS; i++) {
			double x = Math.random() * 2 - 1;
			double y = Math.random() * 2 - 1;
			if (x * x + y * y <= 1) {
				hits++;
			}
		}

		double pi = 4.0 * hits / TRIALS;
		System.out.println("PI is " + pi);
	}
}
