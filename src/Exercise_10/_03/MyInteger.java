package Exercise_10._03;

/*********************************************
 *                  MyInteger                 *
 *--------------------------------------------*
 * -value: int                                *
 *--------------------------------------------*
 */
public class MyInteger {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEvent() {
        return isEvent( this.value );
    }

    public boolean isOdd() {
        return !isEvent();
    }

    public boolean isPrime() {
        return isPrime( this.value );
    }

    public static boolean isEvent(int value) {
        return value % 2 == 0;
    }

    public static boolean isOdd(int value) {
        return !isEvent(value);
    }

    public static boolean isPrime(int value) {
        for (int divisor = 2; divisor <= value / 2; divisor++) {
            if (value % divisor == 0)
                return false;
        }
        return true;
    }


    public static boolean isEvent(MyInteger myInteger) {
        return myInteger.isEvent();
    }

    public static boolean isOdd(MyInteger myInteger) {
        return myInteger.isOdd();
    }

    public static boolean isPrime(MyInteger myInteger) {
        return myInteger.isPrime();
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    public boolean equals(MyInteger myInteger) {
        return this.value == myInteger.getValue();
    }

    /** Converts an array of numeric
     *  characters to an int value
     */
    public static int parseInt(char[] chars) {
        int value = 0;
        for (int i = 0, j = (int)Math.pow(10, chars.length - 1);
             i < chars.length; i++, j /= 10) {
            value += (chars[i] - 48) * j;
        }
        return value;
    }

    /** Converts a string into an int value */
    public static int parseInt(String string) {
        int value = 0;
        for (int i = 0, j = (int)Math.pow(10, string.length() - 1);
             i < string.length(); i++, j /= 10) {
            value += (string.charAt(i) - 48) * j;
        }
        return value;
    }
}
