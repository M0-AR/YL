package Exercise_18._11;

/*********************************************************************************
 * (Sum the digits in an integer using recursion) Write a recursive method that   *
 * computes the sum of the digits in an integer. Use the following method header: *
 *                                                                                *
 * public static int sumDigits(long n)                                            *
 *                                                                                *
 * For example, sumDigits(234) returns 2 + 3 + 4 = 9. Write a test program that   *
 * prompts the user to enter an integer and displays its sum.                     *
 *********************************************************************************/
import java.util.Scanner;

public class SumDigitsUsingRecursion {// Github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter an integer: ");
        long l = input.nextLong();
        System.out.println(sumDigit(l));
    }


    // My method
    public static int sumDigit(long n){
        if (n / 10 == 0)
            return (int) n;
        return (int) ((n%10) + sumDigit( n / 10 ));
    }

    // Another method
    /* Method sums the digits in an integer */
    public static int sumDigits(long n) {
        return sumDigits(n, 0);
    }

    /* Helper overloaded method */
    private static int sumDigits(long n, int sum) {
        if (n == 0) // Base case
            return sum;
        else // Recursive call
            return sumDigits(n / 10, sum + (int)(n % 10));
    }


}
