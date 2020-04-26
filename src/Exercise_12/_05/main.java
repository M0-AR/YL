package Exercise_12._05;

import java.util.Scanner;

/*********************************************************************************
 * (IllegalTriangleException) Programming Exercise 11.1 defined the Triangle class*
 * with three sides. In a triangle, the sum of any two sides is greater than the  *
 * other side. The Triangle class must adhere to this rule. Create the            *
 * IllegalTriangleException class, and modify the constructor of the Triangle     *
 * class to throw an IllegalTriangleException object if a triangle is created     *
 * with sides that violate the rule, as follows:                                  *
 *                                                                                *
 * /** Construct a triangle with the specified sides */                          /*
 *public Triangle(double side1, double side2, double side3)                       *
 *   throws IllegalTriangleException {                                            *
 *   // Implement it                                                              *
 * }                                                                              *
 *********************************************************************************/

public class main {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );


        boolean repeatSidesInput = true;
        do{
            // Prompt the user to enter three sides of th triangle
            System.out.print( "Enter three side of the triangle: " );
            double side1 = input.nextDouble();
            double side2 = input.nextDouble();
            double side3 = input.nextDouble();

            try{
                // Create triangle Object with user input
                new Triangle( side1, side2, side3 );
                repeatSidesInput = false;
            } catch (IllegalTriangleException e) {
                e.printStackTrace();
            }
        }while (repeatSidesInput);
    }
}


class Triangle{
    /** Construct a triangle with the specified sides */
    public Triangle(double side1, double side2, double side3) throws IllegalTriangleException {
        if (side1 + side2 <= side3 ||
            side1 + side3 <= side2 ||
            side2 + side3 <= side1)
            throw new IllegalTriangleException(side1, side2, side3);
    }
}

class IllegalTriangleException extends Exception {

    /** Construct an IllegalTriangleException */
    public IllegalTriangleException(double side1, double side2, double side3){
        super("Illegal sides " + side1 + " " + side2 + " " + side3);
    }

}
