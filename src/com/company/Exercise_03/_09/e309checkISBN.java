package com.company.Exercise_03._09;

import java.util.Scanner;

public class e309checkISBN { //   to make the user just input just 9 digit number

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //1
        boolean isInt = false;
        double d, d1,testInput = 0, sum;
        String reader;

        //2
        // 013031997, 013601267
        do {
            System.out.println("Enter the first 9 digits of an ISBN as integer: ");

            if (input.hasNextDouble() ){
                testInput = input.nextDouble();
                // to make the user just input 9 digit number
                // https://www.baeldung.com/java-number-of-digits-in-int
                int length = String.valueOf(testInput).length();
                System.out.println(length);
                isInt = false;

            }
            else {
                System.out.println("Please make sure that you input a valid number");
                isInt = true;
                input.next();
            }
        }while (isInt);
        sum = 0;

        //3
        // if d1 = 0 
        d1 = (int)((testInput % Math.pow(10, 9)) / Math.pow(10, 8));

        for (int i = 1; i<10; i++){
            d = (int)((testInput % Math.pow(10, (10 - i)) / Math.pow(10, (9-i))));
            sum += d * i;
        }

        sum %= 11;

        //4
        if (d1 == 0 &&  sum < 10)
            System.out.println("The ISBN-10 number is "+(int)(d1)+""+(int)(testInput) +""+ (int)(sum));
        else if (sum >= 10 && d1 == 0)
            System.out.println("The ISBN-10 number is "+(int)(d1)+""+(int)(testInput) +""+ "X");
        else if (sum >= 10)
            System.out.println("The ISBN-10 number is "+(int)(testInput) +""+ "X");
            // d1 not 0
        else
            System.out.println("The ISBN-10 number is "+(int)(testInput) +""+ (int)(sum));





    }
}