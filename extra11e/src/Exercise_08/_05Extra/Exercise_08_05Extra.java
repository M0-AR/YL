package Exercise_08._05Extra;

public class Exercise_08_05Extra {
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

		int[] students = new int[scores.length];
		for (int i = 0; i < students.length; i++) {
			students[i] = i;
		}

		for (int i = scores.length - 1; i > 0; i--) {
			int maxIndex = i;
			for (int j = i - 1; j >= 0; j--) {
				if (scores[j] > scores[maxIndex]) {
					maxIndex = j;
				}
			}

			if (maxIndex != i) {
				int score = scores[i];
				scores[i] = scores[maxIndex];
				scores[maxIndex] = score;

				int student = students[i];
				students[i] = students[maxIndex];
				students[maxIndex] = student;
			}
		}

		for (int i = 0; i < scores.length; i++) {
			System.out.println("Student " + students[i] + ": " + scores[i]);
		}
	}
}
