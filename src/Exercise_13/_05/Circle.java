package Exercise_13._05;
/***************************************************
 *                    Circle2D                     *
 *-------------------------------------------------*
 * -radius; double                                 *
 *-------------------------------------------------*
 * +Circle2D()                                     *
 * +Circle2D(radius: double)                       *
 **************************************************/
public class Circle extends GeometricObject {
    private double radius;

    public Circle() {
        this.radius = 1;
    }

    public Circle(double radius) {
        this.radius = radius;
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
}
