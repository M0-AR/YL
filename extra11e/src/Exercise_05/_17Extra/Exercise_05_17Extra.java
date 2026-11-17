package Exercise_05._17Extra;

public class Exercise_05_17Extra {
	public static void main(String[] args) {
		int rows = 5;
		for (int i = 1; i <= rows; i++) {
			for (int j = i; j < rows; j++) {
				System.out.print("  ");
			}
			for (int j = i; j >= 1; j--) {
				System.out.print(j + " ");
			}
			for (int j = 2; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		int sum = 0;
		for (int j = 1; j <= rows; j++) {
			sum += j;
		}
		for (int j = rows - 1; j >= 1; j--) {
			sum += j;
		}
		if (sum != rows * rows) {
			throw new IllegalStateException("Unexpected row sum");
		}
		System.out.println("Row " + rows + " sum: " + sum);
		System.out.println("Exercise_05_17Extra OK");
	}
}
