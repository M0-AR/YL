package Exercise_06._11;

/*
(Financial application: compute commissions) Write a method that computes the
commission, using the scheme in Programming Exercise 5.39. The header of the
method is as follows:
public static double computeCommission(double salesAmount)
Write a test program that displays the following table:
               Sales Amount        Commission
               --------------------------------
               10000                 900.0
               15000                1500.0
               ...
               95000               11100.0
               100000              11700.0
*/
public class ComputeCommissions {
    public static void main(String[] args) {
        System.out.printf( "%-15s%-15s\n", "SalesAmount", "Commission" );
        System.out.println( "----------------------------" );

        for (int salesAmount = 10000; salesAmount <= 100000; salesAmount += 5000) {
            System.out.printf( "%-15d%-15.1f\n", salesAmount, computeCommission( salesAmount ) );
        }


    }

    public static double computeCommission(double salesAmount) {
        return (5000 * 0.08) + (5000 * 0.1) + ((salesAmount - 10000) * 0.12);
    }


}
