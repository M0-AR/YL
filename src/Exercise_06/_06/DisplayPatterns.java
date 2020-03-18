package Exercise_06._06;

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
