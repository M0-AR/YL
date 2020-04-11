package Exercise_19._09;

import java.util.ArrayList;

public class SortArrayList { // Yl
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(  );
        for (int i = 0; i < 10 ; i++)
            numbers.add( (int) (i*Math.random()) );

        sort( numbers );
        // Display the array after sorting
        System.out.println("The array after sorting : " + numbers);
    }

    public static <E extends Comparable<E>> void sort(ArrayList<E> list){
        for (int i = 0; i < list.size() ; i++) {
            for (int j = i+1; j < list.size() ; j++) {
                if (list.get( i ).compareTo( list.get( j ) ) > 0){
                    E temp = list.get( i );
                    list.set( i, list.get( j ) );
                    list.set( j, temp );
                }
            }
        }
    }
}
