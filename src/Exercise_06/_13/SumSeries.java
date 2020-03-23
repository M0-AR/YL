package Exercise_06._13;

public class SumSeries { // github
    public static void main(String[] args) {
        System.out.printf( "%-15s%-15s\n","i", "m(i)" );
        System.out.println("-------------------------");
        double sumSeries = 0;
        for (int i = 1; i <= 20 ; i++)
            System.out.printf( "%-15d%-15.4f\n", i, sumSeries += m( i ) );

    }

    public static double m(int i){
        return i / (i + 1.0);
    }
}
