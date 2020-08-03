package Exercise_08._15;
/*********************************************************************************
 * (Geometry: same line?) Programming Exercise 6.39 gives a method for testing    *
 * whether three points are on the same line.                                     *
 * Write the following method to test whether all the points in the array points  *
 * are on the same line.                                                          *
 *                                                                                *
 * public static boolean sameLine(double[][] points)                              *
 *                                                                                *
 * Write a program that prompts the user to enter five points and displays        *
 * whether they are on the same line.                                             *
 *********************************************************************************/
import java.util.Scanner;

public class SameLine {
    public static void main(String[] args) {
        final int FIVE_POINTS = 5;

        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create a 5 x 2 array
        double[][] points = new double[FIVE_POINTS][2];

        // Prompt the user to enter five points
        System.out.print( "Enter five points: " );
        for (int i = 0; i < FIVE_POINTS; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j] = input.nextDouble();
            }
        }

        // Print result
        System.out.println("The five points are" +
                (sameLine( points ) ? "":" not") + " on the same line");

    }

    /**
     *  sameLine test whether all the points in array on the same line
     *  */
    public static boolean sameLine(double[][] points) {
        for (int i = 0; i < points.length-2; i++) {
            if (pointPosition(points[i][0], points[i][1],
                              points[i+1][0], points[i+1][1],
                              points[i+2][0], points[i+2][1]) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * To compute and return the position of point2  to point0 and point1
     */
    public static double pointPosition(double x0, double y0,
                 double x1, double y1, double x2, double y2) {
        return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
    }
}
