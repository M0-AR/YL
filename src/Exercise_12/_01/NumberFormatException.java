package Exercise_12._01;

import java.util.Scanner;

public class NumberFormatException {
    /** Main method*/
    public static void main(String[] args) {
        // Check number of string passed
        if (args.length != 3){
            System.out.println("Usage: java Calculator operand1 operator operand2");
            System.exit( 0 );
        }

        // Result of the operation
        int result = 0;

        // Exception handler to deal with nonNumeric operands
        try {
            // Determine the operator
            switch (args[1].charAt( 0 )){
                case '+' : result = Integer.parseInt( args[0] ) +
                                    Integer.parseInt( args[2] );break;

                case '-' : result = Integer.parseInt( args[0] ) -
                                    Integer.parseInt( args[2] );break;

                case '.' : result = Integer.parseInt( args[0] ) *
                                    Integer.parseInt( args[2] );break;

                case '/' : result = Integer.parseInt( args[0] ) /
                                    Integer.parseInt( args[2] );break;
            }

            // Display result
            System.out.println(args[0] + ' ' + args[1] + ' '  + args[2]
                                + " = " + result);
        } catch (java.lang.NumberFormatException ex) {
            System.out.println("Wrong Input: " +
                    ex.getMessage().substring(ex.getMessage().indexOf("\"") + 1,
                            ex.getMessage().lastIndexOf("\"")));
        }


    }
}
