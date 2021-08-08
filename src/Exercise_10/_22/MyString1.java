package Exercise_10._22;

public class MyString1 {
    private char[] chars;

    public MyString1(char[] chars) {
        this.chars = chars;
    }

    public char charAt(int index) {
        return chars[index];
    }

    public int length() {
        return chars.length;
    }

    public MyString1 substring(int begin, int end) {
        char[] c = new char[end - begin + 1];
        for (int i = 0; i < c.length; i++) {
            c[i] = chars[begin++];
        }
        return new MyString1(c);
    }

    public MyString1 toLowerCase() {
        return null; // Todo
    }

    public boolean equals(MyString1 s) {
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] != s.charAt(i))
                return false;
        }
        return true;
    }

    public static MyString1 valueOf(int i) {
        return new MyString1(new char[]{(char) i});
    }
}
