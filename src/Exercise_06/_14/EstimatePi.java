package Exercise_06._14;

public class EstimatePi {
    public static void main(String[] args) { // github and y
        System.out.printf( "%-15s%-15s\n","i","m(i)" );
        System.out.println("------------------------");
        double estimatePi = 0;
        for (int i = 10; i <= 100; i+=10) {
            System.out.printf( "%-15d%-15.4f\n", i , m( i ) );
        }
    }

    public static double m(int i){
//        return (Math.pow( -1, i+1 ) / (2*i-1.0));
//        return 4.0 * (1.0 / (2 * i -1) - 1.0 / (2 * i + 1));
        double pi, term;
        pi = term = 0.0;
        for (int j = 1; j <= i ; j+=2) {
            term = 4.0 * (1.0 / (2 * i -1) - 1.0 / (2 * i + 1));
            pi = term;
        }
        return pi;
    }
}
