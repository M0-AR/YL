package Exercise_05._39;

public class FindSalesAmount {
    public static void main(String[] args) {
        double amount = 25000;

        for (double i = 0;i <= 30000; amount+= 5000) {
            i = (5000 * 0.08) + ( 5000 * 0.1) + ((amount - 10000) * 0.12);
        }

        // Display result
        System.out.println("The minuimum number of sales to make $30000 is " + amount);

    }
}
