package Exercise_18._02;

/*********************************************************************************
 * (Fibonacci numbers) Rewrite the fib method in Listing 18.2 using iterations.   *
 * Hint: To compute fib(n) without recursion, you need to obtain fib(n - 2) and   *
 * fib(n - 1) first. Let f0 and f1 denote the two previous Fibonacci numbers. The *
 * current Fibonacci number would then be f0 + f1. The algorithm  can be          *
 * Write a test program that prompts the user to enter an index and displays its  *
 * Fibonacci number.                                                              *
 * *********************************************************************************/
import java.util.Scanner;

public class FibonacciNumbers {
    /** Main method */
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for the Fibonacci number: ");
        int index = input.nextInt();

        // Find and display the Fibonacci number
        System.out.println(
                "Fibonacci number at index " + index + " is " + fibonacci(index));
    }

    public static int fibonacci(int n){
        if (n <= 1) // Base Case
            return n;

        // Reduction and recursive calls
        return fibonacci( n -1) + fibonacci( n -2 );
    }
}
