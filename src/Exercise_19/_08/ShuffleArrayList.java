package Exercise_19._08;

import java.util.ArrayList;

public class ShuffleArrayList {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(  );
        for (int i = 0; i < 10 ; i++)
            numbers.add( i );

        shuffle( numbers );
        // Display index of key
        System.out.println("The array after shuffle : " + numbers);
    }


    public static <E> void shuffle(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            int randomNumber = (int) (Math.random() * list.size());
            E temp = list.get( randomNumber );
            list.set(randomNumber, list.get( i ) );
            list.set( i, temp );
        }

    }
}
