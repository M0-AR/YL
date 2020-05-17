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
    double width; // Width of rectangle
    double height; // Height of rectangle

    /** A no-arg constructor that creates a default rectangle */
    public Rectangle(){
        this(1,1);
    }

    /** A constructor that creates a rectangle
     *  with specified with and height        */
    public Rectangle(double newWidth, double newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    /** Return the area of this rectangle */
    public double getArea(){
        return width * height;
    }

    /** Return the perimeter of this rectangle */
    public double getPerimeter(){
        return 2 * (width + height);
    }
}
