package Exercise_06._01;

public class PentagonalNumbers {
    public static void main(String[] args) {// github
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
