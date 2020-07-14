package Exercise_08._09;

import java.util.Arrays;
import java.util.Scanner;

public class PlayTic_Tac_ToeGame { // todo check row column cross
    public static void main(String[] args) {

        // Creat an array 3x3 to store X or O
        String[][] xOrO = new String[3][3];

        for (int i = 0; i < xOrO.length; i++) {
            Arrays.fill( xOrO[i], "   " );
        }

        // Display string array and get input from the user
        getInput(xOrO);

    }


    public static void getInput(String[][] strings) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        StringBuilder player = new StringBuilder(); // Store the winner
        String xOrOPlayer = "O";
        while (!check(strings, player)) {
            // Switch player
            xOrOPlayer = ((xOrOPlayer.equals( "O" ))? "X": "O");

            // Display grid
            displayGrid(strings);

            // Prompt the user to enter a number
            System.out.print( "Enter a row (0, 1, or 2) for player " + xOrOPlayer +": ");
            int row = input.nextInt();
            System.out.print( "Enter a column (0, 1, or 2) for player " + xOrOPlayer +": ");
            int column = input.nextInt();

            strings[row][column] = " " + xOrOPlayer + " ";
        }

        // Display grid
        displayGrid(strings);
        System.out.println(player + " player won");
    }

    /** Check if any player has three match */
    public static boolean check(String[][] strings, StringBuilder player) {
        int count = 1; // Count three mean  matching so one player will win
        // Check row
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length -1; j++) {
                if (strings[i][j].equals( "   " ))
                    break;

                if (!strings[i][j].equals( strings[i][j + 1] )) {
                    break;
                }
                count++;
                player.replace( 0, player.length(), strings[i][j] );
            }

            if (count == 3)
                return true;
        }

        // Check column


        // Check cross

        return false;
    }

    public static void displayGrid(String[][] strings) {
        // Line
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            for (int j = 0; j < strings[i].length; j++) {
                System.out.print("|" + strings[i][j]);
            }
            System.out.println("|");


        }
        System.out.println("-------------");
    }
}
