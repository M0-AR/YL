package Exercise_08._08;
/*********************************************************************************
 * (All closest pairs of points) Revise Listing 8.3, FindNearestPoints.java, to   *
 * display all closest pairs of points with the same minimum distance.            *
 *********************************************************************************/
import java.util.Scanner;

public class AllClosestPairsOfPoints {
    public static void main(String[] args) {
        // Manual input for test purpose
        /*double[][] points = {{0, 0}, {1, 1}, {-1, -1},
                            {2, 2}, {-2, -2}, {-3, -3},
                            {-4, -4}, {5,5}};*/

        // Get the input from user
        double[][] points = getInput();

        // Find and Display short distance
        double shortestDistance = findShortestDistance( points );

        // Print result
        print(points, shortestDistance);
    }

    /** Get the input from the user and return 2D array of x and y points*/
    public static double[][] getInput() {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter the number of points and points
        System.out.print( "Enter the number of points: " );
        double[][] points = new double[input.nextInt()][2]; // Create an array to store points


        System.out.print( "Enter " + points.length + " points: ");
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < 2; j++)
                points[i][j] = input.nextInt();
        }

        return points;
    }


    /** Compute distance between two points */
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt( Math.pow( x2 - x1, 2 ) + Math.pow( y2 - y1, 2 ) );
    }


    /** Find shortest distance and display result */
    public static double findShortestDistance(double[][] m) {
        double shortestDistance = // To store a value of first distance
                distance( m[0][0], m[0][1], m[1][0], m[1][1] ); // To compute distance

        // Compute distance for every two points
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                double distance = distance( m[i][0], m[i][1],
                        m[j][0], m[j][1] ); // Find distance

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                }
            }
        }
        return shortestDistance;
    }




    /** Display the closest points and their distance */
    public static void print(double[][] points,double shortestDistance) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (distance( points[i][0], points[i][1],
                        points[j][0], points[j][1] ) == shortestDistance) {
                    System.out.println( "The closest two points are " +
                            "(" + points[i][0] + ", " + points[i][1] + ")" +
                            "(" + points[j][0] + ", " + points[j][1] + ")" );
                }
            }
        }

        System.out.println("Their distance is " + shortestDistance);
    }
}
