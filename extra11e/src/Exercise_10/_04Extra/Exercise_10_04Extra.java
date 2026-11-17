package Exercise_10._04Extra;

public class Exercise_10_04Extra {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise_10_04Extra \"aString\"");
			System.exit(1);
		}

		String[] items = args[0].trim().split("\\s+");
		int sum = 0;
		for (String item : items) {
			sum += Integer.parseInt(item);
		}

		System.out.println("The total is " + sum);
	}
}
