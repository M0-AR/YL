package Exercise_08._10;
/*********************************************************************************
 * (Largest row and column) Write a program that randomly fills in 0s and 1s into *
 * a 4-by-4 matrix, prints the matrix, and finds the first row and column with    *
 * the most 1s.                                                                   *
 *********************************************************************************/
public class LargestRowAndColumn {

    public static void main(String[] args) {
        // Invoke methods
        int[][] m = generateArray(); // Get 4-by-4 matrix with 0 or 1 data
        int row = largestRow(m); // The most row some has 1 in it
        int column = largestColumn(m); // The most column some has 1 in it


        // Display result
        for (int[] ints : m) // Display the matrix
        {
            for (int anInt : ints)
            {
                System.out.print( anInt + " " );
            }
            System.out.println();
        }

        // Display row and column 's index with most 1s.
        System.out.println("The largest row index: " + row);
        System.out.println("The largest column index: " + column);
    }

    /** Create 4-by-4 array and randomly fills matrix with 0s and 1s*/
    public static int[][] generateArray() {
        // Create a 4-by-4 array
        int[][] m = new int[4][4];
        // Fill the array by 0 or 1
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = (int) (Math.random() * 2);
            }
        }

        return m;
    }

    /** LargestRow finds the first row with the most 1s*/
    public static int largestRow(int[][] m) {
        int rowIndex = 0, // To return index of row, where 1 is being repeated most.
            oldCount = 0; // To keep newCount result in it
        for (int row = 0; row < m.length; row++) {
            // To count how much 1 is in every row
            int newCount = 0;

            for (int column = 0; column < m[row].length; column++) {
                if (m[row][column] == 1)
                    newCount++;
            }

            if (oldCount < newCount) {
                oldCount = newCount;
                rowIndex = row;
            }
        }

        return rowIndex;
    }


    /** LargestRow finds the first column with the most 1s*/
    public static int largestColumn(int[][] m) {
        int columnIndex = 0, // To return index of column, where 1 is being repeated most.
                oldCount = 0; // To keep newCount result in it
        for (int column = 0; column < m.length; column++) {
            // To count how much 1 is in every column
            int newCount = 0;

            for (int row = 0; row < m[column].length; row++) {
                if (m[row][column] == 1)
                    newCount++;
            }

            if (oldCount < newCount) {
                oldCount = newCount;
                columnIndex = column;
            }
        }

        return columnIndex;
    }
}
