package com.company.Exercise_03._24;
/*
(Game: pick a card) Write a program that simulates picking a card from a deck
of 52 cards. Your program should display the rank (Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10,
Jack, Queen, King) and suit (Clubs, Diamonds, Hearts, Spades) of the card.
Here is a sample run of the program:
*/
public class e324pickACard {
    public static void main(String[] args){
        final int NUMBER_OF_CARDS = 52;
        // Pick a card
        int cardRandom = (int)(Math.random() * NUMBER_OF_CARDS);

        System.out.print("The card you picked is ");
        if(cardRandom % 13 == 0)
            System.out.print("Ace of ");
        else if (cardRandom % 13 == 10)
            System.out.print("Jac of ");
        else if (cardRandom % 13 == 11)
            System.out.print("Queen of ");
        else if (cardRandom % 13 == 12)
            System.out.print("King of ");
        else
            System.out.println((cardRandom % 13) + " of");


        if (cardRandom / 13 == 0)
            System.out.println("Clubs");
        else if (cardRandom / 13 == 1)
            System.out.println("Diamonds");
        else if (cardRandom / 13 == 2)
            System.out.println("Hearts");
        else if (cardRandom / 13 == 3)
            System.out.println("Spades");
    }
}