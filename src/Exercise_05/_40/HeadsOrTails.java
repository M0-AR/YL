package Exercise_05._40;

public class HeadsOrTails {
    final static int MILLION_TIMES = 1000000;
    public static void main(String[] args) {
        int head = 0;
        int tail = 0;

        int zeroOrOne;
        for (int i = 0; i < MILLION_TIMES; i++) {
            zeroOrOne = (int) (Math.random() * 2);
             if (zeroOrOne == 1)
                 head++;
             else
                 tail++;
        }

        System.out.println("Numbers of Heads: " + head);
        System.out.println("Numbers of Tails: " + tail);
    }
}
