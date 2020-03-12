package Exercise_18._05;

public class SumSeries01 {
    /** Main method */
    public static void main(String[] args) { // github
        System.out.println( sumSeries( 10 ) );
    }

    public static double sumSeries(int n) {
        if (n == 3)
            return 3;
        return n / (2 * n + 1.0) + sumSeries( n - 1 );
    }


}
