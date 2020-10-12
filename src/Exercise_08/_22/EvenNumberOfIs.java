package Exercise_08._22;
/**
 * (Even number of 1s) Write a program that generates a 6-by-6 two-dimensional
 * matrix filled with 0s and 1s, displays the matrix, and checks if every row and
 * every column have an even number of 1s.
 */
public class EvenNumberOfIs { // check yl and github

    public static void main(String[] args) {
        long startTime = System.nanoTime();

       int[][] _6By6Matrix = initialize6By6MatrixWithValues0Or1();

       boolean everyRowHasEvenNumberOf1s
               = isEveryRowHasEvenNumberOf1s( _6By6Matrix );

       boolean everyColumnHasEvenNumberOf1s
               = isEveryColumnHasEvenNumberOf1s( _6By6Matrix );

        System.out.println("Is every row has even number of 1s?: "
                                        + everyRowHasEvenNumberOf1s);
        System.out.println("Is every column has even number of 1s?: "
                                        + everyColumnHasEvenNumberOf1s);

        System.out.println(isEvenParity( _6By6Matrix) ? "All" : "Not all" +
                                                    " rows and columns are even");

        long finishTime = System.nanoTime();
        System.out.println("The time that the program used to execute: " +
                                                    (finishTime - startTime));
        // The time that the program used to execute 60105354
        // because I print more and depends on return false row and column 's methods
    }

    // Initialize matrix and display it
    private static int[][] initialize6By6MatrixWithValues0Or1() {
        int[][] _6By6Array = new int[6][6];
        for (int row = 0; row < _6By6Array.length; row++) {
            for (int column = 0; column < _6By6Array[row].length; column++) {
                _6By6Array[row][column] = (int) (Math.random() * 2);
                System.out.print( _6By6Array[row][column] + " " );
            }
            System.out.println();
        }
        return _6By6Array;
    }


    private static boolean isEvenParity(int[][] matrix) {
        return isEveryRowHasEvenNumberOf1s( matrix ) &&
                    isEveryColumnHasEvenNumberOf1s( matrix );
    }

    private static boolean isEveryRowHasEvenNumberOf1s(int[][] matrix) {
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 1)
                    count++;
            }
            if (count % 2 != 0)
                return false;
        }
        return true;
    }

    private static boolean isEveryColumnHasEvenNumberOf1s(int[][] matrix) {
        for (int column = 0; column < matrix[0].length; column++) {
            int sum = 0;
            for (int row = 0; row < matrix.length; row++)
                sum = matrix[row][column];
            if (sum % 2 != 0)
                return false;
        }
        return true;
    }
}

class YDLiangSolution {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int[][] matrix = new int[6][6];

        // Initialize matrix and display it
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (int)(Math.random() * 2);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        if (isEvenParity(matrix))
            System.out.println("All rows and columns are even");
        else
            System.out.println("Not all rows and columns are even");


        long finishTime = System.nanoTime();
        System.out.println("The time that the program used to execute: " +
                (finishTime - startTime));
        // The time that the program used to execute: 58780407
    }

    public static boolean isEvenParity(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++)
                sum += matrix[i][j];
            if (sum % 2 != 0)
                return false;
        }

        for (int j = 0; j < matrix[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++)
                sum += matrix[i][j];
            if (sum % 2 != 0)
                return false;
        }

        return true;
    }
}
