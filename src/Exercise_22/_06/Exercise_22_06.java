package Exercise_22._06;

public class Exercise_22_06 {
    public static void main(String[] args) {
        long[] fib = new long[46];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        System.out.print("      ");
        for (int i = 40; i <= 45; i++) {
            System.out.printf("%14d", i);
        }
        System.out.println();

        System.out.print("Listing 22.3 GCD");
        for (int i = 40; i <= 45; i++) {
            long start = System.currentTimeMillis();
            gcd(fib[i], fib[i - 1]);
            long end = System.currentTimeMillis();
            System.out.printf("%14d", end - start);
        }
        System.out.println();

        System.out.print("Listing 22.4 GCDEuclid");
        for (int i = 40; i <= 45; i++) {
            long start = System.currentTimeMillis();
            gcdEuclid(fib[i], fib[i - 1]);
            long end = System.currentTimeMillis();
            System.out.printf("%14d", end - start);
        }
        System.out.println();
    }

    public static long gcd(long m, long n) {
        long gcd = 1;
        for (long k = 2; k <= m && k <= n; k++) {
            if (m % k == 0 && n % k == 0) {
                gcd = k;
            }
        }
        return gcd;
    }

    public static long gcdEuclid(long m, long n) {
        if (m % n == 0) {
            return n;
        }
        return gcdEuclid(n, m % n);
    }
}
