package Exercise_18._11;

import java.util.Scanner;

public class SumDigitsUsingRecursion {// Github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter an integer: ");
        long l = input.nextLong();
        System.out.println(sumDigit(l));
    }

    public static int sumDigit(long n){
        if (n / 10 == 0)
            return (int) n;
        return (int) ((n%10) + sumDigit( n / 10 ));
    }
}
