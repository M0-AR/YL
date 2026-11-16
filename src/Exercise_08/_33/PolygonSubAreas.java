package Exercise_08._33;

import java.util.Arrays;
import java.util.Scanner;

public class PolygonSubAreas {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        double[][] points = new double[4][2];
        for (int i = 0; i < points.length; i++) {
            points[i][0] = input.nextDouble();
            points[i][1] = input.nextDouble();
        }

        sortCounterClockwise(points);

        double[] intersection = intersection(points[0], points[2], points[1], points[3]);
        if (intersection == null) {
            System.out.println("The two lines are parallel");
            return;
        }

        double area1 = triangleArea(points[0], points[1], intersection);
        double area2 = triangleArea(points[1], points[2], intersection);
        double area3 = triangleArea(points[2], points[3], intersection);
        double area4 = triangleArea(points[3], points[0], intersection);

        System.out.printf("The areas are %.2f %.2f %.2f %.2f%n", area1, area2, area3, area4);

        input.close();
    }

    private static void sortCounterClockwise(double[][] points) {
        double centerX = 0;
        double centerY = 0;
        for (double[] point : points) {
            centerX += point[0];
            centerY += point[1];
        }
        centerX /= points.length;
        centerY /= points.length;

        final double cx = centerX;
        final double cy = centerY;
        Arrays.sort(points, (a, b) -> Double.compare(
                Math.atan2(a[1] - cy, a[0] - cx),
                Math.atan2(b[1] - cy, b[0] - cx)));
    }

    private static double[] intersection(double[] a, double[] b, double[] c, double[] d) {
        double a1 = b[1] - a[1];
        double b1 = a[0] - b[0];
        double c1 = a1 * a[0] + b1 * a[1];

        double a2 = d[1] - c[1];
        double b2 = c[0] - d[0];
        double c2 = a2 * c[0] + b2 * c[1];

        double determinant = a1 * b2 - a2 * b1;
        if (Math.abs(determinant) < 1e-10) {
            return null;
        }

        double x = (b2 * c1 - b1 * c2) / determinant;
        double y = (a1 * c2 - a2 * c1) / determinant;

        if (!onSegment(a, b, x, y) || !onSegment(c, d, x, y)) {
            return null;
        }

        return new double[] {x, y};
    }

    private static boolean onSegment(double[] a, double[] b, double x, double y) {
        return x >= Math.min(a[0], b[0]) - 1e-10
                && x <= Math.max(a[0], b[0]) + 1e-10
                && y >= Math.min(a[1], b[1]) - 1e-10
                && y <= Math.max(a[1], b[1]) + 1e-10;
    }

    private static double triangleArea(double[] a, double[] b, double[] c) {
        return Math.abs((b[0] - a[0]) * (c[1] - a[1])
                - (c[0] - a[0]) * (b[1] - a[1])) / 2;
    }
}
