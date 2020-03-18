package Exercise_18._12;
/*********************************************************************************
 * (Print the characters in a string reversely) Rewrite Programming Exercise 18.9 *
 * using a helper method to pass the substring high index to the method. The      *
 * helper method header is:                                                       *
 *                                                                                *
 * public static void reverseDisplay(String value, int high)                      *
 *********************************************************************************/
import java.util.Scanner;

public class PrintCharactersInStringReversely {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = input.nextLine();
        System.out.print("The reversal of " + s + " is ");
        reverseDisplay(s);
    }

    public static void reverseDisplay(String value){
        reverseDisplay( value, value.length() -1 );
    }

    public static void reverseDisplay(String value, int high){
        if (high >= 0){
            System.out.print(value.charAt( high  ));
            reverseDisplay( value.substring( 0, high-1 ) );
        }

    }
}
