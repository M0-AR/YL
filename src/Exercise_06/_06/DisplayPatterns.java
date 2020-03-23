package Exercise_06._06;
/*
(Display patterns) Write a method to display a pattern as follows:
                  1
                2 1
              3 2 1
    ...
    n n-1 ... 3 2 1
The method header is
public static void displayPattern(int n)
*/
import java.util.Scanner;

public class DisplayPatterns {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter line number: " );
        int lineNumber = input.nextInt();
        displayPattern( lineNumber );
    }

    public static void displayPattern(int n) {
        for (int i = 1; i < n; i++) {
            // Print spaces
            for (int j = n - i; j > 0; j--) {
                System.out.print( "  " );
            }

            // Print numbers
            for (int j = i; j > 0; j--) {
                System.out.print( " " + j );
            }

            System.out.println();
        }
    }
}
