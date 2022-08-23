package Exercise_11._15;

import java.util.Scanner;

public class AreaOfAConvexPolygon {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of points: ");
        int numberOfPoints = input.nextInt();

        System.out.print("Enter the coordinates of the points: ");
        double[][] points = new double[numberOfPoints + 1][2];
        for (int i = 0; i < numberOfPoints; i++) {
            points[i][0] = input.nextDouble();
            points[i][1] = input.nextDouble();
        }
        points[numberOfPoints][0] = points[0][0];
        points[numberOfPoints][1] = points[0][1];

        System.out.println();
        System.out.println("The total area is " + areaOfConvexPolygon(points));
    }

    // http://www.mathwords.com/a/area_convex_polygon.htm
    public static double areaOfConvexPolygon(double[][] points) {
        final double HALF = .5;
        double principalDiagonal = 0, secondaryDiagonal = 0;

        for (int i = 0; i < points.length - 1; i++) {
            principalDiagonal += points[i][0] * points[i+1][1];
        }

        for (int i = 0; i < points.length - 1; i++) {
            secondaryDiagonal += points[i][1] * points[i+1][0];
        }

        double area = HALF * (principalDiagonal - secondaryDiagonal);
        return Math.abs(area);
    }
}