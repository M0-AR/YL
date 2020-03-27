package Exercise_06._14;

public class EstimatePi {
    public static void main(String[] args) { // github and y
        System.out.printf( "%-15s%-15s\n", "i", "m(i)" );
        System.out.println( "------------------------" );
        double estimatePi = 0;
        for (int i = 1; i <= 901; i += 100) {
            System.out.printf( "%-15d%-15.4f\n", i, m( i ) );
        }
    }

    public static double m(int i) {
        double pi, sign;
        pi = sign = 0.0;
        for (int j = 1; j <= i; j++) {
//            pi += Math.pow( -1, j+1 ) / (2*j-1.0); OR
            pi += sign / (2 * j - 1.0);
            sign = -1 * sign;
        }
        return 4 * pi;
    }
}
