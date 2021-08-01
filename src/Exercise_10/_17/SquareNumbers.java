package Exercise_10._17;
/***********************************************************************************
 * (Square numbers) Find the first 10 square numbers that are greater than Long.   *
 * MAX_VALUE. A square number is a number in the form of n2. For example, 4, 9,    *
 * and 16 are square numbers. Find an efficient approach to run your program fast. *                                                           *
 ***********************************************************************************/
import java.math.BigInteger;

public class SquareNumbers {
    public static void main(String[] args) {
        long longMax = (long) Math.sqrt(Long.MAX_VALUE);

        BigInteger bigInteger;
        for (int i = 0; i < 10; i++) {
            bigInteger = new BigInteger(longMax + i + 1 + "");
            bigInteger = bigInteger.multiply(bigInteger);
            System.out.println(bigInteger);
        }
    }
}
