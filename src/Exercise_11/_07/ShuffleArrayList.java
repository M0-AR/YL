package Exercise_11._07;

import java.util.ArrayList;
import java.util.Arrays;

public class ShuffleArrayList {
    public static void main(String[] args) {
        // Initiate array of numbers
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,4,5,6));

        // Shuffle numbers
        shuffle(numbers);

        // Print numbers
        for (Integer number : numbers) {
            System.out.print(number + ", ");
        }

    }
    public static void shuffle(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int randomNumber = (int) (Math.random() * list.size());
            swap(list, i, randomNumber);
        }
    }

    private static void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
