package Exercise_08._36;

import java.util.Scanner;

public class LatinSquare {
    public static void main(String[] args) {
        char[][] _4x4_true_test = {{'A', 'B', 'C', 'D'},
                                   {'B', 'A', 'D', 'C'},
                                   {'C', 'D', 'B', 'A'},
                                   {'D', 'C', 'A','B'}};

        char[][] _4x4_false_test = {{'A', 'B', 'C', 'D'},
                                    {'B', 'A', 'D', 'C'},
                                    {'C', 'D', 'B', 'A'},
                                    {'D', 'C', 'A','B'}};

        char[][] inputTest = {{'A', 'F', 'D'}};

        char[][] _4x4 = getLatinSquare();
        if (!isFromAToC( _4x4, 0)) {
            System.out.println("Wrong input: the letters must be from A to C");
            System.exit( 1 );
        }

        boolean _isLatinSquare = isLatinSquare( _4x4 );
        System.out.println(_isLatinSquare);
    }


    private static char[][] getLatinSquare() {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter number n: ");
        int n = input.nextInt();
        char[][] latinSquare = new char[n][n];
        for (int i = 0; i < latinSquare.length; i++) {

            if (i == 1 && !isFromAToC( latinSquare, i)) {
                System.out.println("Wrong input: the letters must be from A to C");
                System.exit( 1 );
            }

            if ( i == 3 && isFromAToC(latinSquare, i)) {
                System.out.println("Wrong input: the letters must be from A to C");
                System.exit( 1 );
            }

            for (int j = 0; j < latinSquare[i].length; j++) {
                latinSquare[i][j] = input.next().charAt( 0 );
            }
        }
        return latinSquare;
    }

    private static boolean isFromAToC(char[][] c, int i) {
        if (c[i][0] == 'A' && c[i][1] == 'B' && c[i][2] == 'C')
            return true;
        return false;
    }

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
