package Exercise_11._07;
/*********************************************************************************
 * (Shuffle ArrayList) Write the following method that shuffles the elements in   *
 * an ArrayList of integers.                                                      *
 *                                                                                *
 * public static void shuffle(ArrayList<Integer> list)                            *
 *********************************************************************************/
import java.util.ArrayList;
import java.util.Scanner;

public class ShuffleArrayList {
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Create ArrayList of Integer type
        ArrayList<Integer> numbers = new ArrayList<>();

        // Prompt the user to enter a list of Integer ending with 0
        System.out.println("Enter a list of integers ending with 0: ");
        Integer number = input.nextInt();

        while (number.intValue() != 0) {
            numbers.add(number);
            number = input.nextInt();
        }

        // Shuffle numbers
        shuffle(numbers);

        // Print numbers
        for (Integer n : numbers) {
            System.out.print(n + " ");
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
