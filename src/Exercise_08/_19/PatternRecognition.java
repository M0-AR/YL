package Exercise_08._19;

public class PatternRecognition { // Diagonally checking back
    public static void main(String[] args) {
        int[][] values = {
                {0, 1, 0, 3, 1, 6, 1},
                {0, 1, 6, 8, 6, 0, 1},
                {5, 5, 2, 1, 6, 2, 9},
                {6, 5, 6, 6, 1, 9, 1},
                {1, 5, 6, 1, 4, 0, 7},
                {3, 6, 3, 3, 4, 0, 7}
        };

        System.out.println(isConsecutiveFour( values ));

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



        // Diagonally checking
        int row, column;
        for (int i = 3; i < values.length; i++) {
           row = i;
            for (int j = 0; j < values[i].length - 3; j++) {
                column = j;
                for (; row > 0; row--) {
                    for (; column < i; column++) {
                        if (values[row][column] == values[row-1][column+1]) {
                            fourConsecutiveCount++;
                        }
                    }

                    if (fourConsecutiveCount == 4) {
                        return true;
                    }
                    fourConsecutiveCount = 0;
                }
            }


        }





        return false;

    }
}
