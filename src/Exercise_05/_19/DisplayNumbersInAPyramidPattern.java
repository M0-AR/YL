package Exercise_05._19;
/*
(Display numbers in a pyramid pattern) Write a nested for loop that prints the
following output:
*/

public class DisplayNumbersInAPyramidPattern {
    public static void main(String[] args) {

        int spaces = 35; // Take care of spaces before numbers

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < spaces; System.out.print( " " ), j++) ;
            spaces -= 5;
            // Take care of left numbers
            for (int j = 0; j <= i; System.out.printf( "%5d", (int) Math.pow( 2, j ) ), j++) ;
            if (i >= 1) // // Take care of right numbers
                for (int j = i - 1; j >= 0; System.out.printf( "%5d", (int) Math.pow( 2, j ) ), j--) ;
            System.out.println();
        }
    }
}
