package Exercise_03._31;
/*
(Financials: currency exchange) Write a program that prompts the user to enter
the exchange rate from currency in U.S. dollars to Chinese RMB. Prompt the user
to enter 0 to convert from U.S. dollars to Chinese RMB and 1 to convert from
Chinese RMB and U.S. dollars. Prompt the user to enter the amount in U.S. dollars
or Chinese RMB to convert it to Chinese RMB or U.S. dollars, respectively.
*/

import java.util.Scanner;


public class CurrencyExchange {
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
                double amount = scan.nextInt();
                System.out.println(amount + " is " + (int)(amount * rmb * 100)/100.0 + " yuan");
            }break;
            case 1:{
                System.out.print("Enter the RMB amount: ");
                double amount = scan.nextInt();
                System.out.println(amount + " is " + (int)(amount *  100.0 / rmb )/100.0 + " yuan");
            }break;
            default: System.out.println("Incorrect input");
        }
    }
}
