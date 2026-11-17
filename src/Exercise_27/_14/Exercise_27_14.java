package Exercise_27._14;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Exercise_27_14 {
  public static class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (!(object instanceof Point)) {
        return false;
      }
      Point point = (Point) object;
      return x == point.x && y == point.y;
    }

    public int hashCode() {
      return Objects.hash(x, y);
    }

    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  public static void main(String[] args) {
    Point first = new Point(1, 2);
    Point second = new Point(1, 2);
    check(first.equals(second));
    check(first.hashCode() == second.hashCode());
    Set<Point> points = new HashSet<>();
    points.add(first);
    points.add(second);
    check(points.size() == 1);
    System.out.println("Exercise_27_14 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
