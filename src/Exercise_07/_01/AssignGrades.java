package Exercise_07._01;
/*
(Assign grades) Write a program that reads student scores, gets the best score,
and then assigns grades based on the following scheme:
Grade is A if score is >= best - 10;
Grade is B if score is >= best - 20;
Grade is C if score is >= best - 30;
Grade is D if score is >= best - 40;
Grade is F otherwise.
The program prompts the user to enter the total number of students, then prompts
the user to enter all of the scores, and concludes by displaying the grades. Here
is a sample run:
*/
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

    /** Return the highest score */
    public static int max ( int [] scores){
        int max = scores[0];

        // Find the maximum value in the array
        for (int i = 1; i < scores.length; i++)
            if (max < scores[i])
                max = scores[i];

            return max;
    }

    /** Print grades according to the user input */
    static void printGrades(int[] scores){
        // To store the maximum value
        int max = max( scores);

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
