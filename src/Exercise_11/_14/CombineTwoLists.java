package Exercise_11._14;

import java.util.ArrayList;
import java.util.Scanner;

public class CombineTwoLists {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        System.out.print("Enter five integers for list1: ");
        for (int i = 0; i < 5; i++) {
            list1.add(input.nextInt());
        }

        System.out.print("Enter five integers for list2: ");
        for (int i = 0; i < 5; i++) {
            list2.add(input.nextInt());
        }

        System.out.println("The combined list is " + union(list1, list2).toString());
    }

    public static ArrayList<Integer> union(
            ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer num : list1) {
            result.add(num);
        }

        for (Integer num : list2) {
            result.add(num);
        }

        return result;
    }
}
