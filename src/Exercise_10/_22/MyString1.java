package Exercise_10._22;

public class MyString1 {
    private char[] chars;

    public MyString1(char[] chars) {
        this.chars = new char[chars.length];
        System.arraycopy(chars, 0, this.chars, 0, chars.length);
    }

    public char charAt(int index) {
        return chars[index];
    }

    public int length() {
        return chars.length;
    }

    public MyString1 substring(int begin, int end) {
        char[] temp = new char[end - begin];
        for (int i = begin; i < end; i++) {
            temp[i - begin] = chars[i];
        }
        return new MyString1(temp);
    }

    public MyString1 toLowerCase() {
        char[] temp = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            temp[i] = Character.toLowerCase(chars[i]);
        }
        return new MyString1(temp);
    }

    public boolean equals(MyString1 s) {
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] != s.charAt(i))
                return false;
        }
        return true;
    }

    public static MyString1 valueOf(int i) {
        char[] temp = new char[40];
        int size = 0;
        while (i != 0) {
            int number = i % 10;
            i = i / 10;
            temp[size] = (char)(number + '0');
            size++;
        }

        char[] temp2 = new char[size];
        for (int k = size; k >= 0; k--) {
            temp2[k] = temp[size - k - 1];
        }
        return new MyString1(temp2);
    }

    public char[] toChars() {
        return chars;
    }
}
