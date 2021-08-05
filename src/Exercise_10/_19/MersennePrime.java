package Exercise_10._19;
/***********************************************************************************
 * (Mersenne prime) A prime number is called a Mersenne prime if it can be written *
 * in the form 2p - 1 for some positive integer p. Write a program that finds      *
 * all Mersenne primes with p … 100 and displays the output as shown below.        *
 * (Hint: You have to use BigInteger to store the number because it is too big to  *
 * be stored in long. Your program may take several hours to run.)                 *
 *  p           2^p – 1                                                            *
 *  ---------------------                                                          *
 *  2           3                                                                  *
 *  3           7                                                                  *
 *  5           31                                                                 *
 ***********************************************************************************/
import java.math.BigInteger;

public class MersennePrime {
    public static void main(String[] args) {
        System.out.printf( "%-15s%s\n", "p", "2^p - 1" );
        System.out.println( "---------------------------" );


        for (BigInteger p = new BigInteger("2");
             p.compareTo(new BigInteger("100")) <= 0;
             p = p.add(BigInteger.ONE)) {
            if (isPrime(p)) {
                System.out.printf( "%-15d%d\n", p, mersennePrime(p));
            }
        }
    }

    /**
     * Check whether the number is prime.
     * @param num
     * @return
     */
    public static boolean isPrime(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) == 0 ||
                num.compareTo(new BigInteger("2")) == 0) {
            return true;
        }

        BigInteger halfNum = num.divide(new BigInteger("2"));

        for (BigInteger divisor = new BigInteger("2");
             divisor.compareTo(halfNum) <= 0;
             divisor = divisor.add(BigInteger.ONE)) {
            if (num.remainder(divisor).equals(BigInteger.ZERO)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Find and Return the mersenne prime of num.
     * @param num
     * @return
     */
    public static BigInteger mersennePrime(BigInteger num) {
        BigInteger two = new BigInteger("2");
        BigInteger result = new BigInteger("2");
        for (BigInteger i = new BigInteger("1");
             i.compareTo(num) < 0;
             i = i.add(BigInteger.ONE)) {
           result = result.multiply(two);
        }
        return result.subtract(BigInteger.ONE);
    }
}
