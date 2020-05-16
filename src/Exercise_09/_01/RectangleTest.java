package Exercise_09._01;


public class RectangleTest {
    /** Main method*/
    public static void main(String[] args) {
        // Creates two Rectangle objects
        Rectangle rectangle1 = new Rectangle( 4, 40 );
        Rectangle rectangle2 = new Rectangle( 3.5, 35.9 );

        // Display width, height, area and perimeter of Rectangle
        System.out.println("Rectangle 1 has: " +
                            "\nArea = " + rectangle1.getArea() +
                            "\nPerimeter = " + rectangle1.getPerimeter());

        System.out.println();

        System.out.println("Rectangle 2 has: " +
                "\nArea = " + rectangle2.getArea() +
                "\nPerimeter = " + rectangle2.getPerimeter());
    }
}