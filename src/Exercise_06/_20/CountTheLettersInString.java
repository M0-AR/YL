package Exercise_06._20;

import java.util.Scanner;

public class CountTheLettersInString { // Github and yl
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );


        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();


        int n = getNumber( s );
        System.out.println("The number of letter" + ((n > 1) ?"s":"") + " in the string is " + n);
    }

    /** Count the number of String. */
    public static int getNumber(String s){
        int counter = 0; // Counter for letters
        for (int i = 0; i <s.length() ; i++) {
            if (Character.isLetter( s.charAt( i ) )){
                counter++;
            }
        }
        return counter;
    }
}
