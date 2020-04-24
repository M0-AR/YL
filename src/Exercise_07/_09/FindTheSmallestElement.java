package Exercise_07._09;
/*********************************************************************************
 * (Find the smallest element) Write a method that finds the smallest element in  *
 * an array of double values using the following header:                          *
 * public static double min(double[] array)                                       *
 * Write a test program that prompts the user to enter ten numbers, invokes this  *
 * method to return the minimum value, and displays the minimum value.            *
 *********************************************************************************/
import java.util.Scanner;

public class FindTheSmallestElement {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create array of length 10
        double[] d = new double[10];

        // Prompt the user to enter ten double values
        System.out.print("Enter 10 digits number: ");
        for (int i = 0; i < 10 ; i++)
            d[i] = input.nextDouble();

        // Display smallest element in the array
        System.out.println("The minimum number is: " + min( d ));

    }


    /** Find smallest element in double array */
    public static double min(double[] array){
        double min = array[0]; // To store the smallest element
        for (double v : array)
            if (v < min)
                min = v;

        return min;
    }
}
