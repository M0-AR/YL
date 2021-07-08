package Exercise_10._12;

import Exercise_10._04.MyPoint;

import java.awt.geom.Line2D;

public class Triangle2D {
    private MyPoint p1;
    private MyPoint p2;
    private MyPoint p3;

    public Triangle2D() {
        this.p1 = new MyPoint(0, 0);
        this.p2 = new MyPoint(1, 1);
        this.p3 = new MyPoint(2, 5);
    }

    public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public MyPoint getP1() {
        return p1;
    }

    public void setP1(MyPoint p1) {
        this.p1 = p1;
    }

    public MyPoint getP2() {
        return p2;
    }

    public void setP2(MyPoint p2) {
        this.p2 = p2;
    }

    public MyPoint getP3() {
        return p3;
    }

    public void setP3(MyPoint p3) {
        this.p3 = p3;
    }

    public double getArea() {
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);

        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s -side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return p1.distance(p2) + p1.distance(p3) + p2.distance(p3);
    }

    /** Test whether the MyPoint inside this Triangle.*/
    public boolean contains(double x, double y) {
        double maxX = Math.max(p1.getX(), Math.max(p2.getX(), p3.getX()));
        double minX = Math.min(p1.getX(), Math.min(p2.getX(), p3.getX()));
        double maxY = Math.max(p1.getY(), Math.max(p2.getY(), p3.getY()));
        double minY = Math.min(p1.getY(), Math.min(p2.getY(), p3.getY()));

        if (x < minX || x > maxX || y < minY || y > maxY) {
            return false; // Outside the bounding rectangle of the triangle
        }

        Line2D side1 = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        Line2D side2 = new Line2D.Double(p1.getX(), p1.getY(), p3.getX(), p3.getY());
        Line2D side3 = new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY());

        if (side1.contains(x, y) || side2.contains(x, y) || side3.contains(x, y)) {
            return true; // On the border of the triangle
        }

        double topY = -10;
        if (x == p1.getX()) {
            topY = p1.getY();
        }
        else if (x == p2.getX()) {
            topY = p2.getY();
        }
        else if (x == p3.getX()) {
            topY = p3.getY();
        }

        Line2D line;
        if (y < topY)
            line = new Line2D.Double(x, y, x, minY);
        else
            line = new Line2D.Double(x, y, x, maxY);

        int hits = 0;
        if (line.intersectsLine(side1))
            hits++;

        if (line.intersectsLine(side2))
            hits++;

        if (line.intersectsLine(side3))
            hits++;

        if (hits % 2 == 0)
            return false;
        else
            return true;
    }

    /** Test whether the Triangle inside this Triangle.*/
    public boolean contains(Triangle2D t) {
        return false;
    }

    /** Test whether the Triangle overlaps this Triangle.*/
    public boolean overlaps(Triangle2D t) {
        return false;
    }
}
