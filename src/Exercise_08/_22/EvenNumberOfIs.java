package Exercise_08._22;

public class EvenNumberOfIs { // check yl and github

    public static void main(String[] args) {
       int[][] _6By6Array = generate6By6TwoDimensionalWith0Or1();

       boolean everyRowHasEvenNumberOf1s
               = isEveryRowHasEvenNumberOf1s( _6By6Array );
       boolean everyColumnHasEvenNumberOf1s
               = isEveryColumnHasEvenNumberOf1s( _6By6Array );

        System.out.println("Is every row has even number of 1s: "
                                        + everyRowHasEvenNumberOf1s);
        System.out.println("Is every column has even number of 1s: "
                                        + everyColumnHasEvenNumberOf1s);
    }

    private static int[][] generate6By6TwoDimensionalWith0Or1() {
        int[][] _6By6Array = new int[6][6];
        for (int row = 0; row < _6By6Array.length; row++) {
            for (int column = 0; column < _6By6Array[row].length; column++) {
                _6By6Array[row][column] = (int) (Math.random() * 2);
            }
        }
        return _6By6Array;
    }

    private static boolean isEveryRowHasEvenNumberOf1s(int[][] arrays) {
        for (int[] array : arrays) {
            for (int column = 0; column < array.length; column++) {
                if (array[column] == 1)
                    break;
                if (column == array.length - 1)
                    return false;
            }
        }
        return true;
    }

    private static boolean isEveryColumnHasEvenNumberOf1s(int[][] arrays) {
        for (int column = 0; column < arrays.length; column++) {
            for (int row = 0; row < arrays[row].length; row++) {
                if (arrays[row][column] == 1)
                    break;
                if (row == arrays[row].length - 1)
                    return false;
            }
        }
        return true;
    }
}
