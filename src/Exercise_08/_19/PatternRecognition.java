package Exercise_08._19;

public class PatternRecognition { // Diagonally
    public static void main(String[] args) {

    }


    public static boolean isConsecutiveFour(int[][] values) {
        int fourConsecutiveCount = 0;

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
            fourConsecutiveCount = 0;

        }




        // Vertically checking
        for (int column = 0; column < values.length; column++) {
            for (int row = 0; row < values[row].length; row++) {
                if (values[column][row] == values[column][row+1]) {
                    fourConsecutiveCount++;
                }
            }

            if (fourConsecutiveCount == 4) {
                return true;
            }
            fourConsecutiveCount = 0;
        }



        // Diagonally checking
        for (int row = 4; row < values.length; row++) {
            for (int column = 0; column < values[row].length; column++) {

            }
        }

    }
}
