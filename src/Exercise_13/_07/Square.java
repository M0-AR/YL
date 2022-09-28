package Exercise_13._07;

import Exercise_13._05.GeometricObject;

public class Square extends GeometricObject implements Colorable {
    private double side;

    public Square() {
        this.side = 0;
    }

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String howToColor() {
        return "Color all four sides.";
    }
}
