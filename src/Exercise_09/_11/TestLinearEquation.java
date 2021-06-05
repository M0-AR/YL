package Exercise_09._11;

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
