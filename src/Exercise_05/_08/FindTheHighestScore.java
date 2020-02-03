package Exercise_05._08;
/*
(Find the highest score) Write a program that prompts the user to enter the number
of students and each student’s name and score, and finally displays the name
of the student with the highest score.
*/
import java.util.Scanner;

public class FindTheHighestScore {
    public static void main(String[] args) { // g an
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter the number of students
        System.out.print("Enter a number of students: ");
        int numberOfStudents = input.nextInt();


        int highScore = 0; // Holds the highest student score
        String name = ""; // Student name with the highest score

        int count = 0; // to exit from while loop
        while (count < numberOfStudents) {
            // Prompt the user to enter student name and score
            System.out.print("Enter a name of student: ");
            String studentName = input.next();

            System.out.print("Enter the score of student: ");
            int studentScore = input.nextInt();

            if (studentScore>highScore) {
                highScore = studentScore;
                name = studentName;
            }
            count++;
        }

        // Display the name of the highest student's score
        System.out.println("The name of the highest student's score is " + name);
    }
}
