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
        if (capacity < size + s.length()) {
            increaseCapacity(2 * (size + s.length()));
        }

        String temp = s.toString();
        for (int i = 0; i < temp.length(); i++)
            buffer[size - 1 + temp.length() - i] = buffer[size - 1 - i]; // Todo: not sure what this is try to write a real example to understand it.

        for (int i = 0; i < temp.length(); i++)
            buffer[i + offset] = temp.charAt(i);

        size += temp.length();

        return this;
    }

    private void increaseCapacity(int newCapacity) {
        char[] temp = new char[newCapacity];
        System.arraycopy(buffer, 0, temp, 0, size);
        capacity = newCapacity;
        buffer = temp;
    }

    public int length() {
        return size;
    }

}
