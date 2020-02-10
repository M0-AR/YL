package Exercise_05._18;

public class DisplayFourPatternsUsingLoops {
    public static void main(String[] args) { // check c and d in g

        // 1. Pattern A
        System.out.println("Pattern A");
        for (int i = 1; i <= 6 ; i++) {
            for (int j = 1; j <=i ; j++) {
                    System.out.print( j + " ");
            }
            System.out.println();
        }

        // 1. Pattern B
        System.out.println("Pattern B");
        for (int i = 6; i >= 1 ; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print( j + " ");
            }
            System.out.println();
        }

        // 1. Pattern C
        System.out.println("Pattern C");
        for (int i = 1; i <= 6 ; i++) {
            for (int j = 5; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = i; j >=1 ; j--) {
                System.out.print( j );
            }
            System.out.println();
        }

        // 1. Pattern D
        System.out.println("Pattern D");
        for (int i = 6; i >=1  ; i--) {
            for (int j = i; j <=5 ; j++) {
                System.out.print( " " );
            }
            for (int j = 1; j <=i ; j++) {
                System.out.print( j );
            }

            System.out.println();
        }


    }
}
