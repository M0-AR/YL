package Exercise_10._18;
/******************************************************************************
 * (Large prime numbers) Write a program that finds five prime numbers larger *
 * than Long.MAX_VALUE.                                                       *
 ******************************************************************************/
import java.math.BigInteger;

public class LargePrimeNumbers {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // BigNum represents Long.MAX_VALUE
        BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");
        // BigNum is now larger than Long.MAX_VALUE
        bigNum.add(BigInteger.ONE);

        int count = 1;
        while (count <= 5) {
            if (isPrime(bigNum)) {
                System.out.println(bigNum);
                count++;
            }

            bigNum = bigNum.add(BigInteger.ONE);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time spent is " +
                (endTime - startTime) + " milliseconds");
    }

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

}
