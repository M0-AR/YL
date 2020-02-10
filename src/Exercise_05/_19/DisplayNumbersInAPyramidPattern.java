package Exercise_05._19;

public class DisplayNumbersInAPyramidPattern { // Todo con
    public static void main(String[] args) {

        for (int i = 1; i <= 8 ; i++) {
            for (int j = i; j < 8  ; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < i ; j++) {
                if (j == 0)
                    System.out.print( 1 + "  " );
                else
                    System.out.print( (int)Math.pow( 2, j ) + "  ");
            }
            System.out.println();
        }
    }
}
