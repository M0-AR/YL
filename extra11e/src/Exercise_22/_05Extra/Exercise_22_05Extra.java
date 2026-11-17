package Exercise_22._05Extra;

public class Exercise_22_05Extra {
	public static void main(String[] args) {
		int[] values = {2, 3, 5, 4, 6, 7, 8, 1, 2};
		int bestStart = 0;
		int bestLength = 1;
		int start = 0;
		for (int i = 1; i < values.length; i++) {
			if (values[i] > values[i - 1]) {
				if (i - start + 1 > bestLength) {
					bestLength = i - start + 1;
					bestStart = start;
				}
			}
			else {
				start = i;
			}
		}
		System.out.println("Longest increasing run: start=" + bestStart
				+ ", length=" + bestLength);
		if (bestStart != 3 || bestLength != 4) {
			throw new IllegalStateException("Unexpected run");
		}
		System.out.println("Exercise_22_05Extra OK");
	}
}
