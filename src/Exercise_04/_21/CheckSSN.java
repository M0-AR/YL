package Exercise_04._21;

import java.util.Scanner;

public class CheckSSN {
    public static void main(String[] args) {// how about input letter
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter
        System.out.print("Enter a SSN: ");
        String ssn = scanner.nextLine();

        // Display result
        if (ssn.length() == 11 && ssn.charAt( 3 ) == '-' && ssn.charAt( 6 ) == '-'){
            System.out.println(ssn + " is a valid social security number");
        }else {
            System.out.println(ssn + " is an invalid social security number");
        }
    }
}
