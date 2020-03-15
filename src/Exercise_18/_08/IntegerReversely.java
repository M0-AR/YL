package Exercise_18._08;

public class IntegerReversely {

    public static void main(String[] args) { // github and y
        reverseDisplay( 12345 );
    }

    public static void reverseDisplay(int value) {
        // Base case
        if (value > 0) {
            System.out.print( value % 10 ); // To print last digit
            reverseDisplay( value / 10 ); // To reduce value by deleting last digit
        }
    }
}
