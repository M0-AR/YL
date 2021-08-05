package Exercise_10._20;

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
