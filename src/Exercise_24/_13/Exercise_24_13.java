package Exercise_24._13;

public class Exercise_24_13 {
    public static void main(String[] args) {
        FibonacciIterator iterator = new FibonacciIterator(100_000);
        StringBuilder numbers = new StringBuilder();
        int count = 0;
        while (iterator.hasNext()) {
            numbers.append(iterator.next()).append(' ');
            count++;
        }
        System.out.println("Fibonacci numbers <= 100000: " + numbers.toString().trim());
        System.out.println("Count: " + count);
    }
}
