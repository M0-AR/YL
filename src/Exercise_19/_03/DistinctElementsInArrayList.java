package Exercise_19._03;
/*********************************************************************************
 * (Distinct elements in ArrayList) Write the following method that returns a new *
 * ArrayList. The new list contains the non-duplicate elements from the original  *
 * list.                                                                          *
 *                                                                                *
 * public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)             *
 *********************************************************************************/
import java.util.ArrayList;

public class DistinctElementsInArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(  );
        list.add( 1 );
        list.add( 1 );
        list.add( 2 );
        list.add( 2 );

        list = removeDuplicates( list );

        System.out.print( list );
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j <list.size() ; j++) {
                if (list.get( i ) == list.get( j )){
                    list.remove( j );
                }
            }
        }
        return list;
    }
}
