package Exercise_10._23;

public class MyString2 {
    private String str;

    public MyString2(String str) {
        this.str = str;
    }

    public int compare(String s) {
        return str.compareTo(s);
    }

    public MyString2 substring(int begin) {
        return new MyString2(str.substring(begin));
    }

    public MyString2 toUpperCase() {
        return new MyString2(str.toUpperCase());
    }

    public char[] toChars() {
        return str.toCharArray();
    }

    public static MyString2 valueOf(boolean b) {
        return null;
    }
}
