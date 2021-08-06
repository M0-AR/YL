package Exercise_10._20;
/*********************************************************************************
 * (Approximate e) Programming Exercise 5.26 approximates e using the following   *
 * series:                                                                        *
 *                                                                                *
 *       e = 1 + (1 / 1!) + (1 / 2!) + (1 / 3!) + (1 / 4!) + ... + (1 / i!)       *
 *                                                                                *
 * In order to get better precision, use BigDecimal with 25 digits of precision   *
 * in the computation. Write a program that displays the e value for              *
 * i = 100, 200, . . . , and 1000.                                                *
 *********************************************************************************/
import java.math.BigDecimal;

public class ApproximateE {
    public static void main(String[] args) {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal item = BigDecimal.ONE;

        for (int i = 1; i <= 1000; i++) {
            item = item.divide(new BigDecimal(i + ""),
                    25,
                    BigDecimal.ROUND_UP);
            e = e.add(item);

            if (i % 100 == 0)
                System.out.println("The e is " + e + " for i = " + i);
        }
    }
}
