package Exercise_10._11;

public class Circle2D {
    private double x;
    private double y;
    private double radius;

    public Circle2D() {
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }

    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public boolean contains(double x, double y) {
        return false;
    }

    public boolean contains(Circle2D circle) {
        return false;
    }

    public boolean overlaps(Circle2D circle) {
        return false;
    }
}
