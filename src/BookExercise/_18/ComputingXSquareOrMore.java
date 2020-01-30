package BookExercise._18;

public class ComputingXSquareOrMore {
    public static void main(String[] args) {
        System.out.println(xSquareOrMOre( 3, 3 ));
    }

    static int xSquareOrMOre(int x, int n){
        if (n == 1)
            return x;

        return x * xSquareOrMOre( x, n -1 );
    }
}
