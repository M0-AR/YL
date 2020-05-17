package Exercise_07._23;
/*********************************************************************************
 * (Game: locker puzzle) A school has 100 lockers and 100 students. All lockers   *
 * are closed on the first day of school. As the students enter, the first        *
 * student, denoted S1, opens every locker. Then the second student, S2,          *
 * begins with the second locker, denoted L2, and closes every other locker.      *
 * Student S3 begins with the third locker and changes every third locker (closes *
 * it if it was open, and opens it if it was closed). Student S4 begins with      *
 * locker L4 and changes every fourth locker. Student S5 starts with L5 and       *
 * changes every fifth locker, and so on, until student S100 changes L100.        *
 *                                                                                *
 * After all the students have passed through the building and changed the        *
 * lockers, which lockers are open? Write a program to find your answer and       *
 * display all open locker numbers separated by exactly one space.                *
 * (Hint: Use an array of 100 Boolean elements, each of which indicates whether a *
 * locker is open (true) or closed (false). Initially, all lockers are closed.)   *
 *********************************************************************************/
import java.util.Arrays;
public class Game_lockerPuzzle {
    /** Main method */
    public static void main(String[] args) {
        // Create a boolean array, to indicate whether
        // a locker is open(true) or closed(false)
        boolean[] locker = new boolean[100];

        // Change the situation of locker
        studentLockerChanges( locker );

        // Print the locker if it's open
        print( locker );


    }

    /** To print the locker number if it opens */
    public static void print(boolean[] list){
        int i = 0;
        for (boolean b : list) {
            if (b)
                System.out.print( "L" + (i+1) + " " );
            i++;
        }
    }

    /** Student changes the situation of locker
     * if closed to open or vice versa */
    public static void studentLockerChanges(boolean[] locker){
        // First student open all lockers
        Arrays.fill( locker, true );

        // Second student begin with second locker and
        // Close all other lockers
        for (int i = 2; i < locker.length ; i++) {
            locker[i] = false;
        }


        // S3 begins with the third locker and changes every
        // third locker (closes it if it was open, and opens it
        // if it was closed). Student S4 begins with
        //  locker L4 and changes every fourth locker. so on...
        for (int i = 2; i < locker.length ; i++) {
            // 2 mean third student and so on...
            for (int j = i; j < locker.length ; j = j + i) {
                if (locker[j])
                    locker[j] = false;
                else
                    locker[j] = true;
            }
        }
    }
}
