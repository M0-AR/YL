package Exercise_08._01;

public class SumElementsColumnByColumn {
    public static void main(String[] args) {
        // Test the sumColumn method
        double[][] m = {{1,2},{2,3},{3,4}};

        // Display result
        System.out.println("Display the sum of specified column: "
                                    + sumColumn( m, 1 ));
    }

    /** Returns the sum of all
     * elements in a specified column*/
    public static double sumColumn(double [][] m, int columnIndex){
        int sum = 0;
        for (int i = 0; i < m.length ; i++) {
            sum += m[i][columnIndex];
        }
        return sum;
    }
}
