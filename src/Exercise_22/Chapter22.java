package Exercise_22;

public class Chapter22 {
    public static int[] exerciseOne(int[] numbers, int target) {
        if (numbers == null) {
            return new int[] {-1, -1};
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static int exerciseTwo(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int best = numbers[0];
        int current = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            current = Math.max(numbers[i], current + numbers[i]);
            best = Math.max(best, current);
        }
        return best;
    }

    public static long exerciseThree(long base, int exponent, long modulus) {
        if (modulus <= 0) {
            throw new IllegalArgumentException("modulus must be positive");
        }
        long result = 1 % modulus;
        long value = Math.floorMod(base, modulus);
        int power = Math.max(exponent, 0);
        while (power > 0) {
            if ((power & 1) == 1) {
                result = (result * value) % modulus;
            }
            value = (value * value) % modulus;
            power >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] found = exerciseOne(new int[] {4, 9, 2, 11, 7}, 13);
        System.out.println(found[0] + "," + found[1]);
        System.out.println(exerciseTwo(new int[] {-3, 2, -1, 4, -6, 5}));
        System.out.println(exerciseThree(7, 23, 1000000007L));
    }
}
