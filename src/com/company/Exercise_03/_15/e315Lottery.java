package com.company.Exercise_03._15;

import java.util.Scanner;

public class e315Lottery { // done
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);

        //1
        int lotteryRandom, lotteryRandom1, lotteryRandom2, lotteryRandom3;
        int guessNumber, guessDigit1, guessDigit2, guessDigit3;

        //2
        lotteryRandom = (int)(Math.random() * 900) + 100;
        System.out.println("Enter your lottery pick (three digits):");
        guessNumber = input.nextInt();

        // To separate the lotteryRandom number to each
        lotteryRandom1 = lotteryRandom / 100;
        lotteryRandom2 = (lotteryRandom % 100) / 10;
        lotteryRandom3 = lotteryRandom % 10;

        // To separate the gussNumber to each
        guessDigit1 = guessNumber / 100;
        guessDigit2 = (guessNumber % 100) / 10;
        guessDigit3 = guessNumber % 10;

        //3
        System.out.println("The lottery number is " + lotteryRandom);
        if (lotteryRandom == guessNumber) {
            System.out.println("Exact match: you win $10,000");
        }else if (lotteryRandom1 == guessDigit3
                    && lotteryRandom2 == guessDigit2
                        && lotteryRandom3 == guessDigit1) {
            System.out.println("Match all digits: you win $3,000 ");
        }else if(lotteryRandom1 == guessDigit1 || lotteryRandom1 == guessDigit2 || lotteryRandom1 == guessDigit3
              || lotteryRandom2 == guessDigit1 || lotteryRandom2 == guessDigit2 || lotteryRandom2 == guessDigit3
              || lotteryRandom3 == guessDigit1 || lotteryRandom3 == guessDigit2 || lotteryRandom3 == guessDigit3  ){
            System.out.println("Match one digit: you win $1,000 ");
        }else {
            System.out.println("Sorry, no match");
        }


    }
}
