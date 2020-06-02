package Exercise_07._36;

public class EightQueens { // yl
    public static void main(String[] args) {
        boolean[] queens = new boolean[8];

        printQueen( queens );

    }

    public static void printQueen(boolean[] queens) {
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print( "|" );
                if (isValidPlace( queens, j ))
                    System.out.print( "Q" );
            }
        }
    }

    public static boolean isValidPlace(boolean[] queens, int index) {
        if (index == 0 || index == queens.length)
            return true;

        if (queens[index])
            return false;
        else if (!queens[index] && (queens[index-1] || queens[index+1]))
            return false;

        return true;
    }
}
