package Exercise_05._15;

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
