package Exercise_08._19;
/**
 (Pattern recognition: four consecutive equal numbers) Write the following
 method that tests whether a two-dimensional array has four consecutive numbers
 of the same value, either horizontally, vertically, or diagonally:

 public static boolean isConsecutiveFour(int[][] values)

 Write a test program that prompts the user to enter the number of rows and
 columns of a two-dimensional array then the values in the array, and displays true
 if the array contains four consecutive numbers with the same value. Otherwise,
 the program displays false. Here are some examples of the true cases:

 0 1 0 3 1 6 1
 0 1 6 8 6 0 1
 5 6 2 1 8 2 9            5                   6           9
 6 5 6 1 1 9 1            5                 6               9
 1 3 6 1 4 0 7            5               6                   9
 3-3-3-3 4 0 7            5             6                       9
 */
public class PatternRecognition { // Diagonally test
    public static void main(String[] args) {

        //  Every thing checking but will print true for Horizontally checking
        int[][] values0 = {
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {0, 5, 2, 9, 6, 2, 9},
                {6, 9, 6, 6, 2, 9, 1},
                {1, 5, 9, 1, 4, 9, 7},
                {3, 3, 3, 3, 4, 0, 9}  // 3, 3, 3, 3
        };

        print("Horizontally checking", isConsecutiveFour( values0 ));

        //  Every thing checking but will print true for Vertically checking
        int[][] values1 = {
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {0, 5, 2, 9, 6, 2, 9}, // 5
                {6, 5, 6, 6, 9, 9, 1}, // 5
                {1, 5, 9, 1, 4, 9, 7}, // 5
                {3, 5, 3, 9, 4, 0, 9}  // 5
        };

        print("Vertically checking", isConsecutiveFour( values1 ));

        //  Every thing checking but will print true for Diagonally checking from down to up
        int[][] values2 = {
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {0, 5, 2, 9, 6, 2, 9},  // , , , , 6
                {6, 9, 6, 6, 0, 9, 1},  // , , , 6
                {1, 5, 6, 1, 4, 9, 7},  // , , 6
                {3, 6, 3, 9, 4, 0, 9}   // , 6
        };

        print("Diagonally checking from down to up", isConsecutiveFour( values2 ));

        // Every thing checking but will print true for  Diagonally checking from up to down

        int[][] values3 = {
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {9, 5, 2, 9, 6, 2, 9}, // 9
                {6, 9, 6, 6, 0, 9, 1}, // , 9
                {1, 5, 9, 1, 4, 2, 7}, // , , 9
                {3, 6, 3, 9, 4, 0, 9}  // , , , 9
        };

        print("Diagonally checking from up to down", isConsecutiveFour( values3 ));
    }


    public static boolean isConsecutiveFour(int[][] values) {
        int fourConsecutiveCount = 1;

        // Horizontally checking
        for (int row = 0; row < values.length; row++) {
            for (int column = 0; column < values[row].length -1; column++) {
                if (values[row][column] == values[row][column+1]) {
                    fourConsecutiveCount++;
                }
            }
            if (fourConsecutiveCount == 4) {
                return true;
            }
            fourConsecutiveCount = 1;

        }



        // Vertically checking
        for (int column = 0; column < values[0].length; column++) {
            for (int row = 0; row < values.length - 1; row++) {
                if (values[row][column] == values[row + 1][column]) {
                    fourConsecutiveCount++;
                } else {
                    fourConsecutiveCount = 1;
                }
            }

            if (fourConsecutiveCount == 4) {
                return true;
            }
            fourConsecutiveCount = 1;
        }



        // Diagonally checking from down to up
        int row, column;
        for (int i = 3; i < values.length; i++) {
           row = i;
            for (int j = 0; j < values[i].length - 3; j++) {
                column = j;
                for (; row > 0; row--) {
                    for (; column < i; column++) {
                        if (values[row][column] == values[row-1][column+1]
                            && values[row-2][column+2] == values[row-3][column+3]
                            && values[row][column] == values[row-3][column+3]) {
                            return true;
                        }
                    }

                }
            }
        }


        // Diagonally checking from up to down
        row = 0;
        column = 0;
        for (int i = 2; i < values.length; i++) {
            row = i;
            for (int j = 0; j < values[i].length - 3; j++) {
                column = j;
                for (; row > 0; row++) {
                    for (; column < values[i].length; column++) {
                        if (values[row][column] == values[row+1][column+1]
                                && values[row+2][column+2] == values[row+3][column+3]
                                && values[row][column] == values[row+3][column+3]) {
                            return true;
                        }
                    }

                }
            }
        }
        return false;

    }

    public static void print(String  text, boolean flag) {
        System.out.println(text + ": " + flag);
    }
}
