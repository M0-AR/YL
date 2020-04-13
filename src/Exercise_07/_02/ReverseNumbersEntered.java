package Exercise_07._02;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseNumbersEntered {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create an array of integers
        int[] numbers = new int[10];

        // Prompt the user to enter 10 integers
        System.out.print( "Enter 10 integers (e.g., 10 11 ...):" );

        // Store the integers in the array
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = input.nextInt();


        // Display the reverse of numbers that entered
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print( numbers[i] + " " );
        }


    }
}
