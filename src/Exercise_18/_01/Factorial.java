package Exercise_18._01;

import java.math.BigInteger;
import java.util.Scanner;

/*********************************************************************************
 * (Factorial) Using the BigInteger class introduced in Section 10.9, you can     *
 * find the factorial for a large number (e.g., 100!). Implement the factorial    *
 * method using recursion. Write a program that prompts the user to enter an      *
 * integer and displays its factorial.                                            *
 *********************************************************************************/
public class Factorial { // github
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        String n = input.nextLine();

        // Display factorial
        System.out.println("Factorial of " + n + " is "
                + factorial(new BigInteger(n)));
    }


    /** Return the factorial for the specified number */
    public static BigInteger factorial(BigInteger n){
        if (n.equals( BigInteger.ZERO )) // Base case
            return BigInteger.ONE;

        return n.multiply( factorial( n.subtract( BigInteger.ONE ) ) ); // Recursive call
    }
}
