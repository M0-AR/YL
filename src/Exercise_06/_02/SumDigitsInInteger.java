package Exercise_06._02;

public class SumDigitsInInteger { // github and y
    public static void main(String[] args) {
        System.out.println(sumDigits( 234 ));
    }

    public static int sumDigits(long n){
        int number = 0;
        while (n != 0){
            number += n %  10;
            n /= 10;
        }
        return number;
    }
}
