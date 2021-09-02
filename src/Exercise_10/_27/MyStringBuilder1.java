package Exercise_10._27;

public class MyStringBuilder1 {
    private String s;

    public MyStringBuilder1(String s) {
        this.s = s;
    }

    public MyStringBuilder1 append(MyStringBuilder1 s) {
        String temp = this.s;
        temp += s;
        return new MyStringBuilder1(temp);
    }

    public MyStringBuilder1 append(int i) {
        String temp = this.s;
        temp += i + "";
        return new MyStringBuilder1(temp);    }

    public int length() {
        return s.length();
    }

    public char charAt(int index) {
        return s.charAt(index);
    }

    public MyStringBuilder1 toLowerCase() {
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
               temp += (char)(s.charAt(i) + 32);
            }
        }
        return new MyStringBuilder1(temp);
    }

    public MyStringBuilder1 substring(int begin, int end) {
        String temp = "";
        for (int i = begin; i < end; i++) {
            temp += this.s.charAt(i);
        }
        return new MyStringBuilder1(temp);
    }

    public String toString() {
        return this.s;
    }
}
