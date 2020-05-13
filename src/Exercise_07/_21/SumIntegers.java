package Exercise_07._21;

import java.util.Scanner;

public class SumIntegers {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter an
        // unspecified numbers of integers
        System.out.println( "Enter a an " +
                "unspecified numbers of integers end with 0:" );
        int number;

        // Compute the sum of integers
        int sum = 0;


        // Get unspecified number
        while ((number = input.nextInt()) != 0){
            sum += number;
            if (number == 0)
                break;
        }

        // Display sum
        System.out.println("The sum of integer numbers is: " + sum);
    }
}
