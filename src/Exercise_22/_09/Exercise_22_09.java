package Exercise_22._09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Exercise_22_09 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many points are in the set? ");
        int size = input.nextInt();
        double[][] points = new double[size][2];
        System.out.print("Enter " + size + " points: ");
        for (int i = 0; i < size; i++) {
            points[i][0] = input.nextDouble();
            points[i][1] = input.nextDouble();
        }

        ArrayList<Point2D> hull = getConvexHull(points);
        System.out.println("The convex hull is");
        for (Point2D point : hull) {
            System.out.printf("(%.1f, %.1f) ", point.getX(), point.getY());
        }
        System.out.println();
    }

    public static ArrayList<Point2D> getConvexHull(double[][] s) {
        ArrayList<Point2D> points = new ArrayList<>();
        for (double[] coordinates : s) {
            points.add(new Point2D(coordinates[0], coordinates[1]));
        }

        ArrayList<Point2D> hull = new ArrayList<>();
        if (points.size() <= 1) {
            return new ArrayList<>(points);
        }

        int start = 0;
        for (int i = 1; i < points.size(); i++) {
            Point2D candidate = points.get(i);
            Point2D current = points.get(start);
            if (candidate.getX() < current.getX()
                    || (candidate.getX() == current.getX() && candidate.getY() < current.getY())) {
                start = i;
            }
        }

        int current = start;
        do {
            hull.add(points.get(current));
            int next = (current + 1) % points.size();
            for (int i = 0; i < points.size(); i++) {
                if (i == current) {
                    continue;
                }
                double turn = cross(points.get(current), points.get(next), points.get(i));
                if (turn < 0 || (turn == 0
                        && points.get(current).distance(points.get(i))
                        > points.get(current).distance(points.get(next)))) {
                    next = i;
                }
            }
            current = next;
        } while (current != start && hull.size() <= points.size());

        if (hull.size() >= 2) {
            int top = 0;
            for (int i = 1; i < hull.size(); i++) {
                if (hull.get(i).getY() > hull.get(top).getY()) {
                    top = i;
                }
            }
            Collections.rotate(hull, -top);
            Collections.reverse(hull);
            Collections.rotate(hull, 1);
        }
        return hull;
    }

    private static double cross(Point2D origin, Point2D first, Point2D second) {
        return (first.getX() - origin.getX()) * (second.getY() - origin.getY())
                - (first.getY() - origin.getY()) * (second.getX() - origin.getX());
    }

    static List<Point2D> asList(ArrayList<Point2D> points) {
        return points;
    }
}
