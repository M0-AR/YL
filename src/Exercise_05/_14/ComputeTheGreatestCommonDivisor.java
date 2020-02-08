package Exercise_05._14;

import java.util.Scanner;

public class ComputeTheGreatestCommonDivisor { // g and y
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // 1. Prompt a user to enter two integers numbers
        System.out.print("Enter two integers numbers: ");
        int n1 = input.nextInt();
        int n2 = input.nextInt();

        // swap
        if (n1 < n2){
            int temp = n1; n1 = n2; n2 = temp;
        }

        int gcd;

        while (n1 % n2 != 0){ // n1 = 12 and n2 = 9
            int temp = n1;
            n1 = n2; // n1 = 9
            n2 = temp % n2; // n2 = 3


        }

        gcd = n2;
        System.out.println("The GCD is: "+ gcd);


    }
}
