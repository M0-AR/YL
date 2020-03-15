package Exercise_18._07;

import java.util.Scanner;

public class FibonacciSeries { // github
    static int count = 0;
    /**
     * Main method
     */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );
        System.out.print("Enter an index for a Fibonacci number: ");
        int index = input.nextInt();

        // Find and display the Fibonacci number
        System.out.println(
                "Fibonacci number at index " + index + " is " + fibonacci(index));

        // Display the number of times fib(index) is called
        System.out.println("The fib method was called "
                + count + (count == 1 ? " time." : " times."));
    }


    public static int fibonacci(int n){
        count++;// Increment count

        if (n <= 1) // Base Case
            return n;

        // Reduction and recursive calls
        return fibonacci( n -1) + fibonacci( n -2 );
    }


}
