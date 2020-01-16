package Exercise_04._22;

import java.util.Scanner;

public class CheckSubstring {
    public static void main(String[] args) {// g and y
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter string1 and string2
        System.out.print("Enter string s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter string s2: ");
        String s2 = scanner.nextLine();

        if (s1.indexOf( s2 ) > -1){
            System.out.println(s2 + " is a substring of " + s1);
        }else {
            System.out.println(s2 + " is not a substring of " + s1);
        }



    }
}
