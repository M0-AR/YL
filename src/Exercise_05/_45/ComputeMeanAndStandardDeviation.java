package Exercise_05._45;
/*
(Statistics: compute mean and standard deviation) In business applications, you
are often asked to compute the mean and standard deviation of data. The mean is
simply the average of the numbers. The standard deviation is a statistic that tells
you how tightly all the various data are clustered around the mean in a set of data.
For example, what is the average age of the students in a class? How close are the
ages? If all the students are the same age, the deviation is 0.
Write a program that prompts the user to enter ten numbers, and displays the
mean and standard deviations of these numbers using the following formula:
*/

import java.util.Scanner;

public class ComputeMeanAndStandardDeviation {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter 10 number
        System.out.print("Enter 10 numbers: ");
        String numbers = input.nextLine();

        // Split string numbers into array
        String[] n =   numbers.split( " " );

        /** Compute mean */
        double mean = 0;
        for (int i = 0; i < n.length; i++) {
            mean += Double.parseDouble(n[i]);
        }
        // Display mean
        System.out.println("The mean is " + (mean /= n.length));

        /** Compute Deviation */
        double deviation = 0;
        for (int i = 0; i < n.length; i++) {
            deviation += Math.pow( (Double.parseDouble( n[i] ) - mean), 2 ) ;
        }
        // Display Deviation
        System.out.printf("The standard deviation is %.5f", Math.sqrt( deviation /= n.length-1 ));

    }

   /* public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double mean, 				// Holds the mean
                deviation, 		// Holds the standard deviation
                number;				// Holds user input
        mean = deviation = 0;	// Set mean and deviation to 0

        // Prompt the user to enter ten numbers
        System.out.print("Enter ten numbers: ");

        // Compute mean and standard deviation
        for (int i = 1; i <= 10; i++) {
            number = input.nextDouble();
            mean += number;
            deviation += Math.pow(number, 2);
        }
        deviation =  Math.sqrt((deviation - (Math.pow(mean, 2) / 10)) / (10 - 1));
        mean /= 10;

        // Display results
        System.out.println("The mean is " + mean);
        System.out.printf("The standard deviation is %.5f\n", deviation);
    }*/
}
