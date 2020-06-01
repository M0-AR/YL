package Exercise_07._35;
/*********************************************************************************
 * (Game: hangman) Write a hangman game that randomly generates a word and        *
 * prompts the user to guess one letter at a time, as shown in the sample run.    *
 * Each letter in the word is displayed as an asterisk. When the user makes a     *
 * correct guess, the actual letter is then displayed. When the user finishes a   *
 * word, display the number of misses and ask the user whether to continue to     *
 * play with another word. Declare an array to store words, as follows:           *
 *********************************************************************************/
import java.util.Arrays;
import java.util.Scanner;

public class Hangman { // Github
    private static int count = 0; // To count wrong answers
    private static int correctAnswer = 0; // To count the correct answer
    private static boolean play = true; // To pick one random word for one play round
    private static boolean end = true; // Boolean if the user want to play again

    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Some rules for the game
        String word = null; // To have a random word from words' array
        boolean[] bl = new boolean[0]; // To help the user not repeat the input
        char[] asterisk = new char[0]; // To display asterisk according to the word length
        

        do {
            // Get a random word
            if (play){
                word = randomWord();
                bl = new boolean[word.length()];
                asterisk = new char[word.length()];
                Arrays.fill( asterisk, '*' );
                play = false;
            }

            // Prompt the user to enter a guess
            char userInput = userEnter( asterisk, input );

            // Check if the user's input match any letter in the word
            checkUserAnswer(bl, asterisk, word, userInput);

            // To display result and ask the user to play again or not
            if (correctAnswer == word.length()){
                isEndOfGame( word, input );
            }
        }while (end);
    }



    /** Get random word */
    public static String randomWord() {
        // Random words to make the user guess them
        String[] words = {"that", "write", "md", "good"};

        String word = words[(int)(Math.random() * words.length)];

        return word;
    }

    /** Prompt the user to enter a guess */
    public static char userEnter(char[] asterisk, Scanner input) {
        // Prompt the user to enter a guess
        System.out.print( "(Guess) Enter a letter in word " );

        // Print asterisk according to the word length
        for (int i = 0; i < asterisk.length; i++)
            System.out.print(asterisk[i]);

        System.out.print( " > " );

        char userInput = input.nextLine().charAt( 0 );

        return userInput;
    }

    /** To check user answer and display user answer if correct or not */
    public static void checkUserAnswer(boolean[] bl, char[] asterisk, String word, char userInput) {
        boolean ch = true; // If the userInput don't match any letter in word
        for (int i = 0; i < word.length(); i++) {
            if (userInput == word.charAt( i )) {
                if (!bl[i]){
                    bl[i] = true;
                    ch = false;
                    asterisk[i] = userInput;
                    correctAnswer++;
                }
                else{
                    System.out.println( userInput + " is already in the word" );
                    break;
                }

            }
            else if (i == word.length()-1 && ch){
                System.out.println(userInput + " is not in the word");
                count++;
            }
        }
    }

    /** To display result and ask the user to play again or not */
    public static void isEndOfGame(String word, Scanner input) {
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

}
