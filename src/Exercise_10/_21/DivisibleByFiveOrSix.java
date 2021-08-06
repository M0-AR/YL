package Exercise_10._21;
/*******************************************************************************
 * (Divisible by 5 or 6) Find the first 10 numbers greater than Long.MAX_VALUE *
 * that are divisible by 5 or 6.                                               *
 ******************************************************************************/
import java.math.BigInteger;
// Todo: check if the result is correct.
public class DivisibleByFiveOrSix {
    public static void main(String[] args) {
        // BigNum represents Long.MAX_VALUE
        BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");

        int count = 1;
        while (count <= 10) {
            if (bigNum.remainder(new BigInteger("5")).equals(BigInteger.ZERO) ||
                bigNum.remainder(new BigInteger("6")).equals(BigInteger.ZERO)) {
                System.out.println(bigNum);
                count++;
            }
            bigNum = bigNum.add(BigInteger.ONE);
        }
    }
}
