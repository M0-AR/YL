package Exercise_05._26;

public class Compute_e {
    public static void main(String[] args) { // github and y
        int k = 10000; // k is 10000, 20000,..., and 100000
        while (k <= 100000) {
            double e, factorial;
            e = factorial = 1;

            for (int i = 1; i < k; i++) {
                factorial *= i;
                e += 1 / factorial;
            }

            System.out.println( e );

            k+=10000;
        }
    }
}
