package Exercise_10._28;
// Todo: continue with the methods.
public class MyStringBuilder2 {
    private int size = 0;
    private int capacity = 0;
    private char[] buffer;

    public MyStringBuilder2() {

    }

    public MyStringBuilder2(char[] chars) {
        this.buffer = new char[chars.length];
        size = capacity = chars.length;
        System.arraycopy(chars, 0, buffer, 0, chars.length);
    }

    public MyStringBuilder2(String s) {
        this.buffer = new char[s.length()];
        size = capacity = s.length();

        for (int i = 0; i < buffer.length; i++)
            buffer[i] = s.charAt(i);
    }

    public MyStringBuilder2 insert(int offset, MyStringBuilder2 s) {
        return null;
    }
}
