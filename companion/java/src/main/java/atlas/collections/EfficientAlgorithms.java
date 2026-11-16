package atlas.collections;

import java.util.HashMap;
import java.util.Map;

public final class EfficientAlgorithms {
    private EfficientAlgorithms() {
    }

    public static int binarySearch(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid] == target) return mid;
            if (sorted[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static boolean hasPairSum(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) return true;
            if (sum < target) left++;
            else right--;
        }
        return false;
    }

    public static int longestDistinct(String text) {
        Map<Character, Integer> last = new HashMap<>();
        int best = 0;
        int left = 0;
        for (int right = 0; right < text.length(); right++) {
            char current = text.charAt(right);
            Integer previous = last.get(current);
            if (previous != null && previous >= left) {
                left = previous + 1;
            }
            last.put(current, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static int[] prefixSums(int[] values) {
        int[] prefix = new int[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            prefix[i + 1] = prefix[i] + values[i];
        }
        return prefix;
    }

    public static int rangeSum(int[] prefix, int low, int high) {
        return prefix[high + 1] - prefix[low];
    }

    public static int waysToClimb(int stairs) {
        if (stairs <= 2) return Math.max(0, stairs);
        int twoBack = 1;
        int oneBack = 2;
        for (int i = 3; i <= stairs; i++) {
            int current = twoBack + oneBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    public static void main(String[] args) {
        int[] values = {1, 3, 5, 7, 9, 11};
        System.out.println(binarySearch(values, 9));
        System.out.println(hasPairSum(values, 14));
        System.out.println(longestDistinct("abcabcbb"));
        System.out.println(rangeSum(prefixSums(values), 1, 3));
        System.out.println(waysToClimb(10));
    }
}
