package Exercise_07._11;

import java.util.Scanner;

public class ComputeDeviation {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create an array of length 10
        double[] d = new double[10];

        // Prompt the user to enter ten double values
        System.out.print("Enter 10 digits number: ");
        for (int i = 0; i < 10 ; i++)
            d[i] = input.nextDouble();

        // Display mean
        System.out.printf("The mean is %.2f\n", mean(d));

        // Display Deviation
        System.out.printf("The standard deviation is %.5f", deviation( d ));
    }


    /** Compute the deviation of double values */
    public static double deviation(double[] x){
        double deviation = 0;
        double mean = mean( x );
        for (double v : x) {
            deviation += Math.pow( (v - mean), 2 ) ;
        }

        return Math.sqrt( deviation / x.length-1 );
    }

    /** Compute the mean of an array of double values*/
    public static double mean(double[] x){
        double mean = 0;
        for (double v : x)
            mean += v;

        return mean / x.length;
    }
}
