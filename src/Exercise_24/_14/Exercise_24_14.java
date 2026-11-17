package Exercise_24._14;

public class Exercise_24_14 {
    public static void main(String[] args) {
        PrimeIterator iterator = new PrimeIterator(100_000);
        int count = 0;
        int last = 0;
        while (iterator.hasNext()) {
            last = iterator.next();
            count++;
        }
        System.out.println("Primes <= 100000: " + count);
        System.out.println("Last prime: " + last);
    }
}
