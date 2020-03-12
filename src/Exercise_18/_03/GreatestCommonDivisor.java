package Exercise_18._03;

public class GreatestCommonDivisor {
    /**
     * Main method
     */
    public static void main(String[] args) {
        System.out.println( gcd( 186, 76 ) );
    }

    public static int gcd(int m, int n) {
        if (m % n == 0)
            return n;

        return gcd( n, m % n );

    }
}
