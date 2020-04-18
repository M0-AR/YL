package Exercise_12._01;

import java.util.Scanner;

public class NumberFormatException {
    /** Main method*/
    public static void main(String[] args) { // github
        // Create a scanner
        Scanner input = new Scanner( System.in );

        while (true){
            try {
                int n1 = input.nextInt();
                char ch = input.next().charAt( 0 );
                int n2 = input.nextInt();
            } catch (Exception e) {
                System.out.println( "Wrong Input: " + e );
                break;
            }


        }
    }
}
