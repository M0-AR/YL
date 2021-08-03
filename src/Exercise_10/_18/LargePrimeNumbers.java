package Exercise_10._18;
/******************************************************************************
 * (Large prime numbers) Write a program that finds five prime numbers larger *
 * than Long.MAX_VALUE.                                                       *
 ******************************************************************************/
import java.math.BigInteger;

public class LargePrimeNumbers {
    public static void main(String[] args) {
        // BigNum represents Long.MAX_VALUE
        BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");
        // BigNum is now larger than Long.MAX_VALUE
        bigNum.add(BigInteger.ONE);

        int count = 1;
        while (count <= 5) {
            if (!bigNum.remainder(new BigInteger("2")).equals(BigInteger.ZERO) &&
                    !bigNum.divide(new BigInteger("3")).equals(BigInteger.ZERO)) {
                System.out.println(bigNum);
                count++;
            }

            bigNum = bigNum.add(BigInteger.ONE);
        }
    }
}
