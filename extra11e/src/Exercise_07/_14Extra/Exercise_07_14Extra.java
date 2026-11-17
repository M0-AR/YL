package Exercise_07._14Extra;

import java.util.Scanner;

public class Exercise_07_14Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of students: ");
		int numberOfStudents = input.nextInt();
		double[] scores = new double[numberOfStudents];

		System.out.print("Enter " + numberOfStudents + " scores: ");
		double best = 0;
		for (int i = 0; i < scores.length; i++) {
			scores[i] = input.nextDouble();
			best = Math.max(best, scores[i]);
		}

		int[] counts = new int[5];
		for (int i = 0; i < scores.length; i++) {
			char grade;
			if (scores[i] >= best - 10) {
				grade = 'A';
			}
			else if (scores[i] >= best - 20) {
				grade = 'B';
			}
			else if (scores[i] >= best - 30) {
				grade = 'C';
			}
			else if (scores[i] >= best - 40) {
				grade = 'D';
			}
			else {
				grade = 'F';
			}

			counts[grade == 'F' ? 4 : grade - 'A']++;
			System.out.println("Student " + i + " score is " + scores[i]
				+ " and grade is " + grade);
		}

		int maxCount = 0;
		for (int i = 0; i < counts.length; i++) {
			maxCount = Math.max(maxCount, counts[i]);
		}

		for (int level = maxCount; level > 0; level--) {
			for (int i = 0; i < counts.length; i++) {
				System.out.print(counts[i] >= level ? "*" : " ");
			}
			System.out.println();
		}
		System.out.println("ABCDF");
	}
}
