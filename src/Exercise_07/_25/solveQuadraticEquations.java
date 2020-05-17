package Exercise_07._25;

import java.util.Scanner;

public class solveQuadraticEquations { // github
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Store constants of quadratic equation a, b and c
        double[] eqn = new double[3];

        // Store the roots of quadratic equation
        double[] roots = new double[2];

        // Prompt the user to enter a, b and c
        System.out.print( "Enter a, b and c (e.g, 1 2 3): " );
        for (int i = 0; i < 3; i++)
            eqn[i] = input.nextDouble();

        int numberOfRoots = solveQuadratic( eqn, roots );
        System.out.println("The number of real roots is " +
                numberOfRoots + ((numberOfRoots == 0)? "\nHas 0 root  ":
                                 (numberOfRoots == 1)? "\nHas 1 root  " + roots[0]:
                                                       "\nHas 2 roots " + roots[0] + " " + roots[1]));

    }

    public static int solveQuadratic(double[] eqn, double[] roots){
        // discriminant = b^2−4ac
        double d = Math.pow( eqn[1], 2 ) - 4*eqn[0]*eqn[2];

        if (d < 0){
            return 0; // Has 0 real solutions
        }else if (d == 0){
            roots[0] = -eqn[1] / 2*eqn[0]; // Has 1 real solutions
            return 1;
        }else {
            roots[0] = (-eqn[1] + Math.sqrt( d )) / 2*eqn[0];
            roots[1] = (-eqn[1] - Math.sqrt( d )) / 2*eqn[0];
            return 2; // Has 2 real solutions
        }

    }
}
