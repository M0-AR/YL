package Exercise_10._22;
/*********************************************************************************
 * (Implement the String class) The String class is provided in the Java library.*
 * Provide your own implementation for the following methods (name the new       *
 * class MyString1):                                                             *
 *                                                                               *
 * public MyString1(char[] chars);                                               *
 * public char charAt(int index);                                                *
 * public int length();                                                          *
 * public MyString1 substring(int begin, int end);                               *
 * public MyString1 toLowerCase();                                               *
 * public boolean equals(MyString1 s);                                           *
 * public static MyString1 valueOf(int i);                                       *
 ********************************************************************************/

public class TestMyString1 {
    public static void main(String[] args) {
        MyString1 s = new MyString1(new char[] {'a', 'b', 'c'});
        System.out.println(s.length());
        System.out.println(s.charAt(1));
        System.out.println(s.equals(new MyString1(new char[] {'a', 'b', 'c'})));

        char[] chars = MyString1.valueOf(345).toChars();
        for (char c : chars) {
            System.out.print(c);
        }
    }
}
