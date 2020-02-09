package Exercise_05._14;

import java.util.Scanner;

public class ComputeTheGreatestCommonDivisor { // g and y
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // 1. Prompt a user to enter two integers numbers
        System.out.print(
                "Enter the first number: ");
        int n1 = input.nextInt();

        System.out.print(
                "Enter the second number: ");
        int n2 = input.nextInt();


        // 2. Find GCD
        int GCD = (n1 < n2)? n1: n2; // Math.min(n1 ,n2); d contain the smallest element
        while (n1 % GCD != 0 || n2 % GCD !=0)
            GCD--;

        // or
        int d ;
        for (d = Math.min( n1, n2 ); d >= 1; d--) {
            if ((n1 % d == 0) && (n2 % d == 0)) {
                break;
            }
        }


        // 3. Display result
        System.out.println("GCD of " + n1 + " and " + n2 + " is " + GCD);
        System.out.println("GCD of " + n1 + " and " + n2 + " is " + d);

    }
}
