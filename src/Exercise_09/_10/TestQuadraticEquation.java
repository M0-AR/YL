package Exercise_09._10;

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
