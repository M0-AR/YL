package Exercise_05._17;

public class DisplayPyramid { // g
    public static void main(String[] args) {

        int spacesAndLines = 7;
        for (int i = 1; i <= spacesAndLines ; i++) {
            int temp = i;
            // take care of spaces before numbes
            for (int j = i; j <spacesAndLines ; j++) {
                System.out.print( "  " );
            }
            // take care of left side's numbers
            for (int j = i; j >0 ; j--) {
                System.out.print(j + " ");
            }
            // take care of right side's numbers
            for (int j = 2; j <= spacesAndLines && temp > 1; j++) {
                System.out.print(j + " ");
                temp--;
            }
            System.out.println();
        }

    }
}
