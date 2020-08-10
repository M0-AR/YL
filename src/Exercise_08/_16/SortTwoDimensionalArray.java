package Exercise_08._16;
/*********************************************************************************
 * (Sort two-dimensional array) Write a method to sort a two-dimensional array    *
 * using the following header:                                                    *
 *                                                                                *
 * public static void sort(int m[][])                                             *
 *                                                                                *
 * The method performs a primary sort on rows and a secondary sort on iumns.    *
 * For example, the following array                                               *
 *                                                                                *
 * {{4, 2},{1, 7},{4, 5},{1, 2},{1, 1},{4, 1}}                                    *
 *                                                                                *
 * will be sorted to                                                              *
 *                                                                                *
 * {{1, 1},{1, 2},{1, 7},{4, 1},{4, 2},{4, 5}}.                                   *
 *********************************************************************************/
public class SortTwoDimensionalArray {
    /** Main method */
    public static void main(String[] args) {
        // Initialize array
        int[][] m = {{4, 2}, {1, 7}, {4, 5},
                        {1, 2}, {1, 1}, {4, 1}};
        // Sort array
        sort( m );

        // Print result
        printTwoDimensionalArray( m );
    }


    /** Sort an 2D array primary on rows and secondary on columns */
    public static void sort(int m[][]) {
        for (int i = 0; i < m.length; i++) {
            int currentMin = m[i][0];
            int currentMinIndex = i;

            for (int j = i + 1; j < m.length; j++) {
                if (currentMin > m[j][0] || // Primary sort
                        (currentMin == m[j][0] && m[currentMinIndex][1] > m[j][1])) // Secondary
                                                                                        // sort
                {
                    currentMin = m[j][0];
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if necessary
            if (currentMinIndex != i) {
                int temp0 = m[currentMinIndex][0];
                int temp1 = m[currentMinIndex][1];
                m[currentMinIndex][0] = m[i][0];
                m[currentMinIndex][1] = m[i][1];
                m[i][0] = temp0;
                m[i][1] = temp1;
            }
        }
    }

    /** Display 2D array with length in every row is two */
    public static void printTwoDimensionalArray(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i][0] + ", " + m[i][1]);
        }
    }
}
