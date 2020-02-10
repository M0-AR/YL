package Exercise_05._15;
/*
(Display the ASCII character table) Write a program that prints the characters in
the ASCII character table from ! to ~. Display ten characters per line. The ASCII
table is shown in Appendix B. Characters are separated by exactly one space.
*/
public class ASCIICharacterTable {
    public static void main(String[] args) {

        int newLine = 0;
        for (char c = '!'; c != '~' ; c++) {
            newLine++;
            System.out.print( c + " "
                    + ((newLine % 10 ==0) ? "\n": ""));
        }
    }
}
