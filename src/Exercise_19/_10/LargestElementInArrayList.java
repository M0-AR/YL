package Exercise_19._10;
/*********************************************************************************
 * (Largest element in ArrayList) Write the following method that returns the     *
 * largest element in an ArrayList:                                               *
 *                                                                                *
 * public static <E extends Comparable<E>> E max(ArrayList<E> list)               *
 *********************************************************************************/
import java.util.ArrayList;
import java.util.Arrays;

public class LargestElementInArrayList { // Yl and github
    public static void main(String[] args) {
        // Create a  list of Integers
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            numbers.add( (int) (i * Math.random()) );

        // Create a list of Strings
        String[] stringArray = {"Mohamad", "Omar", "Ahmed"};
        ArrayList<String> stringList =
                new ArrayList<>( Arrays.asList( stringArray ) );

        // Create a list of Characters
        Character[] charArray = {'a', 'J', 'r'};
        ArrayList<Character> charList =
                new ArrayList<>( Arrays.asList( charArray ) );


        // Create a list of Doubles

        ArrayList<Double> doubleList = new ArrayList<>();
        for (double i = 1.0; i <= 10; i++)
            doubleList.add( Math.random() );

        // Find max and display results
        System.out.println("Maximum String object: " + max( stringList ));
        System.out.println("Maximum Integer object: " + max( numbers ));
        System.out.println("Maximum Character object: " + max( charList  ));
        System.out.println("Maximum Double object: " + max( doubleList ));
    }

    /** Find the maximum element in the list */
    public static <E extends Comparable<E>> E max(ArrayList<E> list){
        // Store the maximum of list
        E max = list.get( 0 );

        // Go throw the list to find the maximum element
        for (int i = 1; i < list.size(); i++)
            if (list.get( i ).compareTo( max ) > 0)
                max = list.get( i );

        return max;
    }
}
