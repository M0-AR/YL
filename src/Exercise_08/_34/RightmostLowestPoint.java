package Exercise_08._34;

import java.util.Scanner;

public class RightmostLowestPoint {
    public static void main(String[] args) {
        // Test input
        // double[][] points = {{1.5, 2.5}, {-3, 4.5}, {5.6, -7}, {6.5, -7}, {8, 1}, {10, 2.5}};

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
            if (rightMostLowestPoint[1] == points[i][1] &&
                    rightMostLowestPoint[0] < points[i][0]) {
                rightMostLowestPoint[0] = points[i][0];
                rightMostLowestPoint[1] = points[i][1];
            }
            else if (rightMostLowestPoint[1] > points[i][1]) {
                rightMostLowestPoint[0] = points[i][0];
                rightMostLowestPoint[1] = points[i][1];
            }
        }
        return rightMostLowestPoint;
    }

    // More readable method
    private static double[] _getRightmostLowestPoint(double[][] p) {
        int rightMostIndex = 0;
        double rightMostX = p[0][0];
        double rightMostY = p[0][1];

        for (int i = 0; i < p.length; i++) {
            if(rightMostY > p[i][1]) {
                rightMostY = p[i][1];
                rightMostIndex = i;
            }
            else if (rightMostY == p[i][1] && rightMostX < p[i][0]) {
                rightMostX = p[i][0];
                rightMostIndex = i;
            }
        }

        return new double[]{p[rightMostIndex][0], p[rightMostIndex][1]};
    }
}
