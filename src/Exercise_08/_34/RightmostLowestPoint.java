package Exercise_08._34;

import java.util.Scanner;

public class RightmostLowestPoint {
    public static void main(String[] args) {
        // Test -> double[][] points = {{1.5, 2.5}, {-3, 4.5}, {5.6, -7}, {6.5, -7}, {8, 1}, {10, 2.5}};

        // Get user input
        double[][] points = getPoints();

        // Find rightmost lowest point
        double[] rightMostLowestPoint = getRightmostLowestPoint( points );

        // Print rightmost lowest point
        System.out.print("The rightmost lowest point is (" +
                        rightMostLowestPoint[0] + ", " +
                        rightMostLowestPoint[1] + ")");
    }

    private static double[][] getPoints() {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter 6 points: ");
        double[][] pointsInput = new double[6][2];
        for (int i = 0; i < pointsInput.length; i++) {
            for (int j = 0; j < pointsInput[i].length; j++) {
                pointsInput[i][j] = input.nextDouble();
            }
        }
        return pointsInput;
    }


    public static double[] getRightmostLowestPoint(double[][] points) {
        double[] rightMostLowestPoint = new double[2];
        for (int i = 0; i < points.length; i++) {
            if (rightMostLowestPoint[1] == points[i][1]) {
                if (rightMostLowestPoint[0] < points[i][0]) {
                    rightMostLowestPoint[0] = points[i][0];
                    rightMostLowestPoint[1] = points[i][1];
                }
            } else if (rightMostLowestPoint[1] > points[i][1]) {
                rightMostLowestPoint[0] = points[i][0];
                rightMostLowestPoint[1] = points[i][1];
            }
        }
        return rightMostLowestPoint;
    }
}
