package com.company;
import java.util.Scanner;

public class e302addThreeNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //1
        int number1, number2, number3, answer;

        //2
        number1 = (int) (System.currentTimeMillis() % 10);
        number2 = (int) (System.currentTimeMillis() / 10 % 10); // or * 7 % 10);
        number3 = (int) (System.currentTimeMillis() / 100 % 10);// or * 3 % 10);

        //3

        //4
        // Sum of tow numbers
        System.out.print(number1 + " + " + number2 + "?  " );
        answer  = input.nextInt();
        System.out.println(number1 + " + " + number2 + " = " + answer + " is "
                                                + (number1 + number2 == answer));
        // Sum of three numbers
        System.out.print(number1 + " + " + number2 + " + " + number3 + "?  " );
        answer  = input.nextInt();
        System.out.println(number1 + " + " + number2 + " + " + number3 + " = " + answer + " is "
                + (number1 + number2 + number3 == answer));

    }
}
