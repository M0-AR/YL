package Exercise_18._06;
/*********************************************************************************
 * (Sum series) Write a recursive method to compute the following series:         *
 *                                                                                *
 *                 m(i) = 1 / 2 + 2 / 3 + ... + i / (i + 1)                       *
 *                                                                                *
 * Write a test program that displays m(i) for i = 1, 2, . . ., 10.               *
 *********************************************************************************/
public class SumSeries02 {
    /** Main method */
    public static void main(String[] args) { // Github and YL
        System.out.printf( "%-10s %-15s \n", "i", "sumSeries(i)" );
        for (int i = 1; i <= 10 ; i++)
            System.out.printf( "%-10d %-15f\n", i, sumSeries( i ) );
    }

    public static double sumSeries(int n){
        if (n == 1)
            return 1;
        return n / (n + 1.0) + sumSeries( n - 1 );
    }


}
