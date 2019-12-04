package com.company.Exercise_03._14;

import java.util.Scanner;

public class e314headsOrTails {// done but  // need try again
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        //1
        int random, guss;

        //2
        random = (int)(Math.random() * 2);
        System.out.println("Try to guss: Heads = 0 and Tails = 1, so (1 or 0)?");
        guss = input.nextInt();

        //3
        if (guss == random){
            System.out.println("Correct guss");
        }else if (random == 0){
            System.out.println("Sorry, it is a head");
        }
        else {
            System.out.println("Sorry, it is a tail");
        }

        System.out.println(random);

    }
}
