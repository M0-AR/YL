package com.company.Exercise_03;

import java.util.Scanner;

public class e312palindromeInteger { // done
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);

        //1
        int palindromeInteger;
        String str;
        boolean isThreeDigitNumbers;
        //2
        palindromeInteger = 0;
        str = "";


        do {
            System.out.println("Enter a three-digit integer: ");
            if (input.hasNextInt()){
                palindromeInteger = input.nextInt();
                str = Integer.toString(palindromeInteger);
                if (str.length() == 3)
                    isThreeDigitNumbers = true;
                else
                    isThreeDigitNumbers = false;
            }else {
                System.out.println("Please, enter a valid number: ");
                isThreeDigitNumbers = false;
                input.next();
            }
        }while (!(isThreeDigitNumbers));

        //3

        if (str.charAt(0) == str.charAt(2)){
            System.out.println(palindromeInteger + " is a palindrome");
        }else {
            System.out.println(palindromeInteger + " is not a palindrome");
        }




    }
}
