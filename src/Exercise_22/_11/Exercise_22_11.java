package Exercise_22._11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise_22_11 {
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

        ArrayList<MyPoint> hull = getConvexHull(points);
        System.out.println("The convex hull is");
        for (MyPoint point : hull) {
            System.out.printf("(%.1f, %.1f) ", point.x, point.y);
        }
        System.out.println();
    }

    public static ArrayList<MyPoint> getConvexHull(double[][] s) {
        MyPoint[] points = new MyPoint[s.length];
        for (int i = 0; i < s.length; i++) {
            points[i] = new MyPoint(s[i][0], s[i][1]);
        }
        if (points.length <= 1) {
            return new ArrayList<>(Arrays.asList(points));
        }

        MyPoint pivot = points[0];
        for (MyPoint point : points) {
            if (point.x > pivot.x || (point.x == pivot.x && point.y < pivot.y)) {
                pivot = point;
            }
        }
        for (MyPoint point : points) {
            point.setRightMostLowestPoint(pivot);
        }
        Arrays.sort(points);

        ArrayList<MyPoint> hull = new ArrayList<>();
        for (MyPoint point : points) {
            while (hull.size() >= 2
                    && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), point) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(point);
        }
        return hull;
    }

    private static double cross(MyPoint first, MyPoint second, MyPoint third) {
        return (second.x - first.x) * (third.y - first.y)
                - (second.y - first.y) * (third.x - first.x);
    }

    private static class MyPoint implements Comparable<MyPoint> {
        double x;
        double y;
        MyPoint rightMostLowestPoint;

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void setRightMostLowestPoint(MyPoint p) {
            rightMostLowestPoint = p;
        }

        @Override
        public int compareTo(MyPoint other) {
            double turn = (x - rightMostLowestPoint.x) * (other.y - rightMostLowestPoint.y)
                    - (y - rightMostLowestPoint.y) * (other.x - rightMostLowestPoint.x);
            if (turn > 0) {
                return -1;
            }
            if (turn < 0) {
                return 1;
            }
            double firstDistance = Math.hypot(x - rightMostLowestPoint.x, y - rightMostLowestPoint.y);
            double secondDistance = Math.hypot(
                    other.x - rightMostLowestPoint.x, other.y - rightMostLowestPoint.y);
            return Double.compare(firstDistance, secondDistance);
        }
    }
}
