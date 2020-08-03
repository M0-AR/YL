package Exercise_08._16;

public class SortTwoDimensionalArray {
    public static void main(String[] args) {
        int[][] m = {{4, 2}, {1, 7}, {4, 5},
                        {1, 2}, {1, 1}, {4, 1}};
        sort( m );
    }

    public static void sort(int m[][]) {
        int minValue;
        int minRowIndex;
        int columnIndex = 0;
        // Row sorting
        for (int i = 0; i < m.length; i++) {
            minValue = m[i][columnIndex];
            minRowIndex = i;

            for (int j = i + 1; j < m.length; j++) {
                if (minValue > m[j][columnIndex]) {
                    minValue = m[j][columnIndex];
                    minRowIndex = j;
                }
            }

            if (minValue < m[i][columnIndex]) {
                m[minRowIndex][columnIndex] = m[i][columnIndex];
                m[i][columnIndex] = minValue;
            }

        }
    }
}
