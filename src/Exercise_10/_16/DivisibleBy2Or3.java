package Exercise_10._16;

import java.math.BigInteger;

public class DivisibleBy2Or3 {
    public static void main(String[] args) {
        // BigNum represents Long.MAX_VALUE
        BigInteger bigNum = new BigInteger(
    "10000000000000000000000000000000000000000000000000");
        // BigNum is now larger than Long.MAX_VALUE
        bigNum.add(BigInteger.ONE);

        int count = 1;
        while (count <= 10) {
            // Todo
            if (bigNum.remainder(new BigInteger("2")).equals(BigInteger.ZERO) ||
                bigNum.divide(new BigInteger("3")).equals(BigInteger.ZERO)) {
                System.out.println(bigNum);
                count++;
            }

            bigNum = bigNum.add(BigInteger.ONE);
        }
    }
}
