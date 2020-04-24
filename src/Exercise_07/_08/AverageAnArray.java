package Exercise_07._08;
/************************************************************************************
 * (Average an array) Write two overloaded methods that return the average of an     *
 * array with the following headers:                                                 *
 * public static int average(int[] array)                                            *
 * public static double average(double[] array)                                      *
 * Write a test program that prompts the user to enter ten double values, invokes    *
 * this method, and displays the average value.                                      *
 *************************************************************************************/
import java.util.Scanner;

public class AverageAnArray { // github and y
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        double[] d = new double[10];

        // Prompt the user to enter ten double values
        System.out.print("Enter 10 digits number: ");
        for (int i = 0; i < 10 ; i++)
            d[i] = input.nextDouble();

        // Display the average value of the array
        System.out.println("The average value: " + average( d ));




    }

    /** Overloaded method average accepts an integer array and returns its average */
    public static int average(int[] array){
        int sum = 0;
        for (int i : array)
            sum += i;
        return sum / array.length;
    }

    /** Overloaded method average accepts an double array and returns its average */
    public static double average(double[] array){
        double sum = 0;
        for (double i : array)
            sum += i;
        return sum / array.length;
    }
}
