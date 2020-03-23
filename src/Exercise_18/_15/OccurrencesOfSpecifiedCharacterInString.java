package Exercise_18._15;
/**********************************************************************************
 * (Occurrences of a specified character in a string) Rewrite Programming Exercise *
 * 18.10 using a helper method to pass the substring high index to the method.     *
 * The helper method header is:                                                    *
 *                                                                                 *
 * public static int count(String str, char a, int high)                           *
 **********************************************************************************/
import java.util.Scanner;

public class OccurrencesOfSpecifiedCharacterInString {
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
        // Base case
        if (high <= 0){
            return 0;
        }
        return count( str.substring( 1 ), a, high-1 ) +
                                    ((str.charAt( 0 ) == a) ? 1:0);
    }
}
