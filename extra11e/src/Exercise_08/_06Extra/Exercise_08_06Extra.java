package Exercise_08._06Extra;

public class Exercise_08_06Extra {
	public static void main(String[] args) {
		char[][] answers = {
			{'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
			{'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'},
			{'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'},
			{'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'},
			{'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
			{'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
			{'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
			{'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}
		};
		char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};

		int[] scores = new int[answers.length];
		for (int student = 0; student < answers.length; student++) {
			for (int question = 0; question < keys.length; question++) {
				if (answers[student][question] == keys[question]) {
					scores[student]++;
				}
			}
		}

		System.out.println("Max is " + max(scores));
		System.out.println("Min is " + min(scores));
		System.out.println("Mean is " + mean(scores));
		System.out.println("Deviation is " + deviation(scores));
	}

	public static int max(int[] values) {
		int result = values[0];
		for (int value : values) {
			result = Math.max(result, value);
		}
		return result;
	}

	public static int min(int[] values) {
		int result = values[0];
		for (int value : values) {
			result = Math.min(result, value);
		}
		return result;
	}

	public static double mean(int[] values) {
		double sum = 0;
		for (int value : values) {
			sum += value;
		}
		return sum / values.length;
	}

	public static double deviation(int[] values) {
		double mean = mean(values);
		double sum = 0;
		for (int value : values) {
			sum += (value - mean) * (value - mean);
		}
		return Math.sqrt(sum / values.length);
	}
}
