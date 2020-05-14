package Exercise_07._22;
/*********************************************************************************
 * (Find the number of uppercase letters in a string) Write a program that        *
 * passes a string to the command line and displays the number of uppercase       *
 * letters in the string.                                                          *
 *********************************************************************************/


public class FindTheNumbersOfUppercaseLetterInString {
    /** Main method */
    public static void main(String[] args) {
        // Check command-line arguments
        if(args.length != 1){
            System.out.println(
                    "Usage: java Exercise07_22 string");
            System.exit( 1 );
        }

        String str = args[0];

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
