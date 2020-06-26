package Exercise_07._37;

public class BeanMachine {
    public static void main(String[] args) {
        // 2^7 = 128 probabilities of L or R
        // If we have two layer, so we have 2 letters mean like LR, LL, RL and RR with 3 slots
        //

        int[] slots = new int[8]; // Stores the number of balls in a slot


        String[] rightOrLeft = new String[(int) Math.pow( 2, slots.length-1 )];

        boolean rOrL = true;
        for (int i = 1; i < slots.length-1 ; i++) {
            for (int j = 0; j < (int) Math.pow( 2, i ) ; j++) {
                if (rOrL) {
                    rightOrLeft[j] += "R";
                    rOrL = false;
                } else {
                    rightOrLeft[j] += "L";
                    rOrL = true;
                }

            }
        }
        // Random left and right
        // if letter > 0 so ad right and left


    }
}
