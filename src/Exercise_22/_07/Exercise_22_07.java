package Exercise_22._07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Exercise_22_07 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of points: ");
        int n = input.nextInt();
        double[][] values = new double[n][2];
        System.out.print("Enter " + n + " points: ");
        for (int i = 0; i < n; i++) {
            values[i][0] = input.nextDouble();
            values[i][1] = input.nextDouble();
        }

        Pair pair = getClosestPair(values);
        System.out.printf("The closest pair is (%.2f, %.2f) and (%.2f, %.2f)%n",
                pair.p1.x, pair.p1.y, pair.p2.x, pair.p2.y);
        System.out.printf("Their distance is %.4f%n", pair.getDistance());
    }

    public static Pair getClosestPair(double[][] points) {
        Point[] pointsOrderedOnX = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointsOrderedOnX[i] = new Point(points[i][0], points[i][1]);
        }
        return getClosestPair(pointsOrderedOnX);
    }

    public static Pair getClosestPair(Point[] points) {
        Point[] pointsOrderedOnX = points.clone();
        Arrays.sort(pointsOrderedOnX);
        Point[] pointsOrderedOnY = points.clone();
        Arrays.sort(pointsOrderedOnY, new CompareY());
        return distance(pointsOrderedOnX, 0, pointsOrderedOnX.length - 1, pointsOrderedOnY);
    }

    public static Pair distance(Point[] pointsOrderedOnX, int low, int high,
                                Point[] pointsOrderedOnY) {
        if (high - low <= 3) {
            return bruteForce(pointsOrderedOnX, low, high);
        }

        int mid = (low + high) / 2;
        Point middle = pointsOrderedOnX[mid];
        List<Point> leftY = new ArrayList<>();
        List<Point> rightY = new ArrayList<>();
        for (Point point : pointsOrderedOnY) {
            if (point.x < middle.x || (point.x == middle.x && point.compareTo(middle) <= 0)) {
                leftY.add(point);
            } else {
                rightY.add(point);
            }
        }

        Pair left = distance(pointsOrderedOnX, low, mid, leftY.toArray(new Point[0]));
        Pair right = distance(pointsOrderedOnX, mid + 1, high, rightY.toArray(new Point[0]));
        Pair best = left.getDistance() <= right.getDistance() ? left : right;
        double d = best.getDistance();

        List<Point> strip = new ArrayList<>();
        for (Point point : pointsOrderedOnY) {
            if (Math.abs(point.x - middle.x) < d) {
                strip.add(point);
            }
        }

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size()
                    && strip.get(j).y - strip.get(i).y < d; j++) {
                double candidate = distance(strip.get(i), strip.get(j));
                if (candidate < best.getDistance()) {
                    best = new Pair(strip.get(i), strip.get(j));
                    d = candidate;
                }
            }
        }
        return best;
    }

    public static double distance(Point p1, Point p2) {
        return distance(p1.x, p1.y, p2.x, p2.y);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static Pair bruteForce(Point[] points, int low, int high) {
        Pair best = null;
        for (int i = low; i <= high; i++) {
            for (int j = i + 1; j <= high; j++) {
                if (best == null || distance(points[i], points[j]) < best.getDistance()) {
                    best = new Pair(points[i], points[j]);
                }
            }
        }
        return best;
    }

    public static class Point implements Comparable<Point> {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            if (x != other.x) {
                return Double.compare(x, other.x);
            }
            return Double.compare(y, other.y);
        }
    }

    public static class CompareY implements Comparator<Point> {
        @Override
        public int compare(Point first, Point second) {
            if (first.y != second.y) {
                return Double.compare(first.y, second.y);
            }
            return Double.compare(first.x, second.x);
        }
    }

    public static class Pair {
        public Point p1;
        public Point p2;

        public Pair(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double getDistance() {
            return Exercise_22_07.distance(p1, p2);
        }
    }
}
