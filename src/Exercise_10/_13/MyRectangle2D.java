package Exercise_10._13;

public class MyRectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;

    public MyRectangle2D() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
    }

    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double area() {
        return this.width * this.height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    /** Return true if (x, y) is inside this rectangle */
    public boolean contains(double x, double y) {
        return getDistance(this.x, x) <= width / 2 &&
                        getDistance(this.y, y) <= height / 2 ;
    }

    /** Return true if a rectangle is contained in this rectangle */
    public boolean contains(MyRectangle2D r) {
        return false;
    }

    public boolean overlaps(MyRectangle2D r) {
        return false;
    }

    private double getDistance(double p1, double p2) {
        return Math.sqrt(Math.pow(p2 - p1, 2));
    }}
