package Exercise_09._11;
/*********************************************************************************
 * (Algebra: 2 * 2 linear equations) Design a class named LinearEquation for a    *
 * 2 * 2 system of linear equations:                                              *
 *                                                                                *
 *  ax + by = e, cx + dy = f; x = (ed - bf) / (ad - bc) y = (af - ec) / (ad - bc) *
 *                                                                                *
 * The class contains:                                                            *
 * ■ Private data fields a, b, c, d, e, and f.                                    *
 * ■ A constructor with the arguments for a, b, c, d, e, and f.                   *
 * ■ Six getter methods for a, b, c, d, e, and f.                                 *
 * ■ A method named isSolvable() that returns true if ad - bc is not 0.           *
 * ■ Methods getX() and getY() that return the solution for the equation.         *
 *                                                                                *
 * Draw the UML diagram for the class and then implement the class. Write a test  *
 * program that prompts the user to enter a, b, c, d, e, and f and displays the   *
 * result. If ad - bc is 0, report that “The equation has no solution.” See       *
 * Programming Exercise 3.3 for sample runs.                                      *
 *********************************************************************************/
import java.util.Scanner;

public class TestLinearEquation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a, b, c, d, e, and f: ");
        double a = input.nextInt();
        double b = input.nextInt();
        double c = input.nextInt();
        double d = input.nextInt();
        double e = input.nextInt();
        double f = input.nextInt();

        LinearEquation equation = new LinearEquation(a, b, c, d, e, f);

        if (equation.isSolvable())
            System.out.println("Display X and Y: \n" + equation.getX()
                + " and " + equation.getY());
        else
            System.out.println("The equation has no solution.");
    }
}
