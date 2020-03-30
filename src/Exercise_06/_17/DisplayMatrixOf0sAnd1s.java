package Exercise_06._17;
/*
(Display matrix of 0s and 1s) Write a method that displays an n-by-n matrix using
the following header:
public static void printMatrix(int n)
Each element is 0 or 1, which is generated randomly. Write a test program that
prompts the user to enter n and displays an n-by-n matrix.
*/
import java.util.Scanner;

public class DisplayMatrixOf0sAnd1s {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter n
        System.out.print("Enter n: ");
        int n = input.nextInt();

        // Display an n by n*n matrix
        printMatrix( n );

    }

    /** Method printMatrix displays an n by n*n matrix */
    public static void printMatrix(int n){
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++)
                // Generate and print randomly 0 or 1
                System.out.print( (int)(Math.random() * 2) + " ");
            // Print newLine
            System.out.println();
        }
    }
}
