package Exercise_18._05;
/*********************************************************************************
 * (Sum series) Write a recursive method to compute the following series:         *
 *                                                                                *
 *   m(i) = 1 / 3 + 2 / 5 + 3 / 7 + 4 / 9 + 5 / 11 + 6 / 13 + ... + i / (2i + 1)  *
 *                                                                                *
 * Write a test program that displays m(i) for i = 1, 2, . . ., 10.               *
 *********************************************************************************/
public class SumSeries01 {
    /** Main method */
    public static void main(String[] args) {
        System.out.printf("%-10s%-15s\n", "i", "sumSeries(i)");
        for (int i = 1; i <= 10; i++)
            System.out.printf("%-10d%-15.6f\n", i, sumSeries(i));
    }

    public static double sumSeries(int n) {
        if (n == 1)
            return 1;
        return n / (2 * n + 1.0) + sumSeries( n - 1 );
    }


}
