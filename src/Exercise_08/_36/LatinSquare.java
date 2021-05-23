package Exercise_08._36;

import java.util.Arrays;
import java.util.Scanner;

public class LatinSquare {
    public static void main(String[] args) {
        char[][] matrix = getInput();

        // Create letters
        char[] letters = new char[matrix.length];
        for (int i = 0; i < matrix.length; i++)
            letters[i] = (char)('A' + i);

        char[][] transposedMatrix = getTransposed(matrix);

        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(transposedMatrix[i]);
            if (!Arrays.equals(letters, transposedMatrix[i])) {
                System.out.println("The input array is not a Latin square");
                System.exit(3);
            }
        }

        System.out.println("The input array is a Latin square");
    }


    private static char[][] getInput() {
        Scanner input = new Scanner( System.in );

        System.out.print("Enter number n: ");
        int n = input.nextInt();

        // Create letters
        char[] letters = new char[n];
        for (int i = 0; i < n; i++)
            letters[i] = (char)('A' + i);

        // Enter matrix
        char[][] matrix = new char[n][n];
        System.out.println("Enter " + n + " rows of letters separated by spaces: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               String s = input.next();
               matrix[i][j] = s.charAt( 0 );
            }

            char[] clonedRow = matrix[i].clone();
            Arrays.sort(clonedRow);
            if (!Arrays.equals(clonedRow, letters)) {
                System.out.println("Wrong input: the letters must be from " + letters[0]
                  + " to " + letters[letters.length - 1]);
            }
        }

        return matrix;
    }

    private static char[][] getTransposed(char[][] matrix) {
        char[][] result = new char[matrix.length][matrix.length];

        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    // These two methods are for the previous solutions (Look at previous commits)

    private static boolean isLatinSquare(char[][] c) {
        boolean exactlyOnce = false;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                exactlyOnce = isOccurringExactlyOnceInRowAndColumn(c, i, j);

                if (!exactlyOnce) // Duplication found
                    return false;
            }
        }
        return exactlyOnce;
    }


    private static boolean isOccurringExactlyOnceInRowAndColumn(char[][] c, int i, int j) {
        for (int k = 0; k < c.length; k++) {
            if (k == j) // Same character
                continue;
            if (c[i][k] == c[i][j]) // Occurring more than one
               return false;
        }

        for (int k = 0; k < c.length; k++) {
            if (k == i) // Same character
                continue;
            if (c[k][j] == c[i][j]) // Occurring more than one
                return false;
        }

        return true;
    }

}
