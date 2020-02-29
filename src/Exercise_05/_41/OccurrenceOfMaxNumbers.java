package Exercise_05._41;

public class OccurrenceOfMaxNumbers {
    public static void main(String[] args) { // github

        int[] numbers = {3,5,2,5,5,5};

        int max = numbers[0];
        int count = 1;
        for (int i = 1; i < numbers.length ; i++) {
            if (max < numbers[i]){
                max = numbers[i];
                count = 1;
            }else if (max == numbers[i])
                count++;
        }

        // Display result
        System.out.println("The largest number is: " + max);
        System.out.println("The occurrence count of the largest number is: " + count);
    }
}
