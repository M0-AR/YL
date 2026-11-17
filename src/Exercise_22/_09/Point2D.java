package Exercise_22._09;

public class Point2D {
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point2D other) {
        return Math.hypot(x - other.x, y - other.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
