package Exercise_07._21;
/*********************************************************************************
 * (Sum integers) Write a program that passes an unspecified number of integers   *
 * form command line and displays their total                                     *
 *********************************************************************************/


public class SumIntegers {
    public static void main(String[] args) {
        // Check command-line arguments
        if(args.length == 0){
            System.out.println(
                    "Usage: java Exercise07_21 string");
            System.exit( 1 );
        }

        // Compute sum
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            sum += Integer.parseInt( args[i] );
        }

        // Display sum
        System.out.println("The sum of integer " +
                                "numbers is: " + sum);

    }
}
