package Exercise_05._40;
/**
(Simulation: heads or tails) Write a program that simulates flipping a coin one
million times and displays the number of heads and tails.
*/
public class HeadsOrTails {
    final static int MILLION_TIMES = 1000000;

    public static void main(String[] args) {
        int head = 0; /** Counts the number of head */
        int tail = 0; /** Counts the number of tail */


        for (int i = 0; i < MILLION_TIMES; i++) {
            int zeroOrOne = (int)
                    ((Math.random() * MILLION_TIMES) % 2) == 1? head++: tail++;
        }

        System.out.println( "Numbers of Heads: " + head );
        System.out.println( "Numbers of Tails: " + tail );
    }
}
