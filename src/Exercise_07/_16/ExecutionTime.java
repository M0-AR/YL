package Exercise_07._16;
/*********************************************************************************
 * (Execution time) Write a program that randomly generates an array of 100,000   *
 * integers and a key. Estimate the execution time of invoking the linearSearch   *
 * method in Listing 7.6. Sort the array and estimate the execution time of       *
 * invoking the binarySearch method in Listing 7.7. You can use the following     *
 * code template to obtain the execution time:                                    *
 * long startTime = System.currentTimeMillis();                                   *
 * perform the task;                                                              *
 * long endTime = System.currentTimeMillis();                                     *
 * long executionTime = endTime - startTime;                                      *
 *********************************************************************************/
public class ExecutionTime {
    public static void main(String[] args) {
         final int LENGTH_OF_ARRAY = 100000;

         // Create an array of length 100000
        int[] n = new int[LENGTH_OF_ARRAY];

        for (int i = 0; i < n.length ; i++)
            n[i] = (int)(Math.random() * LENGTH_OF_ARRAY);

        // Estimate the execution time of invoking the linearSearch method
        long startTime = System.nanoTime();
        linearSearch( n, 9999 );
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("linearSearch: "+executionTime);


        // Sort the array before using binarySearch
        long startTime0 = System.nanoTime();
        selectionSort( n );
//        java.util.Arrays.sort(n); TODO More fast
        long endTime0 = System.nanoTime();
        long executionTime0 = endTime0 - startTime0;
        System.out.println("selectionSort: "+executionTime0);


        // Estimate the execution time of invoking the binarySearch method
        long startTime1 = System.nanoTime();
        binarySearch( n, 9999 );
//        java.util.Arrays.binarySearch(n, 9999);
        long endTime1 = System.nanoTime();
        long executionTime1 = endTime1 - startTime1;
        System.out.println("binarySearch: " + executionTime1);
    }


    /** To find element in the array by linear time if not return -1 */
    public static int linearSearch(int[] array, int key){
        for (int i = 0; i < array.length; i++)
            if (key == array[i])
                return i;

        return -1;
    }

    /** To sort the elements in the array */
    public static void selectionSort(int[] list){
        int n = list.length;

        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in the array
            int min = list[i];
            int minIndex = i;


            for (int j = i + 1; j < n; j++)
                if (min > list[j]){
                        min = list[j];
                        minIndex = j;
                }



            // Swap list[i] with list[minIndex] if necessary
            if(minIndex != i){
                list[minIndex] = list[i];
                list[i] = min;
            }
        }
    }



    /** Use binary search to find the key in the list */
    public static int binarySearch(int[] list, int key){
        int low = 0;
        int high = list.length -1;

        while (high >= low){
            int mid = (high+low) / 2;
            if (key == list[mid])
                return mid;
            else if (key < list[mid])
                return high = mid - 1;
            else
                return low = mid + 1;
        }
        return -low -1 ; // Now high < low, key not found
        // e.g., if the method return -4, so the key is not in the list,
        // and the key should be inserted at -(-4 + 1) = 3
    }
}
