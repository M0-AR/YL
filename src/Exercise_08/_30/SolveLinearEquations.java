package Exercise_08._30;
/*********************************************************************************
 * (Algebra: solve linear equations) Write a method that solves the following     *
 * 2 * 2 system of linear equations:                                              *
 * The method header is                                                           *
 *                                                                                *
 * public static double[] linearEquation(double[][] a, double[] b)                *
 *                                                                                *
 * The method returns null if a00a11 - a01a10 is 0. Write a test program that     *
 * prompts the user to enter a00, a01, a10, a11, b0, and b1, and displays the     *
 * result. If a00a11 - a01a10 is 0, report that “The equation has no solution.”   *
 * A sample run is similar to Programming Exercise 3.3.                           *
 *********************************************************************************/
import java.util.Scanner;

public class SolveLinearEquations {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        System.out.print("Enter a00, a01, a10, a11, b0, and b1: ");
        double[][] a = new double[2][2];
        double[] b = new double[2];
        a[0][0] = input.nextDouble();
        a[0][1] = input.nextDouble();
        a[1][0] = input.nextDouble();
        a[1][1] = input.nextDouble();
        b[0] = input.nextDouble();
        b[1] = input.nextDouble();

        double[] result = linearEquation(a, b);

        if (result == null)
            System.out.println("The equation has no solution");
        else
            System.out.println("x is " + result[0] + " and y is " + result[1]);
    }

    private static double[] linearEquation(double[][] a, double[] b) {
        double detA = a[0][0]*a[1][1] - a[0][1]*a[1][0];
        if (detA == 0) {
            return null;
        } else {
            double x = (b[0] * a[1][1] - b[1] * a[0][1]) / detA;
            double y = (b[1] * a[0][0] - b[0] * a[1][0]) / detA;

            return new double[]{x, y};
        }
    }
}
