package Exercise_06._12;
/*
(Display characters) Write a method that prints characters using the following
header:
public static void printChars(char ch1, char ch2, int
numberPerLine)
This method prints the characters between ch1 and ch2 with the specified numbers
per line. Write a test program that prints ten characters per line from 1 to Z.
Characters are separated by exactly one space.
*/
public class DisplayCharacters {
    public static void main(String[] args) {
        printChars( '1', 'Z', 10 );
    }

    public static void printChars(char ch1, char ch2, int numberPerLine) {
        for (int i = ch1, count = 1; i <= ch2; i++, count++) {
            System.out.print((char)i + " ");
            if (count % numberPerLine == 0)
                System.out.println();
        }
    }
}
