package Exercise_11._17;
/*
(Algebra: perfect square) Write a program that prompts the user to enter an integer m and
find the smallest integer n such that m * n is a perfect square. (Hint:
Store all smallest factors of m into an array list. n is the product of the factors that
appear an odd number of times in the array list. For example, consider m = 90,
store the factors 2, 3, 3, and 5 in an array list. 2 and 5 appear an odd number of
times in the array list. Thus, n is 10.) Then m * n is 10 * 90 = 900
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PerfectSquare {
    public static void main(String[] args) {
        int m = promptingUserToEnterNumber("Enter an integer m: ");

        ArrayList<Integer> factorList = getFactors(m);
        ArrayList<Integer> numberAppearOddOfTimesList = getNumbersAppearOddOfTimes(factorList);
        int n = multiplyNumbersInList(numberAppearOddOfTimesList);

        System.out.println("The smallest number n for m * n to be a perfect square is " + n);
        System.out.println("m * n is " + m * n);
    }

    public static int promptingUserToEnterNumber(String message) {
        Scanner input = new Scanner(System.in);

        System.out.print(message);
        return input.nextInt();
    }

    public static ArrayList<Integer> getFactors(int m) {
        ArrayList<Integer> factors = new ArrayList<>();
        int count = 2;

        while (count <= m) {
            if (m % count == 0) {
                factors.add(count);
                m /= count;
            } else {
                count++;
            }
        }

        return factors;
    }

    public static ArrayList<Integer> getNumbersAppearOddOfTimes(ArrayList<Integer> factorList) {
        ArrayList<Integer> numberAppearOddOfTimeList = new ArrayList<>();

        HashMap<Integer, Integer> frequenciesOfNumber = countFrequenciesOfNumber(factorList);

        for (Integer integer : frequenciesOfNumber.keySet()) {
            if (frequenciesOfNumber.get(integer) % 2 != 0)
                numberAppearOddOfTimeList.add(integer);
        }

        return numberAppearOddOfTimeList;
    }

    public static HashMap<Integer, Integer> countFrequenciesOfNumber(ArrayList<Integer> numberList) {
        HashMap<Integer, Integer> frequenciesHashMap = new HashMap<>();

        for (Integer integer : numberList) {
            if (frequenciesHashMap.containsKey(integer))
                frequenciesHashMap.put(integer, frequenciesHashMap.get(integer) + 1);
            else
                frequenciesHashMap.put(integer, 1);
        }

        return frequenciesHashMap;
    }

    public static int multiplyNumbersInList(ArrayList<Integer> numberList) {
        int n = 1;
        for (Integer integer : numberList) {
            n *= integer;
        }
        return n;
    }
}
