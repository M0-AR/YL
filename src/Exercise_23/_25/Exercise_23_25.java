package Exercise_23._25;

import java.util.Arrays;
import java.util.Random;

public class Exercise_23_25 {
  public static void insertionSort(int[] values) {
    for (int i = 1; i < values.length; i++) {
      int current = values[i];
      int j = i - 1;
      while (j >= 0 && values[j] > current) {
        values[j + 1] = values[j];
        j--;
      }
      values[j + 1] = current;
    }
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
    int middle = (low + high) >>> 1;
    mergeSort(values, temp, low, middle);
    mergeSort(values, temp, middle + 1, high);
    System.arraycopy(values, low, temp, low, high - low + 1);
    int i = low;
    int j = middle + 1;
    int k = low;
    while (i <= middle && j <= high) {
      values[k++] = temp[i] <= temp[j] ? temp[i++] : temp[j++];
    }
    while (i <= middle) {
      values[k++] = temp[i++];
    }
  }

  public static void quickSort(int[] values) {
    quickSort(values, 0, values.length - 1);
  }

  private static void quickSort(int[] values, int low, int high) {
    if (low >= high) {
      return;
    }
    int pivot = values[(low + high) >>> 1];
    int i = low;
    int j = high;
    while (i <= j) {
      while (values[i] < pivot) {
        i++;
      }
      while (values[j] > pivot) {
        j--;
      }
      if (i <= j) {
        int temp = values[i];
        values[i++] = values[j];
        values[j--] = temp;
      }
    }
    quickSort(values, low, j);
    quickSort(values, i, high);
  }

  public static void main(String[] args) {
    Random random = new Random(19);
    int[] source = new int[64];
    for (int i = 0; i < source.length; i++) {
      source[i] = random.nextInt(200);
    }
    int[] expected = source.clone();
    Arrays.sort(expected);
    int[] insertion = source.clone();
    int[] merge = source.clone();
    int[] quick = source.clone();
    insertionSort(insertion);
    mergeSort(merge);
    quickSort(quick);
    check(Arrays.equals(insertion, expected));
    check(Arrays.equals(merge, expected));
    check(Arrays.equals(quick, expected));
    System.out.println("Exercise_23_25 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
