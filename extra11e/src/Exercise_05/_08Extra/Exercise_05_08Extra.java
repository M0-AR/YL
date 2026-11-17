package Exercise_05._08Extra;

public class Exercise_05_08Extra {
	public static void main(String[] args) {
		String plate = "";
		for (int i = 0; i < 7; i++) {
			if (Math.random() < 0.5) {
				plate += (char)('A' + (int)(Math.random() * 26));
			}
			else {
				plate += (int)(Math.random() * 10);
			}
		}

		System.out.println("A random vehicle plate number: " + plate);
	}
}
