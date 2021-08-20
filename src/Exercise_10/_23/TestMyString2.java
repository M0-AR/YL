package Exercise_10._23;
/**********************************************************************************
 * (Implement the String class) The String class is provided in the Java library. *
 * Provide your own implementation for the following methods (name the new        *
 * class MyString2):                                                              *
 * public MyString2(String s);                                                    *
 * public int compare(String s);                                                  *
 * public MyString2 substring(int begin);                                         *
 * public MyString2 toUpperCase();                                                *
 * public char[] toChars();                                                       *
 * public static MyString2 valueOf(boolean b);                                    *
 *********************************************************************************/
public class TestMyString2 {
    public static void main(String[] args) {
        // Create two MyString2 objects
        MyString2 str1 = new MyString2("test1");
        MyString2 str2 = new MyString2("test2");

        // Compare
        System.out.println("Compare str1 to \"test1\": ");
        System.out.println(str1.compare("test1"));

        // Substring
        System.out.println("\nDisplay str2 at index 3: ");
        print(str2.substring(3));

        // ToUpperCase
        System.out.println("\nConvert str2 to upper case:");
        print(str2.toUpperCase());

        // ValueOf
        System.out.println("\nDisplay the value true as a MyString2 object: ");
        print(MyString2.valueOf(true));
    }

    /** Display a MyString2 object */
    public static void print(MyString2 str) {
        print(str.toChars());
    }

    /** Display an array of character */
    private static void print(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        System.out.println();
    }
}
