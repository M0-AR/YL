package Exercise_08._09;
/*********************************************************************************
 * (Game: play a tic-tac-toe game) In a game of tic-tac-toe, two players take     *
 * turns marking an available cell in a 3 * 3 grid with their respective tokens   *
 * (either X or O). When one player has placed three tokens in a horizontal,      *
 * vertical, or diagonal row on the grid, the game is over and that player has    *
 * won. A draw (no winner) occurs when all the cells on the grid have been filled *
 * with tokens and neither player has achieved a win. Create a program for        *
 * playing tic-tac-toe. The program prompts two players to enter an X token       *
 * and O token alternately. Whenever a token is entered, the program redisplays   *
 * the board on the console and determines the status of the game (win, draw, or  *
 * continue).                                                                     *
 *********************************************************************************/
import java.util.Arrays;
import java.util.Scanner;

public class PlayTic_Tac_ToeGame {
    public static void main(String[] args) {
        // Creat an array 3x3 to store X or O
        String[][] xOrO = new String[3][3];

        for (int i = 0; i < xOrO.length; i++) {
            Arrays.fill( xOrO[i], "   " );
        }

        // Display string array and get input from the user
        startGame(xOrO);

    }


    /** Get input from the user and display result */
    public static void startGame(String[][] strings) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Game status
        int status = 2;

        StringBuilder player = new StringBuilder("O"); // Store the winner
        String xOrOPlayer = "O";

        while (status == 2) {
            // Switch player
            xOrOPlayer = ((xOrOPlayer.equals( "O" ))? "X": "O");
            player.deleteCharAt( 0 );
            player.append( xOrOPlayer );

            // Display grid
            displayGrid(strings);

            // Prompt the user to enter a number
            System.out.print( "Enter a row (0, 1, or 2) for player " + xOrOPlayer +": ");
            int row = input.nextInt();
            System.out.print( "Enter a column (0, 1, or 2) for player " + xOrOPlayer +": ");
            int column = input.nextInt();


            strings[row][column] = " " + xOrOPlayer + " ";

            status = getStatus( strings, player );
        }

        // Display grid
        displayGrid(strings);
        System.out.println(status == 0 ? player + " player won" : "is drew");


    }


    /** Get the status of the player */
    public static int getStatus(String[][] strings, StringBuilder player) {
        if (isWin(strings, player))
            return 0; // Win
        else if (isDraw(strings))
            return 1; // Draw
        else
            return 2; // Continue
    }



    /** Check if the player win */
    public static boolean isWin(String[][] strings, StringBuilder player) {
        return check( strings, player );
    }


    /** Check if draw: no one win */
    public static boolean isDraw(String[][] strings) {
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                if (strings[i][j].equals( "   " ))
                    return false;
            }
        }

        return true;
    }

    /** Check if any player has three match */
    public static boolean check(String[][] strings, StringBuilder player) {
        int count = 0; // Count three mean  matching so one player will win
        // Check row
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {

                if (!strings[i][j].contains( player )) {
                    break;
                }
                count++;
            }

            if (count == 3)
                return true;
            count = 0;
        }

        // Check column
        count = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {

                if (!strings[j][i].contains( player )) {
                    break;
                }
                count++;
            }

            if (count == 3)
                return true;
            count = 0;
        }

        // Check Diagonal
        if (strings[0][0].contains( player ) &&
                strings[1][1].contains( player ) &&
                    strings[2][2].contains( player ))
            return true;

        if (strings[0][2].contains( player ) &&
                strings[1][1].contains( player ) &&
                    strings[2][0].contains( player ))
            return true;


        return false;
    }

    /** Display game design to the user */
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
