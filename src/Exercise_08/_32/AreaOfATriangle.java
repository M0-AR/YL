package Exercise_08._32;
/*********************************************************************************
 * (Geometry: area of a triangle) Write a method that returns the area of a       *
 * triangle using the following header:                                           *
 *                                                                                *
 * public static double getTriangleArea(double[][] points)                        *
 *                                                                                *
 * The points are stored in a 3-by-2 two-dimensional array points with points[0]  *
 * [0] and points[0][1] for (x1, y1). The triangle area can be computed using the *
 * formula in Programming Exercise 2.19. The method returns 0 if the three points *
 * are on the same line. Write a program that prompts the user to enter three     *
 * points of a triangle and displays the triangle's area.                         *
 *********************************************************************************/
import java.util.Scanner;

public class AreaOfATriangle { // yl and github

    public static void main(String[] args) {
        double[][] points = getPoints();
        double area = getTriangleArea( points );
        System.out.println((area == 0) ? "The three points are on the same line" :
                                                "The area of the triangle is " + area);
    }

    private static double[][] getPoints() {
        Scanner input = new Scanner( System.in );
        System.out.println("Enter x1, y1, x2, y2, x3, y3: ");
        double[][] pointsInput = new double[3][2];
        for (int i = 0; i < pointsInput.length; i++) {
            for (int j = 0; j < pointsInput[i].length; j++) {
                pointsInput[i][j] = input.nextDouble();
            }
        }
        return pointsInput;
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
