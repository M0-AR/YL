package Exercise_08._35;
/**
 *      (Largest block)
 *      Given a square matrix with the elements 0 or 1, write a program to
 *      find a maximum square submatrix whose elements are all 1s. Your
 *      program should prompt the user to enter the number of rows in the matrix.
 *      The program then displays the location of the first element in the maximum
 *      square submatrix and the number of the rows in the submatrix.
 *
 *      Your program should implement and use the following method
 *      to find the maxi- mum square submatrix:
 *
 *      public static int[] findLargestBlock(int[][] m)
 *
 *      The return value is an array that consists of three values.
 *      The first two values are the row and column indices for the first
 *      element in the submatrix, and the third value is the
 *      number of the rows in the submatrix
 *
 **/
public class LargestBlock {
    public static void main(String[] args) {
        int[][] matrix = {{1,0,1,0,1}, {1,1,1,0,1}, {1,0,1,1,1}, {1,0,1,1,1}, {1,0,1,1,1}};

        int[] largestBlock = findLargestBlock( matrix );

        System.out.print("The maximum square submatrix is at (" +
                  largestBlock[0] + ", " + largestBlock[1] +")" +
                                " with size " + largestBlock[2]);
    }


    // This solution from: https://codereview.stackexchange.com/questions/88555/find-largest-block-in-matrix
    private static int[] findLargestBlock(int[][] m) {
        int[] max = new int[3];

        // Use the first matrix row as 'current' this will become
        // become 'previous' in the loop, and will not be changed.
        // The column-loop starts from1 1, not 0.
        int[] previous = null;
        int[] current = m[0];

        for (int y = 1; y < m.length; y++) {
            // Prepare the memoization space.
            // Forget the previous, move current back and make a new current.
            previous = current;
            current = new int[m[0].length];
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x] == 1) {
                    int span = 1;
                    if (x > 0) {
                        // No need to check the left-most y, if set, it is always size 1.
                        span = 1 + Math.min( current[x - 1], Math.min( previous[x], previous[x - 1] ) );
                    }
                    if (span > max[2]) {
                        // Because we find the max at x, and y, which are the bottom-right,
                        // we need to subtract the span to get the top-left instead.
                        max[0] = x - span + 1;
                        max[1] = y - span + 1;
                        max[2] = span;
                    }
                    current[x] = span;
                }
            }
        }

        return max;
    }
}
