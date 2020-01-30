package Exercise_05._01;
/*
(Count positive and negative numbers and compute the average of numbers) Write
a program that reads an unspecified number of integers, determines how many
positive and negative values have been read, and computes the total and average of
the input values (not counting zeros). Your program ends with the input 0. Display
the average as a floating-point number.
*/
import java.util.Scanner;

public class CountPositiveAndNegativeNumbersAndComputeTheAverageOfNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        int countPositiveNumbers = 0;
        int countNegativeNumbers = 0;
        int countNumber = 0;
        float totalOfNumbers = 0;
        float averageOfNumbers = 0;

        // First Method
        // Prompt the user to enter integers number
        System.out.print( "Enter an integer, the input ends if it is 0: " );
        int number = input.nextInt();

        if (number == 0) {
            System.out.println( "No numbers are entered except 0" );
            System.exit( 1 );
        }

        while (number != 0) {
            if (number > 0)
                countPositiveNumbers++;
            else
                countNegativeNumbers++;
            totalOfNumbers += number;
            countNumber++;
            number = input.nextInt();
        }

        averageOfNumbers = totalOfNumbers / countNumber;
        // Display result
        System.out.println( "The number of positives is " + countPositiveNumbers
                + "\nThe number of negatives is " + countNegativeNumbers
                + "\nThe total is " + totalOfNumbers
                + "\nThe average is " + averageOfNumbers );


        // Second Method
        // Prompt the user to enter integers number
        System.out.print( "Enter an integer, the input ends if it is 0: " );
        String numbers = input.nextLine();
        // We split numbers by space in array of String
        String[] splitNumbers = numbers.split( " " );

        // When the user input just 0
        if (splitNumbers.length == 1 && splitNumbers[0].equals( "0" )) {
            System.out.println( "No numbers are entered except 0" );
        } else { // Compute positive, negative, total and average
            for (int i = 0; i < splitNumbers.length; i++) {
                if (splitNumbers[i].equals( "0" ))
                    break;
                else {
                    // Counter for positive and negative
                    if (Integer.parseInt( splitNumbers[i] ) > 0)
                        countPositiveNumbers++;
                    else
                        countNegativeNumbers++;

                }
                totalOfNumbers += Integer.parseInt( splitNumbers[i] );

            }

            averageOfNumbers = totalOfNumbers
                    / (countPositiveNumbers + countNegativeNumbers);

            // Display result
            System.out.println( "The number of positives is " + countPositiveNumbers
                    + "\nThe number of negatives is " + countNegativeNumbers
                    + "\nThe total is " + totalOfNumbers
                    + "\nThe average is " + averageOfNumbers );
        }


    }
}
