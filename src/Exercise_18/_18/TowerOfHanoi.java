package Exercise_18._18;

public class TowerOfHanoi { // github
    static int count = 0;
    public static void main(String[] args) {
        moveDisks( 3, 'A', 'B', 'C' );
        System.out.println(count);
    }

    public static void moveDisks(int n, char fromTower, char toTower, char auxTower){
        count++;
        if (n == 1)
            System.out.println("Move disk " + n + " from " +
                    fromTower + " to " + toTower);
        else {
            moveDisks( n-1, fromTower, auxTower, toTower );
            System.out.println("Move disk " + n + " from " +
                    fromTower + " to " + toTower);
            moveDisks( n-1, auxTower, toTower, fromTower );
        }

    }

}
