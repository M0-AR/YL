package Exercise_06._12;

public class DisplayCharacters {
    public static void main(String[] args) { // github and y how to print the first line 10 characters
        printChars( '1', 'Z', 10 );
    }

    public static void printChars(char ch1, char ch2, int numberPerLine) {
        for (int i = ch1; i <= ch2; i++) {
            System.out.print((char)i + " ");
            if (i % numberPerLine == 0)
                System.out.println();
        }
    }
}
