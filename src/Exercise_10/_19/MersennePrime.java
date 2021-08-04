package Exercise_10._19;

import java.math.BigInteger;

public class MersennePrime {
    public static void main(String[] args) {
        final int LAST_DIGIT = 100;
        System.out.printf( "%-15s%s\n", "p", "2^p - 1" );
        System.out.println( "---------------------------" );
        for (int i = 2; i <= LAST_DIGIT; i++) {
            if (isPrime(i)) {
                System.out.printf( "%-15d%d\n", i, mersennePrime(i));
            }
        }
    }

    private static boolean isPrime(int num) {
        for (int divisor = 2; divisor < num / 2; divisor++) {
            if (num % divisor == 0)
                return false;
        }

        return true;
    }

    /**
     * Return the mersenne prime of number
     */
    public static BigInteger mersennePrime(int num) {
        BigInteger bigNum = new BigInteger("2");
        bigNum = bigNum.pow(num);
        bigNum = bigNum.subtract(BigInteger.ONE);
        return bigNum;
    }

}
