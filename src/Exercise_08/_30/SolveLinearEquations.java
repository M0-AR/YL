package Exercise_08._30;

import java.util.Scanner;

public class SolveLinearEquations { // yl and github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        double[][] a = new double[2][2];
        double[] b = new double[2];
        System.out.print("Enter a00, a01, a10, a11, b0, and b1: ");
        a[0][0] = input.nextDouble();
        a[0][1] = input.nextDouble();
        a[1][0] = input.nextDouble();
        a[1][1] = input.nextDouble();
        b[0] = input.nextDouble();
        b[1] = input.nextDouble();
        double[] result = linearEquation(a, b);

        if (result == null)
            System.out.println("No solution");
        else
            System.out.println("There is a solution");
    }

    private static double[] linearEquation(double[][] a, double[] b) {
        if ((a[0][0]*a[1][1] - a[0][1]*a[1][0]) == 0)
            return null;

        double x = (b[0]*a[1][1] - b[1]*a[0][1]) / (a[0][0]*a[1][1] - a[0][1]*a[1][0]);
        double y = (b[1]*a[0][0] - b[0]*a[1][0]) / (a[0][0]*a[1][1] - a[0][1]*a[1][0]);

        return  new double[]{x, y};
    }
}
