package Exercise_10._11;
                   // UML diagram
/***************************************************
 *                    Circle2D                     *
 *-------------------------------------------------*
 * -x: double                                      *
 * -y: double                                      *
 * -radius; double                                 *
 *-------------------------------------------------*
 * +Circle2D()                                     *
 * +Circle2D(x: double, y: double, radius: double) *
 * +contains(x: double, y: double): boolean        *
 * +contains(circle: Circle2D): boolean            *
 * +overlaps(circle: Circle2D): boolean            *
 **************************************************/
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
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /** Test whether the (x, y) inside this circle.*/
    public boolean contains(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) +
                         Math.pow(y - this.y, 2))
                         < radius;
    }

    /** Test whether the circle inside this circle.*/
    public boolean contains(Circle2D circle) {
        return Math.sqrt(Math.pow(circle.getX()- this.x, 2) +
                         Math.pow(circle.getY() - this.y, 2))
                         <= Math.abs(radius - circle.getRadius());
    }

    /** Test whether the circle overlaps this circle.*/
    public boolean overlaps(Circle2D circle) {
        return Math.sqrt(Math.pow(circle.getX()- this.x, 2) +
                         Math.pow(circle.getY() - this.y, 2))
                         <= radius + circle.getRadius();
    }
}
