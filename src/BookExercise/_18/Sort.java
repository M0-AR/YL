package BookExercise._18;

public class Sort {
    public static void main(String[] args) {
        int[] e = {1, 4, 5, 2, 22};
        sort( e );

        for (int v : e) {
            System.out.print( v + " " );
        }
        System.out.println(recursiveBinarySearch( e, 5 )); // 1
    }

    public static void sort(int[] list) {
        sort( list, 0, list.length - 1 );
    }

    private static void sort(int[] list, int low, int high) {

        if (low < high) {
            // Find the smallest number and its index in list[low .. high]
            int indexOfMin = low;
            int min = list[low];
            for (int i = low + 1; i <= high; i++) {
                if (list[i] < min) {
                    min = list[i];
                    indexOfMin = i;
                }
            }


            // Swap the smallest in list[low .. high] with list[low]
            list[indexOfMin] = list[low];
            list[low] = min;

            // Sort the remaining list[low+1 .. high]
            sort( list, low + 1, high );
        }
    }

    public static int recursiveBinarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;
        return recursiveBinarySearch( list, key, low, high );
    }

    public static int recursiveBinarySearch(int[] list, int key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;

        if (key > list[mid])
            return recursiveBinarySearch( list, key, mid + 1, high );
        else if (key == list[mid])
            return mid; // The index of the array
        else // key < list[mid]
            return recursiveBinarySearch( list, key, low, mid - 1 );
    }
}
