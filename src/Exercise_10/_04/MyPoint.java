package Exercise_10._04;

public class MyPoint {
    private double x;
    private double y;

    public MyPoint() {
    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(MyPoint secondPoint) {
        return distance( this, secondPoint );
    }

//    public static double distance(MyPoint p1, MyPoint p2) {
//        return p2.distance( p1 );
//    }

    public static double distance(MyPoint p1, MyPoint p2) {
        return Math.sqrt( Math.pow((p1.x - p2.x), 2)
                            + Math.pow((p1.y - p2.y), 2));
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
