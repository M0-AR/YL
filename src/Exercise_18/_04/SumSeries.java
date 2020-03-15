package Exercise_18._04;
/*********************************************************************************
 * (Sum series) Write a recursive method to compute the following series:         *
 * 				m(i) = 1 + 1 / 2 + 1 / 3 + ... + 1 / i                            *
 * Write a test program that displays m(i) for i = 1, 2, . . ., 10.               *
 *********************************************************************************/
public class SumSeries {
    /** Main method */
    public static void main(String[] args) {
        System.out.printf("%-10s%-15s\n", "i", "sumSeries(i)");
        for (int i = 1; i <= 10; i++)
            System.out.printf("%-10d%-15.6f\n", i, sumSeries(i));    }

    public static double sumSeries(double n){
        if (n == 1)
            return 1;

        return 1.0 / n + sumSeries( n-1 );
    }

}
