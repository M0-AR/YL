package Exercise_08._10;

public class LargestRowAndColumn {

    public static void main(String[] args) { // todo check should I reapeat the code for column

        // Create an 4x4 array
        int[][] m = generateArray();

        // Invoke methods
        int row = largestRow(m); // The most row some has 1 in it
        /*int column = largestColumn(m); // The Column row some has 1 in it*/


        // Display result
        for (int[] ints : m) {
            for (int anInt : ints) {
                System.out.print( anInt + " " );
            }
            System.out.println();
        }
        System.out.println("The largest row index: " + row);
    }

    public static int[][] generateArray() {
        int[][] m = new int[4][4];
        // Fill the array by 0 or 1
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = (int) (Math.random() * 2);
            }
        }

        return m;
    }

    public static int largestRow(int[][] m) {
        int rowIndex = 0, // To return index of row, where 1 is being repeated most.
            oldCount = 0; // To keep newCount result in it
        for (int i = 0; i < m.length; i++) {
            // To count how much 1 is in every row
            int newCount = 0;

            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 1)
                    newCount++;
            }

            if (oldCount < newCount) {
                oldCount = newCount;
                rowIndex = i;
            }
        }

        return rowIndex;
    }

    /*public static int largestColumn(int[][] m) {

    }*/
}
