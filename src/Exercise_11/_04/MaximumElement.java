package Exercise_11._04;

import java.util.ArrayList;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a sequence of numbers ending with 0: ");
        int userInput, end = -1;

        while (end != 0) {
            userInput = input.nextInt();
            numbers.add(userInput);
            end = userInput;
        }

        // Display the maximum number
        System.out.println(max(numbers));
    }

    public static Integer max(ArrayList<Integer> list) {
        if (list == null || list.size() == 0)
            return null;

        int max = list.get(0);
        for (Integer integer : list) {
            if (max < integer)
                max = integer;
        }
        return max;
    }
}
