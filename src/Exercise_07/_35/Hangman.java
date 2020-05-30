package Exercise_07._35;

import java.util.Arrays;
import java.util.Scanner;

public class Hangman { // Github
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Random words to make the user guess them
        String[] words = {"that", "write", "md", "good"};


        // Some rules for the game
        boolean play = true; // To pick one random word for one play round
        boolean end = true; // Boolean if the user want to play again
        String word = null; // To have a random word from words' array
        int count = 0; // To count wrong answers
        int correctAnswer = 0; // To count the correct answer
        boolean[] bl = new boolean[0]; // To help the user not repeat the input
        char[] asterisk = new char[0]; // To display asterisk according to the word length
        

        do {
            // Get a random word
            if (play){
                word = words[(int)(Math.random() * words.length)];
                bl = new boolean[word.length()];
                asterisk = new char[word.length()];
                Arrays.fill( asterisk, '*' );
                play = false;
            }

            // Prompt the user to enter a guess
            System.out.print( "(Guess) Enter a letter in word " );
            // Print asterisk according to the word length
            for (int i = 0; i < word.length(); i++)
                System.out.print(asterisk[i]);
            System.out.print( " > " );
            char userInput = input.nextLine().charAt( 0 );

            // Check if the user's input match any letter in the word
            boolean ch = true; // If the userInput don't match any letter in word
            for (int i = 0; i < word.length(); i++) {
                if (userInput == word.charAt( i )) {
                    if (!bl[i]){
                        bl[i] = true;
                        ch = false;
                        asterisk[i] = userInput;
                        correctAnswer++;
                    }
                    else
                        System.out.println( userInput + " is already in the word" );
                }
                else if (i == word.length()-1 && ch){
                    System.out.println(userInput + " is not in the word");
                    count++;
                }
            }

            // To display result and ask the user to play again or not
            if (correctAnswer == word.length()){
                System.out.println("The word is " + word + ". You missed " + 
                                        count + ((count > 1)?" times":" time"));
                System.out.print("Do you want to guess another word? " +
                                                        "Enter y or n> ");
                char yOrN= input.nextLine().charAt( 0 );
                if (yOrN == 'n')
                    end = false;
                else{
                    play = true;
                    count = 0;
                    correctAnswer = 0;
                }

            }


        }while (end);
    }


}
