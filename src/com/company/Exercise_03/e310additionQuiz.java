package com.company.Exercise_03;

import java.util.Scanner;

public class e310additionQuiz { // done but -->    // Why in tryAgain I have to use exit to times

    private static int n1, random1, random2, temp;
    private static Scanner input;
    private static String exit;
    public static void main(String[] args) {

        // playGame
        playGame();

    }

    public static void playGame(){
         input = new Scanner(System.in);

        do {
            random1 = (int)(Math.random() * 100);
            random2 = (int)(Math.random() * 100);
            // sort two numbers
            if (random1 < random2){
                temp = random1;
                random1 = random2;
                random2 = temp;
            }

            System.out.println(random1 + " - " + random2 + " =  ?" );
            if (input.hasNextInt()){
                n1 = input.nextInt();
                System.out.println(n1 == (random1 - random2));
            }else {
                System.out.println("Input a valid number: ");
                input.next();
            }
        }while ((n1 == (random1 - random2)));

        tryAgain();
    }

    public static void tryAgain(){

        System.out.println("Do you want to try again (y or n): ");
        exit = input.nextLine();
        exit = input.nextLine();




        if (exit.equals("y")){
            playGame();
        }else {
            System.out.println("Have a nice day :)");
        }
    }
}