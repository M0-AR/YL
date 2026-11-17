package Exercise_20._26;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise_20_26 {
  public static class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  public static void main(String[] args) {
    List<Point> points = new ArrayList<>(List.of(
        new Point(2, 0), new Point(1, 5), new Point(1, 2)));
    points.sort(Comparator.comparingInt(Point::getX).thenComparingInt(Point::getY));
    check(points.toString().equals("[(1, 2), (1, 5), (2, 0)]"));
    System.out.println("Exercise_20_26 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
