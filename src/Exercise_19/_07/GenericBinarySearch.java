package Exercise_19._07;

import java.util.Random;
import java.util.Scanner;


public class GenericBinarySearch {
    public static void main(String[] args) { // github
        Integer[] numbers = new Integer[10];
        // Prompt the user to enter 10 digits
        for (int i = 0; i < numbers.length ; i++)
            numbers[i] = i * 3;

        // Display max of 10 digits
        System.out.println("The index of the key is : " + binarySearch( numbers, 14 ));
    }

    public static <E extends Comparable<E>> int binarySearch(E[] list, E key){
        int low = 0;
        int high = list.length-1;

        while (low < high){
            int mid = (high + low) / 2;

            if (list[mid] == key)
                return mid;
            else if (list[mid].compareTo( key ) > 0){
                high = mid - 1;
            }else
                low = mid + 1;
        }

        int indexToAddTheKey = - low - 1;
        return indexToAddTheKey ; // If we not find the key so we return which place should we add this key  (-1*indexToAddTheKey -1)
    }
}
