package Exercise_10._28;
// Todo: Print the real value instead of printing address of object when using toString()
public class TestMyStringBuilder2 {
    public static void main(String[] args) {
        MyStringBuilder2 s1 =
                new MyStringBuilder2(new char[]{'a', 'b', 'c', 'd', 'p'});
        MyStringBuilder2 s2 = new MyStringBuilder2("xyz");

        System.out.println("S1 -> length(): " + s1.length());
        System.out.println("S2 -> toString(): " + s2.toString());
        System.out.println("S1 -> subString(1, 2): " + s1.subString(1, 2));

        s1.revers();
        System.out.println("S1 -> revers() then subString(2): " + s1.subString(2));
        System.out.println("S1 -> toUpperCase() then toString(): " + s1.toUpperCase().toString());
    }
}
