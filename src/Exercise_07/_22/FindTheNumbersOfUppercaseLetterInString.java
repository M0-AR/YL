package Exercise_07._22;

import java.util.Scanner;

public class FindTheNumbersOfUppercaseLetterInString {
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a string
        System.out.print( "Enter a string: " );
        String str = input.nextLine();


        System.out.println("Tne number of uppercase is: " +
                                    numberOfUppercase( str ));
    }

    /** Find the number of uppercase in String */
    public static int numberOfUppercase(String str){
        int sum = 0; // Sum the number of upper case in string
        for (int i = 0; i < str.length() ; i++) {
            if (String.valueOf( str.charAt( i ) ).matches(("[A-Z]") ) )
                sum++;
        }
        return sum;
    }
}
