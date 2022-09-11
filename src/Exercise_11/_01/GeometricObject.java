package Exercise_11._01;
/******************************************************
 *                    GeometricObject                 *
 *----------------------------------------------------*
 * -side1: double                                     *
 * -side2: double                                     *
 * -side3: double                                     *
 *----------------------------------------------------*
 * +GeometricObject()                                 *
 * +GeometricObject(color: String, isFilled: boolean) *
 * +getColor()                                        *
 * +setColor(color: String)                           *
 * +isFilled()                                        *
 * +setFilled(filled: boolean)                        *
 * +toString(): String                                *
 ******************************************************/
public abstract class GeometricObject {
    private String color;
    private boolean isFilled;

    public GeometricObject() {
    }

    public GeometricObject(String color, boolean isFilled) {
        this.color = color;
        this.isFilled = isFilled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    @Override
    public String toString() {
        return "Geometric: color = " + color +
                " isFilled = " + isFilled;
    }

    // Abstract methods
    public abstract double getArea();
    public abstract double getPerimeter();
}
