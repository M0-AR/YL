package Exercise_09._10;
/*********************************************************************************
 * (Algebra: quadratic equations) Design a class named QuadraticEquation for      *
 * a quadratic equation ax2 + bx + x = 0. The class contains:                     *
 * ■ Private data fields a, b, and c that represent three coefficients.           *
 * ■ A constructor for the arguments for a, b, and c.                             *
 * ■ Three getter methods for a, b, and c.                                        *
 * ■ A method named getDiscriminant() that returns the discriminant, which is     *
 *   b2 - 4ac.                                                                    *
 * ■ The methods named getRoot1() and getRoot2() for returning two roots of the   *
 *   equation                                                                     *
 *           r1 = (-b + √(b^2 - 4ac)) / 2a and r2 = (-b - √(b2 - 4ac)) / 2a       *
 *                                                                                *
 * These methods are useful only if the discriminant is nonnegative. Let these    *
 * methods return 0 if the discriminant is negative.                              *
 * Draw the UML diagram for the class and then implement the class. Write a test  *
 * program that prompts the user to enter values for a, b, and c and displays the *
 * result based on the discriminant. If the discriminant is positive, display the *
 * two roots. If the discriminant is 0, display the one root. Otherwise, display  *
 * “The equation has no roots.” See Programming Exercise 3.1 for sample runs.     *
 *********************************************************************************/
import java.util.Scanner;

public class TestQuadraticEquation {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter a, b, c: ");
        double a = input.nextInt();
        double b = input.nextInt();
        double c = input.nextInt();

        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        double discriminant = equation.getDiscriminant();

        if (discriminant < 0) {
            System.out.println("The equation has no roots");
        } else if (discriminant == 0)
        {
            System.out.println("The root is " + equation.getRoot1());
        }
        else // (discriminant >= 0)
        {
            System.out.println("The roots are " + equation.getRoot1()
                + " and " + equation.getRoot2());
        }
    }
}
