package Exercise_08._20;

import Exercise_08._19.PatternRecognition;

import java.util.Scanner;
// Plan
// Check if column is full and all grids as well(draw)
// check if win

public class ConnectFour { // -1 and drawFlag

    private static final int ROW_NUMBER = 6;
    private static final int COLUMN_NUMBER = 7;
    private static int[][] connectFourGrids = new int[ROW_NUMBER][COLUMN_NUMBER];
    private static boolean[] fullColumn = new boolean[COLUMN_NUMBER];

    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        connectFourGrids = new int[][]{
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {0, 5, 2, 9, 6, 2, 9},
                {6, 9, 6, 6, 2, 9, 1},
                {1, 5, 9, 1, 4, 9, 7},
                {3, 3, 3, 3, 4, 0, 9}  // 3, 3, 3, 3
        };

        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                connectFourGrids[i][j] = -1;
            }
        }

        boolean winFlag = false;
        boolean drawFlag = false;
        String diskColor = "red";
        int columnInput = 0;




        do {
            drawConnectFourGame();

            // Prompt user
            System.out.print("Drop a " + diskColor + " disk at column (0-6): ");
            columnInput = input.nextInt();


            fullColumn[columnInput] = checkForEmptySpaceAndAssignValue(columnInput,
                                        Character.toUpperCase(diskColor.charAt( 0 )));

            if (fullColumn[columnInput])
                System.out.println("Full Column find solution + drawFlag");

            // Check for win
           winFlag =  PatternRecognition.isConsecutiveFour(  connectFourGrids );

            // Change player
            diskColor = diskColor.equals( "red" ) ? "yellow" : "red";

        }while (!winFlag || drawFlag);
        connectFourGrids[5][0] = 'R';

    }

    private static boolean checkForEmptySpaceAndAssignValue(int columnInput, char diskColor) {
        for (int i = ROW_NUMBER - 1; i >= 0; i--) {
            if (connectFourGrids[i][columnInput] == -1) {
                connectFourGrids[i][columnInput] = diskColor;
               return false;
            }
        }
        return true;
    }

    public static void drawConnectFourGame() {
        int userInput = 0;
        int columnNumber = 7;
        int rowNumber = 6;
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.print("|" + (char) connectFourGrids[i][j]);
            }
            System.out.println();
        }
    }
}
