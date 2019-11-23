package com.company.op03.Exercise_03;

import java.util.Scanner;

public class e326UseAndOrOp {// done but need github
    public static void main(String[] args) {
        // Create Scanner object
        Scanner input = new Scanner( System.in );

        //1
        int a, b, c;

        // Prompt the user to Enter an integer
        System.out.print("Enter an integer: ");
        c = input.nextInt();
//        a = 5;
//        b = 6;

        //Calculate if c divisible by 5 and or 6
        // Display the result
        System.out.println("Is "+c+" divisible by 5 and 6? " + ((10 % 5 == 0)  && (10 % 6 == 0)));
        System.out.println("Is "+c+" divisible by 5 or 6? " + ((10 % 5 == 0)  || (10 % 6 == 0)));
        System.out.println("Is "+c+" divisible by 5 or 6, but not both? " + ((10 % 5 == 0)  ^ (10 % 6 == 0)));


    }
}
