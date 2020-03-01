package Exercise_05._45;

import java.util.Arrays;
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
}
