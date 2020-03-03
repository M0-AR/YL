package Exercise_05._42;
/*
(Financial application: find the sales amount) Rewrite Programming Exercise
5.39 as follows:
■ Use a for loop instead of a do-while loop.
■ Let the user enter COMMISSION_SOUGHT instead of fixing it as a constant.
*/
public class FindSalesAmount {
    public static void main(String[] args) {
        double amount = 1;
        double commission = 0;

        java.util.Scanner input = new java.util.Scanner( System.in );
        // Prompt the user to enter the desired commission
        System.out.print(
                "Enter the desired commission: " );
        double commissionSought = input.nextDouble();

        for ( commission = 1; commission <= commissionSought; amount++) {
            if (amount >= 10001)
                commission = (5000 * 0.08) + (5000 * 0.1) + ((amount - 10000) * 0.12);
            else if (amount>= 5001)
                commission = (5000 * 0.08) + ((amount - 5000) * 0.1) ;
            else
                commission = amount * 0.08 ;
        }

        // Display result
        System.out.println(
                "The minuimum number of sales to make $30000 is " + amount );


    }
}
