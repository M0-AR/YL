package Exercise_07._37;
/** (Game: bean machine) The bean machine, also known as a quincunx or
     the Galton box, is a device for statistics experiments named after
     English scientist Sir Francis Galton. It consists of an upright board
     with evenly space nails (or pegs) in a triangular form, as shown in Figure 7.13.

     Balls are dropped from the opening of the board. Every time a ball hits a nail,
     it has a 50% chance of falling to the left or to the right. The piles of balls
     are accumulated in the slots at the bottom of the board.

     Write a program that simulates the bean machine. Your program should prompt the
     user to enter the number of the balls and the number of the slots (maximum 50) in the machine.
     Simulate the falling of each ball by printing its path. For example, the path for the ball in
     Figure 7.13b is LLRRLLR and the path for the ball in Figure 7.13c is RLRRLRR.
     Display the final buildup of the balls in the slots in a histogram. Here is a sample run of the program:

     Enter the number of balls to drop: 5

     Enter the number of slots in the bean machine: 8

     LRLRLRR

     RRLLLRR

     LLRLLRR

     RRLLLLL

     LRLRRLR

     O

     O

     O O O

    (Hint: Create an array named slots. Each element in slots stores the number of balls in a slot.
    Each ball falls into a slot via a path. The number of Rs in a path is the position of the slot
    where the ball falls. For example, for the path LRLRLRR, the ball falls into slots[4], and for
    the path is RRLLLLL, the ball falls into slots[2].)
 */
import java.util.Scanner;

public class BeanMachine {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user
        System.out.print("Enter the number of balls to drop: "); // Prompt the user to enter the number of balls
        int numberOfBalls = input.nextInt();

        System.out.print("Enter the number of slots in the bean machine: "); // Prompt the user to enter tne number of slots
        int numberOfSlots = input.nextInt();

        // Initialise two arrays
        String[] ballsPath = new String[numberOfBalls]; // Stores balls path
        int[] slots = new int[numberOfSlots]; // Stores the number of balls in a slot

        // Call methods
        generateRandomPath(ballsPath); // To generate path for every ball in ballsPath array
        countRLetter(ballsPath, slots); // Count R letter in ballsPath and increase the number of slot index by one according to ball's path
        printBalls(ballsPath, slots); // Print Ball's path and Balls in right slot
    }

    /** To generate path for every ball in ballsPath array */
    public static void generateRandomPath(String[] array) {
        for (int i = 0; i < array.length ; i++) {
            array[i] = "";
            for (int j = 0; j < 7; j++) { // 7 is the number of letters in every index in array
                int zeroOrOne = (int)(Math.random() * 2); // Zero = Left and One = Right
                if(zeroOrOne == 1)
                    array[i] += "R";
                else
                    array[i] += "L";
            }
        }
    }

    /**  Count R letter in ballsPath and increase the number of slot index by one according to ball's path*/
    public static void countRLetter(String[] ballsPath, int[] slots) {
        for (int i = 0; i < ballsPath.length; i++) {
            int countR = 0;
            for (int j = 0; j < ballsPath[i].length(); j++)
                if (ballsPath[i].charAt( j ) == 'R')
                    countR++;

            slots[countR % slots.length]++; // Increase one in the slots index
        }
    }

    /** Print Ball's path and Balls in right slot */
    public static void printBalls(String[] ballsPath, int[] slots) {
        System.out.println("\n");
        // Print balls's path
        for (int i = 0; i < ballsPath.length; i++)
            System.out.println(ballsPath[i]);

        // Call method
        int max = findMaximumNumber(slots); // To find maximum number of balls in a specific slot

        System.out.println("\n");
        // Print balls in slot
        for (int i = max; i > 0; i--) {
            int previousIndex = 0; // To print space from the previous ball

            for (int j = 0; j < slots.length; j++) {

                if (max == slots[j]) {
                    // Print spaces
                    for (int k = previousIndex+1; k < j; k++) {
                        System.out.print( " " );
                    }
                    System.out.print( "O" );
                    slots[j]--;
                    previousIndex = j;
                }
            }
            System.out.println();
            max--;
        }
    }

    /** Find maximum number in array*/
    public static int findMaximumNumber(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length -1; i++)
            if (max < array[i])
                max = array[i];

        return max;
    }


}

/** Another solution
 * BeanMachine.java */
/*
import java.util.Scanner;

public class BeanMachine {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int i=0;

        System.out.print("Enter the number of balls to drop:");
        int balls=in.nextInt();
        System.out.print("Enter the number of slots in the bean machine:");
        int n=in.nextInt();
        int []slots=new int[n];
        game(slots,balls,n);
        output(slots,n);

    }

    public static void game(int[]slots,int balls,int n){
        for(int i=0;i<balls;++i){
            System.out.println();
            int r=0;
            for(int j=0;j<n;++j){
                int chance=(int)(Math.random()*2);
                if(chance==1){
                    System.out.print("R");
                    r++;
                }
                else{
                    System.out.print("L");
                }
            }
            slots[r]++;
        }
        System.out.println('\n');
    }

    public static void output(int[]slots,int n){
        int max=0;
        for(int i=0;i<n;++i){
            if(max<slots[i])
                max=slots[i];
        }
        for(int i=max;i>0;--i){
            for(int j=0;j<n;++j){
                int num=slots[j]-i;
                if(num<0){
                    System.out.print(" ");
                }
                else{
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
}*/
