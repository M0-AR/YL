package Exercise_18._04;

public class SumSeries { // github
    /** Main method */
    public static void main(String[] args) {
        System.out.println(sumSeries(10));
    }

    public static double sumSeries(double n){
        if (n == 1)
            return 1.0;

        return 1.0 / n + sumSeries( n-1 );
    }

}
