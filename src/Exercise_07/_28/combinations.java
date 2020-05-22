package Exercise_07._28;
/*********************************************************************************
 * (Math: combinations) Write a program that prompts the user to enter 10 integers*
 * and displays all combinations of picking two numbers from the 10.              *
 *********************************************************************************/
import java.util.Scanner;

public class combinations {
    /** Main method */
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in ); // Create a scanner
        int[] numbers = new int[10]; // Create an array of length ten

        // Prompt the user to enter 10 integers
        System.out.println( "Enter 10 integers numbers: " );
        for (int i = 0; i < numbers.length ; i++)
            numbers[i] = input.nextInt();

        // Display result
        displayCombinations(numbers);

    }

    /** To display all combinations for every number in the array */
    public static void displayCombinations(int[] list){
        for (int i = 0; i < list.length; i++)
            for (int j = i + 1; j < list.length; j++)
                System.out.println(list[i] + " " + list[j]);
    }
}
