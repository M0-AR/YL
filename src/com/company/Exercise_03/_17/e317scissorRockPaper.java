package com.company.Exercise_03._17;
/*
(Game: scissor, rock, paper) Write a program that plays the popular scissor-rockpaper
game. (A scissor can cut a paper, a rock can knock a scissor, and a paper can
wrap a rock.) The program randomly generates a number 0, 1, or 2 representing
scissor, rock, and paper. The program prompts the user to enter a number 0, 1, or
2 and displays a message indicating whether the user or the computer wins, loses,
or draws.
*/
import java.util.Random;
import java.util.Scanner;
// see youtube video and search for automatik losning
public class e317scissorRockPaper { // done by a smart method
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter 0, 1, or 2
        System.out.println("scissor (0), rock (1), paper (2):");
        int user = input.nextInt();

        // Generate a random integer 0, 1 or 2
        int random = (int)(Math.random() * 3);
        System.out.println(random);



        System.out.print("The computer is ");
        switch (random)
        {
            case 0: System.out.print("scissor. "); break;
            case 1: System.out.print("rock. "); break;
            case 2: System.out.print("paper. ");
        }

        System.out.print("You are ");
        switch (user)
        {
            case 0: System.out.print("scissor. "); break;
            case 1: System.out.print("rock. "); break;
            case 2: System.out.print("paper. ");
        }

        if (random == user)
            System.out.println("It is a draw");
        else
            {
                boolean win =  (user == 0 && random == 2)
                            || (user == 1 && random == 0)
                            || (user == 2 && random == 1);
                if (win)
                    System.out.println("You won");
                else
                    System.out.println("You loses");
            }


    }

}

//if (random == 0 && user ==0){
//        System.out.println("The computer is scissor. You are scissor. It is a draw" );
//        } else if (random == 0 && user ==1){
//        System.out.println("The computer is scissor. You are rock. You won" );
//        }else if(random == 0 && user == 2){
//        System.out.println("The computer is scissor. You are paper. You loses" );
//        }else if (random == 1 && user == 0){
//        System.out.println("The computer is rock. You are scissor. You loses" );
//        }else if (random == 1 && user == 1){
//        System.out.println("The computer is rock. You are rock. It is a draw" );
//        }else if (random == 1 && user == 2){
//        System.out.println("The computer is rock. You are paper. You won" );
//        } else if (random == 2 && user == 0){
//        System.out.println("The computer is paper. You are scissor. You won" );
//        }else if (random == 2 && user == 1){
//        System.out.println("The computer is paper. You are rock. You loses" );
//        }else if (random == 2 && user == 2){
//        System.out.println("The computer is paper. You are paper. It is a draw" );
//        }
