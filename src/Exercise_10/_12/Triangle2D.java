package Exercise_10._12;

import Exercise_10._04.MyPoint;

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
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    /** Test whether the MyPoint inside this Triangle.*/
    public boolean contains(MyPoint p) {
        return false;
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
