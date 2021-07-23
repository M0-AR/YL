package Exercise_10._13;
                    // UML
/************************************************
 *                 MyRectangle2D                *
 *----------------------------------------------*
 * -x: double                                   *
 * -y: double                                   *
 * -width: double                               *
 * -height: double                              *
 *----------------------------------------------*
 * +contains(x: double, y: double): boolean     *
 * +contains(r: MyRectangle2D): boolean         *
 * +overlaps(r: MyRectangle2D): boolean         *
 * +getDistance(p1: double, p2: double): double *
 ************************************************/
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

    /** Return true if a rectangle overlaps this rectangle */
    public boolean overlaps(MyRectangle2D r) {
        return contains(r) &&
                ((x + width / 2 > r.getX() - r.getWidth()) ||
                        (y + height / 2 > r.getY() - r.getHeight())) &&
                (getDistance(x, r.getX()) < width / 2 + r.getWidth() / 2) &&
                (getDistance(y, r.getY()) < height / 2 + r.getHeight());
    }

    /** Return true if a rectangle is inside this rectangle */
    public boolean contains(MyRectangle2D r) {
        return  getDistance(x, r.getX()) + r.getWidth() / 2 <= width / 2 &&
                getDistance(y, r.getY()) + r.getHeight() / 2 <= height / 2 &&
                width / 2 + r.getWidth() / 2 <= width &&
                height / 2 + r.getHeight() / 2 <= height;
    }

    private double getDistance(double p1, double p2) {
        return Math.sqrt(Math.pow(p2 - p1, 2));
    }}
