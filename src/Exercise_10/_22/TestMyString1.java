package Exercise_10._22;

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
