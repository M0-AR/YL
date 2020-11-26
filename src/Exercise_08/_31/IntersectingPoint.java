package Exercise_08._31;

import java.util.Scanner;

public class IntersectingPoint { // yl
    public static void main(String[] args) {
        double[][] points = getPoints();

        double[] intersectingPoint = getIntersectingPoint(points);

        System.out.println("THe intersecting point is at (" +
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
