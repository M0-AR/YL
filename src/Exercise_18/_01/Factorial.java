package Exercise_18._01;

public class Factorial { // github
    public static void main(String[] args) {
        System.out.println(factorial(100));
    }

    public static double factorial(int n){
        if (n == 1)
            return 1;
        return factorial( n-1 ) * n;
    }
}
