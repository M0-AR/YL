package Exercise_09._13;
/*********************************************************************************
 * (The Location class) Design a class named Location for locating a maximal      *
 * value and its location in a two-dimensional array. The class contains public   *
 * data fields row, column, and maxValue that store the maximal value and its     *
 * indices in a two-dimensional array with row and column as int types and        *
 * maxValue as a double type.                                                     *
 *                                                                                *
 * Write the following method that returns the location of the largest element in *
 * a two-dimensional array:                                                       *
 *                                                                                *
 * public static Location locateLargest(double[][] a)                             *
 *                                                                                *
 * The return value is an instance of Location. Write a test program that prompts *
 * the user to enter a two-dimensional array and displays the location of the     *
 * largest element in the array.                                                  *
 *********************************************************************************/
import java.util.Scanner;

public class TestLocation {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a two-dimensional array
        System.out.print("Enter the number of rows and columns in the array: ");
        int row = input.nextInt();
        int column = input.nextInt();

        double[][] locations = new double[row][column];
        System.out.println("Enter the array: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                locations[i][j] = input.nextDouble();
            }
        }

        // Get location
        Location location = Location.locateLargest(locations);

        // Display the location of the largest element in the array
        System.out.println("The location of the largest element is " +
                location.maxValue + " at (" + location.locations[0][0] +
                                    ", " + location.locations[0][1] + ")");

    }
}

