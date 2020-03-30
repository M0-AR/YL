package Exercise_06._21;

import java.util.Scanner;

public class PhoneKeypads {
    public static void main(String[] args) {// Github
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a phone number as a string.
        System.out.print( "Enter a string: " );
        String s = input.nextLine();

        //
        for (int i = 0; i < s.length() ; i++) {
            if (Character.isLetter( s.charAt( i ) )){
                System.out.print(getNumber( Character.toUpperCase( s.charAt( i ) ) ));
            }else {
                System.out.print(s.charAt( i ));
            }
        }
    }

    public static int getNumber(char uppercaseLetter){
        if (uppercaseLetter <= 'C' ){
            return 2;
        }else if (uppercaseLetter <= 'F'){
            return 3;
        }else if (uppercaseLetter <= 'I'){
            return 4;
        }else if (uppercaseLetter <= 'L'){
            return 5;
        }else if (uppercaseLetter <= 'O'){
            return 6;
        }else if (uppercaseLetter <= 'S'){
            return 7;
        }else if (uppercaseLetter <= 'V'){
            return 8;
        }else {
            return 9;
        }
    }
}
