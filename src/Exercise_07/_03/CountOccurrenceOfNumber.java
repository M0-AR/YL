package Exercise_07._03;

import java.util.Scanner;

public class CountOccurrenceOfNumber { // github
    /** Main method */
    public static void main(String[] args) {
        // Create an array of integers
        int[] numbers = new int[100];

        // Prompt the user to enter  integers between 1 and 100
        System.out.print( "Enter integers between 1 and 100: " );

        // Count occurrence of numbers
        count(numbers);

        // Display result
        for (int i = 0; i < numbers.length ; i++) {
            if (numbers[i] > 0)
                System.out.println((i + 1) + " occurs" + numbers[i]
                            + " time" + ((numbers[i] > 1) ? "s ":" "));
        }



    }

    /** Read an input between 1 and 100
     * and count the occurrence of number */
    public static void count(int[]  counts){
        // Creat a scanner
        Scanner input = new Scanner( System.in );

        // Hold user input
        int number;

        do {
            number = input.nextInt();
            if (number >= 1 && number <= 100)
                // Count the occurrence of number
                counts[number - 1]++;
        }while (number != 0);
    }

}

