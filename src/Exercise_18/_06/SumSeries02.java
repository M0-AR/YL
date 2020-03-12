package Exercise_18._06;

public class SumSeries02 {
    /** Main method */
    public static void main(String[] args) { // Github and YL
        System.out.println(sumSeries(10));
    }

    public static double sumSeries(int n){
        if (n == 2)
            return 2;
        return n / (n + 1.0) + sumSeries( n - 1 );
    }


}
