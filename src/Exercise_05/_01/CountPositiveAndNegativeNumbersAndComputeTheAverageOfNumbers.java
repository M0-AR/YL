package Exercise_05._01;

import java.util.Scanner; // g

public class CountPositiveAndNegativeNumbersAndComputeTheAverageOfNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter integers number
        System.out.print( "Enter an integer, the input ends if it is 0: " );
        String numbers = input.nextLine();
        // We split numbers by space in array of String
        String[] splitNumbers = numbers.split( " " );

        // When the user input just 0
        if (splitNumbers.length == 1 && splitNumbers[0].equals( "0" )) {
            System.out.println( "No numbers are entered except 0" );
        } else { // Compute positive, negative, total and average
            int countPositiveNumbers = 0;
            int countNegativeNumbers = 0;
            float totalOfNumbers = 0;

            for (int i = 0; i < splitNumbers.length; i++) {
                if (splitNumbers[i].equals( "0" )) {
                    break;
                } else {
                    // Counter for positive and negative
                    if (Integer.parseInt( splitNumbers[i] ) > 0
                            || Integer.parseInt( splitNumbers[i] ) < 0) {
                        if (Integer.parseInt( splitNumbers[i] ) > 0) {
                            countPositiveNumbers++;
                        } else {
                            countNegativeNumbers++;
                        }
                    }
                    totalOfNumbers += Integer.parseInt( splitNumbers[i] );
                }
            }

            float averageOfNumbers = totalOfNumbers
                    / (countPositiveNumbers + countNegativeNumbers);

            // Display result
            System.out.println("The number of positives is " + countPositiveNumbers
                    + "\nThe number of negatives is " + countNegativeNumbers
                    + "\nThe total is " + totalOfNumbers
                    + "\nThe average is " + averageOfNumbers);
        }


    }
}
