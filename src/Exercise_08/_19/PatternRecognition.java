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
public class PatternRecognition { // todo improve this
    public static void main(String[] args) {

          //Every thing checking but will print true for Horizontally checking
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
        return  checkVertical(values)   ||
                checkHorizontal(values) ||
                checkDiagonal(values);
               // checkDia(values);
    }




    private static boolean checkVertical(int[][] values) {
        int fourConsecutiveCount = 1;

        // Horizontally checking
        for (int[] value : values) {
            for (int column = 0; column < value.length - 1; column++) {
                if (value[column] == value[column + 1]) {
                    fourConsecutiveCount++;
                }
            }
            if (fourConsecutiveCount == 4) {
                return true;
            }
            fourConsecutiveCount = 1;

        }
        return false;
    }


    private static boolean checkHorizontal(int[][] values) {
        int fourConsecutiveCount = 1;

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
        return false;
    }

    private static boolean checkDiagonal(int[][] values) {
        // Diagonally checking from down to up
        int row, column;
        for (row = values.length - 1; row >= 3; row--) {
            for (column = 0; column < values[row].length - 3; column++) {
                if (values[row][column] == values[row-1][column+1]
                        && values[row-2][column+2] == values[row-3][column+3]
                        && values[row][column] == values[row-3][column+3]) {
                    return true;
                }
            }
        }

        // Diagonally checking from up to down
        for (row = 0; row < values.length - 3; row++) {
            for (column = 0; column < values[row].length - 3; column++) {
                if (values[row][column] == values[row+1][column+1]
                        && values[row+2][column+2] == values[row+3][column+3]
                        && values[row][column] == values[row+3][column+3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*http://soultionmanual.blogspot.com/2016/08/chapter-8-exercise-19-introduction-to.html*/
    public static boolean checkDia(int[][] values) {

        // From upper left to lower right
        // Go through the row

        for (int i = 0; i < values.length; i++) {
            int start = values[i][0];
            int count = 1;
            for (int j = i+1, k = 1; j < values.length && k < values[0].length; j++, k++) {
                if (start == values[j][k]) {
                    count++;
                } else {
                    start = values[j][k];
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }

        // Going through the column

        for (int i = 0; i < values[0].length; i++) {
            int start = values[0][i];
            int count = 1;
            for (int j = i+1, k = 1; j < values[0].length && k < values.length; j++, k++) {
                if (start == values[k][j]) {
                    count++;
                } else {
                    start = values[k][j];
                    count = 1;
                }

                if (count == 4) {
                    return true;
                }
            }
        }

        // From upper right to lower left
        // Go through the column

        for (int i = 0; i < values[0].length; i++) {
            int start = values[0][i];
            int count = 1;
            for (int j = 1, k = i-1; j < values.length && k >= 0; j++, k--) {
                if (start == values[j][k]) {
                    count++;
                } else {
                    start = values[j][k];
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }

        // Going through the row

        for (int i = 0; i < values.length; i++) {
            int start = values[i][values[i].length-1];
            int count = 1;
            for (int j = i+1, k = values[0].length-2; j < values.length && k >= 0; j++, k--) {
                if (start == values[j][k]) {
                    count++;
                } else {
                    start = values[j][k];
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }

        return false;

    }

    public static void print(String  text, boolean flag) {
        System.out.println(text + ": " + flag);
    }
}
