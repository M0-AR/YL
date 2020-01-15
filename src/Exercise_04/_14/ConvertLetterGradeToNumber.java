package Exercise_04._14;
/*
(Convert letter grade to number) Write a program that prompts the user to enter a
letter grade A, B, C, D, or F and displays its corresponding numeric value 4, 3, 2,
1, or 0.
*/
import java.util.Scanner;

public class ConvertLetterGradeToNumber { // g and y
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a letter grade
        System.out.print("Enter a letter grade: ");
        char letter = Character.toUpperCase( scanner.next().charAt( 0 ) );


        // Display Output
        int numericValue = 0;
        switch (letter){
            case 'A':numericValue = 4;break;
            case 'B':numericValue = 3;break;
            case 'C':numericValue = 2;break;
            case 'D':numericValue = 1;break;
            case 'F':numericValue = 0;break;
            default:
                System.out.println(letter + " is an invalid grade");
        }
        System.out.println("The numeric value for grade " + letter + " is " + numericValue);
    }
}
