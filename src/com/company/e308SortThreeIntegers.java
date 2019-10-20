package com.company;

import java.util.Scanner;

public class e308SortThreeIntegers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the numbers: ");
        //1
        int n1, n2, n3, temp;

        //2
          n1=3; n2=10; n3=0;
//        n1=input.nextInt();
//        n2=input.nextInt();
//        n3=input.nextInt();

        //3
        if (n1 > n2){
            temp = n1;
            n1 = n2;
            n2 = temp;
        }

        if (n2 > n3){
            temp = n2;
            n2 = n3;
            n3 = temp;
        }

        if (n1 > n2){
            temp = n1;
            n1 = n2;
            n2 = temp;
        }


        //4
        System.out.println("The sorted numbers are: "
                        + n1 + ", " + n2 + ", " + n3);
    }
}