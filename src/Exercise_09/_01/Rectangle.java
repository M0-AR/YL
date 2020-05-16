package Exercise_09._01;
                //UML Class Diagram
/***************************************************
 *                   Rectangle                     *
 *-------------------------------------------------*
 * width: double                                   *
 * height: double                                  *
 * ------------------------------------------------*
 * Rectangle()                                     *
 * Rectangle(newWidth: double, newHeight: double)  *
 * getArea(): double                               *
 * getPerimeter(): double                          *
 * *************************************************/
public class Rectangle {
    private double width;
    private double height;

    // Default rectangle
    public Rectangle(){
        this(1,1);
    }

    public Rectangle(double newWidth, double newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    public double getArea(){
        return width * height;
    }

    public double getPerimeter(){
        return 2 * (width + height);
    }
}
