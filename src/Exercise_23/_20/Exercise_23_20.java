package Exercise_23._20;

import java.util.Scanner;

public class Exercise_23_20 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of values: ");
        int size = input.nextInt();
        int[] values = new int[size];
        System.out.print("Enter " + size + " integers: ");
        for (int i = 0; i < size; i++) {
            values[i] = input.nextInt();
        }
        mergeSort(values);
        System.out.println(java.util.Arrays.toString(values));
    }

    public static void mergeSort(int[] values) {
        if (values.length < 2) {
            return;
        }
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(int[] values, int[] temp, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        mergeSort(values, temp, low, middle);
        mergeSort(values, temp, middle + 1, high);
        merge(values, temp, low, middle, high);
    }

    private static void merge(int[] values, int[] temp, int low, int middle, int high) {
        System.arraycopy(values, low, temp, low, high - low + 1);
        int left = low;
        int right = middle + 1;
        for (int index = low; index <= high; index++) {
            if (left > middle) {
                values[index] = temp[right++];
            } else if (right > high) {
                values[index] = temp[left++];
            } else if (temp[left] <= temp[right]) {
                values[index] = temp[left++];
            } else {
                values[index] = temp[right++];
            }
        }
    }
}
