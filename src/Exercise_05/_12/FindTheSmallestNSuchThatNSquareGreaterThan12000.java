package Exercise_05._12;
/** Find the smallest number such that n*n < 12000 */
public class FindTheSmallestNSuchThatNSquareGreaterThan12000 {
    public static void main(String[] args) { // g
        int i = 1;

        while (i * i <= 12000)
            i++;
        System.out.println("This number is " + i);
    }
}
