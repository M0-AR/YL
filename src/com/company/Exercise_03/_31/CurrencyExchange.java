package com.company.Exercise_03._31;

import java.util.Scanner;


public class CurrencyExchange {// github and .43 not .42
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );

        // Prompt the user to enter the next following
        System.out.print("Enter the exchange rate from dollars to RMB: ");
        double rmb = scan.nextDouble();
//        double rmb = 6.81;
        System.out.print("Enter 0 to convert dollars to RMB and 1 vice versa: ");
        int exchange = scan.nextInt();

        switch (exchange){
            case 0: {
                System.out.print("Enter the dollar amount: ");
                int amount = scan.nextInt();
                System.out.println(amount + " is " + (amount * rmb) + " yuan");
            }break;
            case 1:{
                System.out.print("Enter the RMB amount: ");
                int amount = scan.nextInt();
                System.out.println(amount + " is " + (int)(amount *  100.0 / rmb )/100.0 + " yuan");
            }break;
        }
    }
}
