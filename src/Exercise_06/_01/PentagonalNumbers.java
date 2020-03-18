package Exercise_06._01;
/*
(Math: pentagonal numbers) A pentagonal number is defined as n(3n–1)/2 for
n = 1, 2, . . ., and so on. Therefore, the first few numbers are 1, 5, 12, 22, . . . .
Write a method with the following header that returns a pentagonal number:
public static int getPentagonalNumber(int n)
Write a test program that uses this method to display the first 100 pentagonal
numbers with 10 numbers on each line.
*/
public class PentagonalNumbers {
    public static void main(String[] args) {
        printPentagonalNumber( 100 );
    }

    public static int pentagonalNumber(int n) {
        return n * (3 * n - 1) / 2;
    }

    /**
     * @param n Numbers of pentagonal
     */
    public static void printPentagonalNumber(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.printf( "%7d", pentagonalNumber( i ) );
            if (i % 10 == 0)
                System.out.println();
        }
    }
}
