package Exercise_12._02;
/*********************************************************************************
 * (InputMismatchException) Write a program that prompts the user to read two     *
 * integers and displays their sum. Your program should prompt the user to read   *
 * the number again if the input is incorrect.                                    *
 *********************************************************************************/
import java.util.InputMismatchException;
import java.util.Scanner;

public class inputMismatchException  {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        boolean done = false;
        int n1 = 0, n2 = 0;

        do {
            try {
                // Prompt the user to enter to numbers
                System.out.print( "Enter to numbers: " );
                n1 = input.nextInt();
                n2 = input.nextInt();
                done = true;
            }catch (InputMismatchException e){
                e.printStackTrace();
                System.out.println("Input should be a number: ");
                input.nextLine(); // Discard input
            }

        }while (!done);

        int sum = n1 + n2;
        // Display result
        System.out.println(sum);

    }
}
