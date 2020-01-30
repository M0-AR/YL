package BookExercise._18;

public class ComputingTwoSquareOrMore {
    public static void main(String[] args) {
        System.out.println( twoSquareOrMore( 5 ) );
    }

    public static int twoSquareOrMore(int n) {
        if (n == 1)
            return 2;

        return 2 *   twoSquareOrMore( n - 1 );
    }
}
