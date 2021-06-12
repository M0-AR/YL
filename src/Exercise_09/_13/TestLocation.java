package Exercise_09._13;

import java.util.Scanner;

public class TestLocation {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

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

        Location location = Location.locateLargest(locations);

        System.out.println("The location of the largest element is " +
                location.maxValue + " at (" + location.locations[0][0] +
                                    ", " + location.locations[0][1] + ")");

    }
}
