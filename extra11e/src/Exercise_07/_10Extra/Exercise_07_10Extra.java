package Exercise_07._10Extra;

public class Exercise_07_10Extra {
	private static final int COMMISSION_SOUGHT = 30000;

	public static void main(String[] args) {
		int low = 0;
		int high = (int)(COMMISSION_SOUGHT / 0.08);

		while (low <= high) {
			int mid = (low + high) / 2;
			if (getCommission(mid) >= COMMISSION_SOUGHT) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}

		System.out.println("The sales amount " + low
			+ " is needed to make a commission of $" + COMMISSION_SOUGHT);
	}

	public static double getCommission(double sales) {
		if (sales <= 5000) {
			return sales * 0.08;
		}
		if (sales <= 10000) {
			return 5000 * 0.08 + (sales - 5000) * 0.10;
		}
		return 5000 * 0.08 + 5000 * 0.10 + (sales - 10000) * 0.12;
	}
}
