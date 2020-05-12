package Exercise_07._17;

import java.util.Scanner;

/*********************************************************************************
 * (Sort students) Write a program that prompts the user to enter the number of  *
 * students, the students’ names, and their scores, and prints student names in  *
 * decreasing order of their scores.                                             *
 *********************************************************************************/
public class sortStudents {
    /**
     * Main method
     */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter students number
        System.out.print( "Enter students number: " );
        int n = input.nextInt();

        // Create two array to store students's name and their scores
        String[] studentsName = new String[n];
        int[] studentScores = new int[n];


        // Prompt the user to enter  students's name and their scores
        for (int i = 0; i < n ; i++) {
            System.out.print( "Enter student's name: ");
            studentsName[i] = input.next();
            System.out.print( "Enter student's score: ");
            studentScores[i] = input.nextInt();
        }

        // Sort students scores and swap student name
        sortStudentsScores( studentScores, studentsName );


        // Display result
        System.out.println("Students's name and their scores: ");
        for (int i = 0; i < n; i++) {
            System.out.println(studentsName[i] + "\t\t" + studentScores[i]);
        }


    }

    /** To sort students's score in decreasing order and swap students's names
     * according to sorting */
    public static void sortStudentsScores(int[] studentScores, String[] studentsName){
        int n = studentScores.length;

        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in the array
            int max = studentScores[i];
            int maxIndex = i;
            String temp;


            for (int j = i + 1; j < n; j++)
                if (max < studentScores[j]){
                    max = studentScores[j];
                    maxIndex = j;
                }



            // Swap list[i] with list[maxIndex] if necessary
            if(maxIndex != i){
                // Swap int array
                studentScores[maxIndex] = studentScores[i];
                studentScores[i] = max;

                // Swap String array
                temp = studentsName[i];
                studentsName[i] = studentsName[maxIndex];
                studentsName[maxIndex] = temp;

            }
        }
    }
}
