package Exercise_18._15;

import java.util.Scanner;

public class OccurrencesOfSpecifiedCharacterInString {// github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter a string: " );
        String s = input.nextLine();
        System.out.print( "Enter a character: " );
        char ch = input.nextLine().charAt( 0 );
        int times = count( s, ch );
        System.out.println( ch + " appears " + times +
                (times > 1 ? " times " : " time ") + "in " + s );
    }

    public static int count(String str, char a){
        return count(str, a ,str.length());
    }

    public static int count(String str, char a,int high){
        int number = 0;
        if (high > 0){
            number = count( str.substring( 1 ), a, high-1 ) + ((str.charAt( 0 ) == a) ? 1:0);
        }
        return number;
    }
}
