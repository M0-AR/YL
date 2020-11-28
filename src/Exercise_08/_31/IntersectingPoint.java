package Exercise_08._31;

/*********************************************************************************
 * (Geometry: intersecting point) Write a method that returns the intersecting    *
 * point of two lines. The intersecting point of the two lines can be found by    *
 * using the formula shown in Programming Exercise 3.25. Assume that (x1, y1) and *
 * (x2, y2) are the two points on line 1 and (x3, y3) and (x4, y4) are on line 2. *
 * The method header is                                                           *
 *                                                                                *
 * public static double[] getIntersectingPoint(double[][] points)                 *
 *                                                                                *
 * The points are stored in a 4-by-2 two-dimensional array points with            *
 * (points[0][0], points[0][1]) for (x1, y1). The method returns the intersecting *
 * point or null if the two lines are parallel. Write a program that prompts      *
 * the user to enter four points and displays the intersecting point. See         *
 * Programming Exercise 3.25 for a sample run.                                    *
 *********************************************************************************/
import java.util.Scanner;

public class IntersectingPoint {
    public static void main(String[] args) {
        double[][] points = getPoints();

        double[] intersectingPoint = getIntersectingPoint(points);

        System.out.println("The intersecting point is at (" +
                        String.format("%.5f",intersectingPoint[0]) + ", "
                        + String.format("%.5f",intersectingPoint[1]) + ")");
    }

    private static double[][] getPoints() {
        Scanner input = new Scanner( System.in );
        System.out.println("Enter four points: ");
        double[][] pointsInput = new double[4][2];
        for (int i = 0; i < pointsInput.length; i++) {
            for (int j = 0; j < pointsInput[i].length; j++) {
                pointsInput[i][j] = input.nextDouble();
            }
        }
        return pointsInput;
    }


    private static double[] getIntersectingPoint(double[][] points) {
        double a, b, c, d, e, f;
        double  x1 = points[0][0], y1 = points[0][1],
                x2 = points[1][1], y2 = points[1][1],
                x3 = points[2][0], y3 = points[2][1],
                x4 = points[3][0], y4 = points[3][1];

        // Calculate the intersecting point
        a = y1 - y2;
        b = -1 * (x1 - x2);
        c = y3 - y4;
        d = -1 * (x3 - x4);
        e = (y1 - y2) * x1 - (x1 - x2) * y1;
        f = (y3 - y4) * x3 - (x3 - x4) * y3;

        double[] intersectingPoint = new double[2];

        intersectingPoint[0] = (e * d - b * f) / (a * d - b * c);
        intersectingPoint[1] = (a * f - e * c) / (a * d - b * c);

        return intersectingPoint;
    }


}
