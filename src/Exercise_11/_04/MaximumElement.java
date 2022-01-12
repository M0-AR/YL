package Exercise_11._04;

import java.util.ArrayList;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a sequence of numbers ending with 0: ");
        int end = -1;
        ArrayList numbers = new ArrayList();

        while (end != 0) {
            int userInput = input.nextInt();
            numbers.add(userInput);
            end = userInput;
        }

        int maxNumber = max(numbers);
        System.out.println(maxNumber);
    }

    public static Integer max(ArrayList<Integer> list) {
        int max = list.get(0);
        for (Integer integer : list) {
            if (max < integer) max = integer;
        }
        return max;
    }
}
