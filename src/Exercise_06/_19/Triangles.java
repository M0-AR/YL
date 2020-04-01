package Exercise_06._19;
// (The MyTriangle class) Create a class named MyTriangle that contains the
// following two methods:
//
// /** Return true if the sum of any two sides is
// * greater than the third side. */
// public static boolean isValid(
// 	double side1, double side2, double side3)
//
// /** Return the area of the triangle. */
// public static double area(
// 	double side1, double side2, double side3)
//
// Write a test program that reads three sides for a triangle and computes the
// the input is valid. Otherwise, it displays that the input is invalid. The formula
// computing the area of a triangle is given in Programming Exercise 2.19.
public class Triangles {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner( System.in );

        // Prompt the user to enter three side of triangle
        System.out.print( "Enter first side of triangle: " );
        int side1 = input.nextInt();
        System.out.print( "Enter second side of triangle: " );
        int side2 = input.nextInt();
        System.out.print( "Enter third side of triangle: " );
        int side3 = input.nextInt();

        if (isValid( side1, side2, side3 )) {
            System.out.println( "The area of triangle is "
                    + area( side1, side2, side3 ) );
        } else {
            System.out.println( "The input is invalid," );
        }
        // Display Result
        System.out.println(isValid( side1,side2,side3 )
                ?"The area of triangle is "+ area( side1,side2 ,side3 )
                : "The input is invalid");

    }


    /**
     * Return true if the sum of every two sides is
     * greater than the third side.
     */
    public static boolean isValid(double side1, double side2, double side3) {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }

    /**
     * Return the area of the triangle.
     */
    public static double area(double side1, double side2, double side3) {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt( s * (s - side1) * (s - side2) * (s - side3) );
    }
}
