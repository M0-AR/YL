package Exercise_08._20;

import Exercise_08._19.PatternRecognition;

import java.util.Scanner;
// Plan
// Check if column is full and all grids as well(draw)
// check if win

public class ConnectFour {

    private static final int ROW_NUMBER = 6;
    private static final int COLUMN_NUMBER = 7;
    private static char[][] connectFourGrids = new char[ROW_NUMBER][COLUMN_NUMBER];
    private static boolean[] fullColumn = new boolean[COLUMN_NUMBER];

    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );


        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                connectFourGrids[i][j] = ' ';
            }
        }

        boolean winFlag = false;
        boolean drawFlag = false;
        String diskColor = "red";
        int columnInput = 0;

        do {
            // Prompt user
            System.out.print("Drop a " + diskColor + " disk at column (0-6): ");
            columnInput = input.nextInt();


            fullColumn[columnInput] = checkForEmptySpaceAndAssignValue(columnInput,
                                        Character.toUpperCase(diskColor.charAt( 0 )));



        }while (winFlag || drawFlag);
        connectFourGrids[5][0] = 'R';
        drawConnectFourGame();

    }

    private static boolean checkForEmptySpaceAndAssignValue(int columnInput, char diskColor) {
        for (int i = ROW_NUMBER - 1; i >= 0; i--) {
            if (connectFourGrids[i][columnInput] == ' ') {
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
                System.out.print("|" + connectFourGrids[i][j]);
            }
            System.out.println();
        }
    }
}
