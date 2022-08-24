package Exercise_11._15;

import Exercise_10._04.MyPoint;

import java.util.ArrayList;
import java.util.Scanner;

public class AreaOfAConvexPolygon {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of points: ");
        int numberOfPoints = input.nextInt();

        System.out.print("Enter the coordinates of the points: ");
        ArrayList<MyPoint> points = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            points.add(new MyPoint(input.nextDouble(), input.nextDouble()));
        }
        points.add(new MyPoint(points.get(0).getX(), points.get(0).getY()));

        System.out.println();
        System.out.println("The total area is " + areaOfConvexPolygon(points));
    }

    // http://www.mathwords.com/a/area_convex_polygon.htm
    public static double areaOfConvexPolygon(ArrayList<MyPoint> points) {
        final double HALF = .5;
        double principalDiagonal = 0, secondaryDiagonal = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            principalDiagonal += points.get(i).getX() * points.get(i + 1).getY();
        }

        for (int i = 0; i < points.size() - 1; i++) {
            secondaryDiagonal += points.get(i).getY() * points.get(i + 1).getX();
        }

        double area = HALF * (principalDiagonal - secondaryDiagonal);
        return Math.abs(area);
    }
}