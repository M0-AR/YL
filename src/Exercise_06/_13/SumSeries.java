package Exercise_06._13;
/*
(Sum series) Write a method to compute the following series:
	m(i) = 1 / 2 + 2 / 3 + ... + i / (i + 1)
Write a test program that displays the following table:
i       m(i)
--------------
1        0.5000
2        1.1667
...
19       16.4023
20       17.3546
*/
public class SumSeries {
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
