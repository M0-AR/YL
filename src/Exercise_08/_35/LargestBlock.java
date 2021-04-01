package Exercise_08._35;

public class LargestBlock {
    public static void main(String[] args) {
        int[][] matrix = {{1,0,1,0,1}, {1,1,1,0,1}, {1,0,1,1,1}, {1,0,1,1,1}, {1,0,1,1,1}};

        int[] maximumSquareSubMatrix = findLargestBlock( matrix );

        for (int squareSubMatrix : maximumSquareSubMatrix) {
            System.out.print( squareSubMatrix + " " );
        }
    }

    // https://www.techiedelight.com/find-size-largest-square-sub-matrix-1s-present-given-binary-matrix/
    private static int[] findLargestBlock(int[][] m) {
        int[] elements = new int[3];
        for (int column = 0; column < m.length; column++) {
            for (int row = 0; row < m[column].length; row++) {
                if (row+1 != m[column].length && column+1 != m.length)
                    if (m[row][column] == 1 && m[row+1][column] == 1 &&
                        m[row][column+1] == 1 && m[row+1][column+1] == 1) {

                        elements[0] = row;
                        elements[1] = column;
                        elements[2] = 2;
                    }
            }
        }
        return elements;
    }
}
