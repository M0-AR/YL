package Exercise_10._17;

import java.math.BigInteger;
// Todo: check if the solution is correct.
public class SquareNumbers {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        BigInteger bigInteger = new BigInteger(Long.MAX_VALUE + "");
        for (int i = 0; i < 10; i++) {
            bigInteger = bigInteger.add(BigInteger.valueOf(Long.parseLong(i + "")));
            bigInteger = bigInteger.multiply(bigInteger);
            System.out.println(bigInteger);
        }
    }
}
