package Exercise_08._33;

import java.util.Scanner;

public class PolygonSubAreas {


    public static void main(String[] args) {
        double[][] points = getPoints();
        double area = getFourAreasTriangles( points );
        System.out.println((area == 0) ? "The three points are on the same line" :
                "The area of the triangle is " + area);

    }



    private static double[][] getPoints() {
        Scanner input = new Scanner( System.in );
        System.out.println("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        double[][] pointsInput = new double[4][2];
        for (int i = 0; i < pointsInput.length; i++) {
            for (int j = 0; j < pointsInput[i].length; j++) {
                pointsInput[i][j] = input.nextDouble();
            }
        }
        return pointsInput;
    }

    private static double[] getIntersectingPoint(double[][] points) {
        double[][] p1 = findTwoFurthesPoints(points);
        double[][] p2 = exclude(points, p1);

        // y1 - y2
        double a = p1[0][1] - p1[1][1];
        // -(x1 - x2)
        double b = -(p1[0][0] - p1[1][0]);
        // y3 - y4
        double c = p2[0][1] - p2[1][1];
        // -(x3 - x4)
        double d = -(p2[0][0] - p2[1][0]);
        // (y1 - y2) * x1 - (x1 - x2) *y1
        double e = ((p1[0][1] - p1[1][1]) * p1[0][0]) -
                        ((p1[0][0] - p1[1][0]) * p1[0][1]);
        // (y3 - y4) * x3 - (x3 - x4) *x3
        double f = ((p2[0][1] - p2[1][1]) * p2[0][0]) -
                        ((p2[0][0] - p2[1][0]) * p2[0][1]);

        double ad_Minus_bc = a * d - b * c;

        if (ad_Minus_bc == 0) return null;
        double[] point = new double[2];
        point[0] = (e * d - b * f) / ad_Minus_bc;
        point[1] = (a * f - e * c) / ad_Minus_bc;

        return point;
    }


    private static double[] getFourAreasTriangles(double[][] points) {
        double[] areas = new double[4];
        areas[0] = getAreaForFristTriangle (points[0][0], points[0][1]);
        areas[1] = getAreaForSecondTriangle(points[1][0], points[1][1]);
        areas[2] = getAreaForThirdTriangle (points[2][0], points[2][1]);
        areas[3] = getAreaForForthTriangle (points[3][0], points[3][1]);
        return areas;
    }

    public static double getTriangleArea(double[][] points) {
        double side1 = distance(points[0][0],  points[0][1], points[1][0], points[1][1]);
        double side2 = distance(points[0][0],  points[0][1], points[2][0], points[2][1]);
        double side3 = distance(points[2][0],  points[2][1], points[1][0], points[1][1]);
        double s = (side1 + side2 + side3)/2;
        double area = Math.sqrt( s * (s - side1) * (s - side2) * (s - side3) );
        return ((int) (area * 100)) / 100.0; // Two decimal number
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return d;
    }


}