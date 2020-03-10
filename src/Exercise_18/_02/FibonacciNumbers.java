package Exercise_18._02;

public class FibonacciNumbers {
    public static void main(String[] args) { // github and y
        System.out.println(fibonacci(8));
    }

    public static int fibonacci(int n){
        if (n <= 1)
            return n;

        return fibonacci( n -1) + fibonacci( n -2 );
    }
}
