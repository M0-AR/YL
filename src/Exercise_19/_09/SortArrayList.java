package Exercise_19._09;
/*********************************************************************************
 * (Sort ArrayList) Write the following method that sorts an ArrayList:           *
 *                                                                                *
 * public static <E extends Comparable<E>>                                        *
 * 	void sort(ArrayList<E> list)                                                  *
 *********************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;

public class SortArrayList {
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

        // Sort
        sort( stringList );
        sort1( numbers );
        sort( charList );
        sort1( doubleList );

        // Display the array after sorting
        System.out.println( "The array after sorting : " + numbers );
        System.out.println( "The array after sorting : " + stringList );
        System.out.println( "The array after sorting : " + charList );
        System.out.println( "The array after sorting : " + doubleList );
    }

    // Method 1

    /**
     * Sorts an ArrayList of comparable objects
     */
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {

        for (int i = 0; i < list.size() - 1; i++)
            for (int j = i + 1; j < list.size(); j++)
                if (list.get( i ).compareTo( list.get( j ) ) > 0) {
                    E temp = list.get( i );
                    list.set( i, list.get( j ) );
                    list.set( j, temp );
                }

    }


    // Method 2

    /**
     * Sorts an ArrayList of comparable objects
     */
    public static <E extends Comparable<E>> void sort1(ArrayList<E> list) {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.size() - 1; i++) {
            // Find the minimum in the arrayList
            currentMin = list.get( i );
            currentMinIndex = i;

            for (int j = i + 1; j < list.size(); j++) {
                if (currentMin.compareTo( list.get( j ) ) > 0) {
                    currentMin = list.get( j );
                    currentMinIndex = j;
                }
            }

            // Swap list.get(i) with list.get(currentMinIndex)
            if (currentMinIndex != i) {
                list.set( currentMinIndex, list.get( i ) );
                list.set( i, currentMin );
            }
        }

    }


}
