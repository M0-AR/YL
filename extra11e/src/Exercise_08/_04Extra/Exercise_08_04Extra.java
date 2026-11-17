package Exercise_08._04Extra;

public class Exercise_08_04Extra {
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

		int[][] counts = new int[10][5];
		for (int student = 0; student < answers.length; student++) {
			for (int question = 0; question < answers[student].length; question++) {
				counts[question][answers[student][question] - 'A']++;
			}
		}

		for (int question = 0; question < counts.length; question++) {
			if (question > 0) {
				System.out.println();
			}
			System.out.println("For question #" + (question + 1));
			for (int choice = 0; choice < counts[question].length; choice++) {
				System.out.printf("Percentage of %cs: %.1f%%%n",
					(char)('A' + choice),
					counts[question][choice] * 100.0 / answers.length);
			}
		}
	}
}
