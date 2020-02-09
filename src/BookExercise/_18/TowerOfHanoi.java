package BookExercise._18;

import java.util.Scanner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        System.out.println("Enter number of disks: ");
//        int n = input.nextInt();
        int n = 3;

        // Find the solution recursively
        moveDisks(n, 'A', 'B', 'C');
    }


    public static void moveDisks(int n, char fromTower, char toTower , char auxTower){
        if (n == 1) // Stopping condition
            System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
        else {
            moveDisks(  n - 1, fromTower, auxTower, toTower );
            System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
            moveDisks( n -1 , auxTower , toTower, fromTower );
        }
    }
}
