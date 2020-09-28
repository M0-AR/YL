package Exercise_08._18;

import java.util.Arrays;

public class ShuffleRows {// Check YL and github
    public static void main(String[] args) {
        int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
        shuffle( m );
        System.out.println( Arrays.deepToString(m));

    }

    public static void shuffle(int[][] m) {
      int rowIndexForShuffle = 0;
      shuffleRowOrColumn( m, rowIndexForShuffle );

      int columnIndexForShuffle = 1;
      shuffleRowOrColumn( m, columnIndexForShuffle );
    }

    public static void shuffleRowOrColumn(int[][] m, int index) {
        for (int i = 0; i < m.length; i++) {
            int randomNumber = (int) (Math.random() * m.length);
            int temp = m[i][index];
            m[i][index] = m[randomNumber][index];
            m[randomNumber][index] = temp;
        }
    }
}
