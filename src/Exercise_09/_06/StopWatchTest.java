package Exercise_09._06;

public class StopWatchTest {
    public static void main(String[] args) {
        int size = 100000;

        double[] list = new double[size];

        for (int i = 0; i < list.length; i++) {
            list[i] = Math.random() * list.length;
        }

        StopWatch stopWatch = new StopWatch();
        selectionSort(list);
        stopWatch.stop();

        System.out.println("The sort time is " + stopWatch.getElapsedTime());
    }

    /** The method for sorting the numbers */
    private static void selectionSort(double[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            // Find the minimum in the list[i..list.length-1]
            double currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if necessary
            if (currentMin != list[i]) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }
}
