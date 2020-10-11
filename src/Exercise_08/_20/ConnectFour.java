package Exercise_08._20;
/**
 * (Game: connect four) Connect four is a two-player board game in which the
 * players alternately drop colored disks into a seven-column, six-row vertically
 * suspended grid, as shown below.
 *
 * The objective of the game is to connect four same-colored disks in a row, a column, or a diagonal before your opponent can do likewise. The program prompts
 * two players to drop a red or yellow disk alternately. In the preceding figure, the
 * red disk is shown in a dark color and the yellow in a light color. Whenever a disk
 * is dropped, the program redisplays the board on the console and determines the
 * status of the game (win, draw, or continue)
 */

import Exercise_08._19.PatternRecognition;
import java.util.Scanner;

public class ConnectFour {

    private static final int ROW_NUMBER = 6;
    private static final int COLUMN_NUMBER = 7;
    private static int[][] connectFourGrids = new int[ROW_NUMBER][COLUMN_NUMBER];
    private static boolean[] fullColumn = new boolean[COLUMN_NUMBER];

    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Random numbers
        // We need to have different values at the beginning of game,
        // Because when checking of four connected pattern.
        // Why this, because I reusing the previous assigment for this checking
        connectFourGrids = new int[][]{
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {0, 5, 2, 9, 6, 2, 9},
                {6, 9, 6, 6, 2, 9, 1},
                {1, 5, 9, 1, 4, 9, 7},
                {3, 2, 0, 3, 4, 0, 9}
        };


        boolean winFlag = false;
        boolean drawFlag = false;
        boolean changePlayer = true;
        String diskColor = "red";

        do {
            displayBoard();
            changePlayer = true;

            // Prompt user
            System.out.print("Drop a " + diskColor + " disk at column (0-6): ");
            int columnInput = input.nextInt();


            fullColumn[columnInput] = checkForEmptySpaceAndPlaceADisk(columnInput,
                                        Character.toUpperCase(diskColor.charAt( 0 )));

            if (fullColumn[columnInput]) {
                if (checkIfAllColumnsIsFull(fullColumn)) {
                    drawFlag = true;
                }

                System.out.println("Full Column try another column");
                changePlayer = false;
            }


            // Check for win
           winFlag =  PatternRecognition.isConsecutiveFour(  connectFourGrids );

           if (changePlayer && !winFlag) // Change player
                diskColor = diskColor.equals( "red" ) ? "yellow" : "red";

        }while (!winFlag && !drawFlag);


        if (drawFlag) {
            System.out.println("It's draw");
        } else {
            displayBoard();
            System.out.println("The winner is " + diskColor);
        }


    }


    private static boolean checkForEmptySpaceAndPlaceADisk(int columnInput, char diskColor) {
        for (int i = ROW_NUMBER - 1; i >= 0; i--) {
            if (connectFourGrids[i][columnInput] != 'R' && connectFourGrids[i][columnInput] != 'Y') {
                connectFourGrids[i][columnInput] = diskColor;
               return false;
            }
        }
        return true;
    }

    private static boolean checkIfAllColumnsIsFull(boolean[] fullColumn) {
        for (int i = 0; i < fullColumn.length; i++) {
            if (!fullColumn[i])
                return false;
        }
        return true;
    }

    public static void displayBoard() {
        int columnNumber = 7;
        int rowNumber = 6;
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.print("|" + (((char) connectFourGrids[i][j] == 'R' ||
                                         (char) connectFourGrids[i][j] == 'Y') ?
                                                (char) connectFourGrids[i][j] : " "));
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
}
