package Exercise_10._04;

public class MyPoint {
    private double x;
    private double y;

    public MyPoint() {
        this(0,0);
    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance() {
        // Todo:
        // return Math.sqrt(  );
        return 0;
    }

    public double distance(MyPoint myPoint) {
        return Math.sqrt( Math.pow((this.x - myPoint.x), 2)
                        + Math.pow((this.y - myPoint.y), 2));
    }

    public static double distance(MyPoint myPoint1, MyPoint myPoint2) {
        return Math.sqrt( Math.pow((myPoint1.x - myPoint2.x), 2)
                + Math.pow((myPoint1.y - myPoint2.y), 2));
    }
}
