package Exercise_06._04;

import java.util.Scanner;

public class DisplayIntegerReversed {// github and y
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter an integer: ");
        int number = input.nextInt();
        reverse( number );
    }

    public static void reverse(int number){
        while (number != 0){
            int reminder = number % 10 ;
            System.out.print( reminder );
            number /= 10;
        }
    }
}
