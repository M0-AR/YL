package Exercise_05._09;
/*
(Find the two highest scores) Write a program that prompts the user to enter the
number of students and each student’s name and score, and finally displays the
student with the highest score and the student with the second-highest score.
*/
import java.util.Scanner;

public class FindTheTwoHighestScores {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter  numbers of students
        System.out.print( "Enter numbers of students: " );
        int numberOfStudents = input.nextInt();

        int highestScore = 0, secondHighestScore = 0, count = 0; // when count reach numberOfStudents we stop the loop
        String firstStudentName = null, secondStudentName = null;
                
        while (count < numberOfStudents) {
            
            // Prompt the user to enter a student's name and score
            System.out.print( "Enter a student's name and score: " );
            String studentName = input.next();
            int studentScore = input.nextInt();

            if (studentScore > highestScore || studentScore > secondHighestScore) {
                if (studentScore > highestScore){
                    // Handel names
                    secondStudentName = firstStudentName;
                    firstStudentName = studentName;
                    // Handel scores
                    secondHighestScore = highestScore;
                    highestScore = studentScore;
                }else {
                    secondStudentName = studentName;
                    secondHighestScore = studentScore;
                }
            }
            count++;
        }

        System.out.println("The first: " + firstStudentName + " " + highestScore 
                + "\nThe second: " + secondStudentName + " " + secondHighestScore);
    }
}
