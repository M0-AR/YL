package Exercise_07._01;

import java.util.Scanner;

public class AssignGrades {
    /** Main Method */
    public static void main(String[] args) {
        // Instance a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter number of students
        System.out.print("Enter the number of students: ");
        int numberOfStudents = input.nextInt();

        // Create an array of grades
        int[] scores = new int[numberOfStudents];

        // Prompt the user to enter scores
        System.out.print( "Enter " + numberOfStudents + " scores: " );
        // Store the scores in the array
        for (int i = 0; i < scores.length; i++)
            scores[i] = input.nextInt();

        printGrades(scores);
    }


    /** Print grades according to the user input */
    static void printGrades(int[] scores){
        // To store the maximum value
        int max = 0;

        // Find the maximum value in the array
        for (int i = 0; i < scores.length; i++)
            if (max < scores[i])
                max = scores[i];

        // Print print the right grade,
        // For each score in the scores array
        for (int i = 0; i < scores.length; i++) {
            if ((max - scores[i]) <= 10)
                System.out.println("Student " + i + " score is " + scores[i]
                    + " and grades is A");

            else if ((max - scores[i]) <= 20)
                System.out.println("Student " + i + " score is " + scores[i]
                        + " and grades is B");

            else if ((max - scores[i]) <= 30)
                System.out.println("Student " + i + " score is " + scores[i]
                        + " and grades is C");

            else if ((max - scores[i]) <= 40)
                System.out.println("Student " + i + " score is " + scores[i]
                        + " and grades is B");

            else {
                System.out.println("Student " + i + "score is " + scores[i]
                        + " and grades is F");
            }
        }
    }
}
